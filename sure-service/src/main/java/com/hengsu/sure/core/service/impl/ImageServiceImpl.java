package com.hengsu.sure.core.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.core.entity.Image;
import com.hengsu.sure.core.repository.ImageRepository;
import com.hengsu.sure.core.model.ImageModel;
import com.hengsu.sure.core.service.ImageService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.io.InputStream;
import java.util.Date;

@Service
public class ImageServiceImpl implements ImageService {

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
        ossClient.deleteObject(ossBucketName, imageModel.getPath());
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
            if("NoSuchKey".equals(e.getErrorCode())){
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

}
