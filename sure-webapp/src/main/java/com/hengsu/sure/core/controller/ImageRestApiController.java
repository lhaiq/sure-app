package com.hengsu.sure.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.core.service.ImageService;
import com.hengsu.sure.core.model.ImageModel;
import com.hengsu.sure.core.vo.ImageVO;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestApiController
@RequestMapping("/sure")
public class ImageRestApiController {

	private final Logger logger = LoggerFactory.getLogger(ImageRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value = "/core/image/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<ImageVO>> getImageById(@PathVariable Long id){
		ImageModel imageModel = imageService.findByPrimaryKey(id);
		ImageVO imageVO =beanMapper.map(imageModel, ImageVO.class);
		ResponseEnvelope<ImageVO> responseEnv = new ResponseEnvelope<ImageVO>(imageVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 上传文件
	 * @param files
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/core/image", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createImage(@RequestParam("file") CommonsMultipartFile[] files,
																 HttpServletRequest request){
		for(CommonsMultipartFile file:files){
		}
		ImageModel imageModel = beanMapper.map(null, ImageModel.class);
		Integer  result = imageService.create(imageModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	

	
	@RequestMapping(value = "/core/image/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateImageByPrimaryKeySelective(@PathVariable Long id,
																					   @RequestBody ImageVO imageVO){
		ImageModel imageModel = beanMapper.map(imageVO, ImageModel.class);
		imageModel.setId(id);
		Integer  result = imageService.updateByPrimaryKeySelective(imageModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
