package com.hengsu.sure.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.core.model.StsModel;
import com.hengsu.sure.core.util.RandomUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.core.entity.Image;
import com.hengsu.sure.core.repository.ImageRepository;
import com.hengsu.sure.core.model.ImageModel;
import com.hengsu.sure.core.service.ImageService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);


    private static final long serialVersionUID = 5522372203700422672L;
    // 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
    public static final String STS_API_VERSION = "2015-04-01";

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ImageRepository imageRepo;

    @Autowired
    private OSSClient ossClient;

    @Value("${oss.access.bucket.name}")
    private String ossBucketName;

    @Transactional
    @Override
    public int create(ImageModel imageModel) {
        return imageRepo.insert(beanMapper.map(imageModel, Image.class));
    }

    @Transactional
    @Override
    public int createSelective(ImageModel imageModel) {
        Image image = beanMapper.map(imageModel, Image.class);
        int retVal = imageRepo.insertSelective(image);
        imageModel.setId(image.getId());
        return retVal;
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {

        //先查出图片,然后在删除
        ImageModel imageModel = findByPrimaryKey(id);
        //TODO
//        ossClient.deleteObject(ossBucketName, imageModel.getPath());
        return imageRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ImageModel findByPrimaryKey(Long id) {
        Image image = imageRepo.selectByPrimaryKey(id);
        return beanMapper.map(image, ImageModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(ImageModel imageModel) {
        return imageRepo.selectCount(beanMapper.map(imageModel, Image.class));
    }

    @Transactional
    @Override
    public Long uploadImage(ImageModel imageModel) {

        //上传文件到OSS
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(imageModel.getContentType());
        objectMetadata.setContentLength(imageModel.getLength());
        String key = RandomUtil.generateOSSKey();
        ossClient.putObject(ossBucketName, key, imageModel.getContent(), objectMetadata);

        //保存到数据库
        imageModel.setPath(key);
        imageModel.setTime(new Date());
        createSelective(imageModel);

        return imageModel.getId();
    }

    @Override
    public ImageModel findById(Long id) {

        //从数据库加载图片数据
        ImageModel imageModel = findByPrimaryKey(id);
        if (null == imageModel) {
            ErrorCode.throwBusinessException(ErrorCode.IMAGE_NOT_EXISTED);
        }

        //从OSS读取数据
        OSSObject ossObject = null;

        try {
            ossObject = ossClient.getObject(ossBucketName, imageModel.getPath());
        } catch (OSSException e) {
            if ("NoSuchKey".equals(e.getErrorCode())) {
                ErrorCode.throwBusinessException(ErrorCode.IMAGE_NOT_EXISTED);
            }
        }
        imageModel.setContent(ossObject.getObjectContent());

        return imageModel;
    }


    @Transactional
    @Override
    public int updateByPrimaryKey(ImageModel imageModel) {
        return imageRepo.updateByPrimaryKey(beanMapper.map(imageModel, Image.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(ImageModel imageModel) {
        return imageRepo.updateByPrimaryKeySelective(beanMapper.map(imageModel, Image.class));
    }

    public StsModel acquireSTS(Long userId) {
        String data = readClasspath("config.json");
        JSONObject jsonObject = JSON.parseObject(data);
        String accessKeyId = jsonObject.getString("AccessKeyID");
        String accessKeySecret = jsonObject.getString("AccessKeySecret");

        // RoleArn 需要在 RAM 控制台上获取
        String roleArn = jsonObject.getString("RoleArn");
        long durationSeconds = jsonObject.getLong("TokenExpireTime");
        String policy = readClasspath(jsonObject.getString("PolicyFile"));

        String roleSessionName = "sure-" + userId;

        // 此处必须为 HTTPS
        ProtocolType protocolType = ProtocolType.HTTPS;

        try {
            final AssumeRoleResponse stsResponse = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName,
                    policy, protocolType, durationSeconds);
            StsModel stsModel = new StsModel();
            stsModel.setStatus("200");
            stsModel.setAccessKeyId(stsResponse.getCredentials().getAccessKeyId());
            stsModel.setAccessKeySecret(stsResponse.getCredentials().getAccessKeySecret());
            stsModel.setSecurityToken( stsResponse.getCredentials().getSecurityToken());
            stsModel.setExpiration(stsResponse.getCredentials().getExpiration());
            return stsModel;
        } catch (ClientException e) {
            logger.error("acquire sts error:", e);
            StsModel stsModel = new StsModel();
            stsModel.setStatus(e.getErrCode());
            return stsModel;
        }

    }

    private AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
                                          String roleSessionName, String policy, ProtocolType protocolType, long durationSeconds)
            throws ClientException {
        try {
            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);

            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);

            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            request.setDurationSeconds(durationSeconds);

            // 发起请求，并得到response
            final AssumeRoleResponse response = client.getAcsResponse(request);

            return response;
        } catch (ClientException e) {
            throw e;
        }
    }

    private String readClasspath(String path) {
        try {
            return IOUtils.readStreamAsString(new ClassPathResource("sts/" + path).getInputStream(), "utf-8");
        } catch (IOException e) {
            logger.error("unexpected error", e);
            return null;
        }
    }

}
