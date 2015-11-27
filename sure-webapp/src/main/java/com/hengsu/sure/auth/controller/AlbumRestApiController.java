package com.hengsu.sure.auth.controller;

import com.hengsu.sure.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.auth.service.AlbumService;
import com.hengsu.sure.auth.model.AlbumModel;
import com.hengsu.sure.auth.vo.AlbumVO;

import java.util.Date;
import java.util.List;

@RestApiController
@RequestMapping("/sure")
public class AlbumRestApiController {

    private final Logger logger = LoggerFactory.getLogger(AlbumRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private AlbumService albumService;


    @RequestMapping(value = "/auth/album", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<AlbumModel>>> getAlbumById(
            @Value("#{request.getAttribute('userId')}") Long userId,
            @RequestParam Integer page,
            @RequestParam Integer size) {

        //设置查询参数
        AlbumModel param = new AlbumModel();
        param.setUserId(userId);
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "create_time");

        //返回数据
        Integer count = albumService.selectCount(param);
        List<AlbumModel> albumModels = albumService.selectPage(param, pageable);
        Page<AlbumModel> pageContent = new PageImpl<AlbumModel>(albumModels, pageable, count);
        ResponseEnvelope<Page<AlbumModel>> responseEnv = new ResponseEnvelope<>(pageContent, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 上传照片
     *
     * @param albumVO
     * @param userId
     * @return
     */
    @RequestMapping(value = "/auth/album", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> createAlbum(
            @RequestBody AlbumVO albumVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        List<Long> imageIds = albumVO.getImageIds();

        for (Long imageId : imageIds) {
            AlbumModel albumModel = new AlbumModel();
            albumModel.setImageId(imageId);
            albumModel.setUserId(userId);
            albumModel.setCreateTime(new Date());
            albumService.createSelective(albumModel);
        }

        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 删除照片
     *
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping(value = "/auth/album/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<String>> deleteAlbum(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        albumService.deleteAlbum(id, userId);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}
