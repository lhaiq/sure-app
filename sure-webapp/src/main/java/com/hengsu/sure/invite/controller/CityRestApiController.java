package com.hengsu.sure.invite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.invite.service.CityService;
import com.hengsu.sure.invite.model.CityModel;
import com.hengsu.sure.invite.vo.CityVO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestApiController
@RequestMapping("/sure")
public class CityRestApiController {

    private final Logger logger = LoggerFactory.getLogger(CityRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/invite/city/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<CityVO>> getCityById(@PathVariable Long id) {
        CityModel cityModel = cityService.findByPrimaryKey(id);
        CityVO cityVO = beanMapper.map(cityModel, CityVO.class);
        ResponseEnvelope<CityVO> responseEnv = new ResponseEnvelope<CityVO>(cityVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/invite/provinces", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<List<CityModel>>> selectProvinces() {
        List<CityModel> cityModels = cityService.selectProvinces();
        ResponseEnvelope<List<CityModel>> responseEnv = new ResponseEnvelope<>(cityModels, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/invite/{provinceId}/city", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<List<CityModel>>> selectCity(
            @PathVariable Long provinceId) {
        CityModel param = new CityModel();
        param.setParentId(provinceId);
        List<CityModel> cityModels = cityService.selectPage(param);
        ResponseEnvelope<List<CityModel>> responseEnv = new ResponseEnvelope<>(cityModels, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/invite/citys", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<List<CityModel>>> getCitys() {

        List<CityModel> cityModels = cityService.selectPage(new CityModel());
        ResponseEnvelope<List<CityModel>> responseEnv = new ResponseEnvelope<>(cityModels, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/invite/city", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<CityVO>> getCityByName(@RequestParam String name) {
        try {
            name = new String(name.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CityModel cityModel = cityService.findByName(name);
        CityVO cityVO = beanMapper.map(cityModel, CityVO.class);
        ResponseEnvelope<CityVO> responseEnv = new ResponseEnvelope<>(cityVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}
