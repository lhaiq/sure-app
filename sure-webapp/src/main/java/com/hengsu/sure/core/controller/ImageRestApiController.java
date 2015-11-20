package com.hengsu.sure.core.controller;

import com.hengsu.sure.auth.annotation.IgnoreAuth;
import com.hengsu.sure.core.model.ImageModel;
import com.hengsu.sure.core.service.ImageService;
import com.hengsu.sure.core.vo.ImageVO;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestApiController
@RequestMapping("/sure")
public class ImageRestApiController {

    private final Logger logger = LoggerFactory.getLogger(ImageRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ImageService imageService;

    //TODO 上线后删除

    /**
     * 取得图片
     * @param id
     * @param width
     * @param height
     * @param response
     */
    @IgnoreAuth
    @RequestMapping(value = "/core/image/{id}", method = RequestMethod.GET)
    public void getImageById(@PathVariable Long id,
                             @RequestParam(value = "width", required = false) Integer width,
                             @RequestParam(value = "height", required = false) Integer height,
                             HttpServletResponse response) {
        ImageModel imageModel = imageService.findById(id);
        response.setContentType(imageModel.getContentType());
        OutputStream outputStream = null;
        try {
            //判断是否压缩
            outputStream = response.getOutputStream();
            if (null == width && null == height) {
                IOUtils.copy(imageModel.getContent(), outputStream);
            } else {
                Thumbnails.of(imageModel.getContent())
                        .size(width, height)
                        .toOutputStream(outputStream);
            }

        } catch (IOException e) {
            logger.error("get image error", e);
        } finally {
            if (null != outputStream) {
                IOUtils.closeQuietly(outputStream);
            }
        }


    }

    /**
     * 上传文件
     *
     * @param files
     * @return
     */
    @RequestMapping(value = "/core/image", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<List<Long>>> createImage(@RequestParam("file") CommonsMultipartFile[] files
    ) {

        List<Long> imageIds = new ArrayList<>();
        for (CommonsMultipartFile file : files) {
            ImageModel imageModel = new ImageModel();
            imageModel.setContentType(file.getContentType());
            imageModel.setFilename(file.getOriginalFilename());
            imageModel.setLength(file.getSize());
            try {
                imageModel.setContent(file.getInputStream());
            } catch (IOException e) {
                logger.error("upload error" + file.getName());
                continue;
            }
            Long imageId = imageService.uploadImage(imageModel);
            imageIds.add(imageId);

        }

        ResponseEnvelope<List<Long>> responseEnv = new ResponseEnvelope<>(imageIds, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    @RequestMapping(value = "/core/image/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateImageByPrimaryKeySelective(@PathVariable Long id,
                                                                                      @RequestBody ImageVO imageVO) {
        ImageModel imageModel = beanMapper.map(imageVO, ImageModel.class);
        imageModel.setId(id);
        Integer result = imageService.updateByPrimaryKeySelective(imageModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
