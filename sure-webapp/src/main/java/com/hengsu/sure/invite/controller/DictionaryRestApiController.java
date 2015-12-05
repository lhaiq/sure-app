package com.hengsu.sure.invite.controller;

import com.hengsu.sure.invite.DictionaryType;
import com.hengsu.sure.invite.vo.SceneVO;
import com.hengsu.sure.invite.vo.TimeSlotVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.invite.service.DictionaryService;
import com.hengsu.sure.invite.model.DictionaryModel;
import com.hengsu.sure.invite.vo.DictionaryVO;

import java.util.ArrayList;
import java.util.List;

@RestApiController
@RequestMapping("/sure")
public class DictionaryRestApiController {

    private final Logger logger = LoggerFactory.getLogger(DictionaryRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/invite/scene", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<List<SceneVO>>> getScene() {
        DictionaryModel param = new DictionaryModel();
        param.setType(DictionaryType.SCENE.getCode());
        List<DictionaryModel> dictionaryModels = dictionaryService.selectPage(param);
        List<SceneVO> sceneVOs = beanMapper.mapAsList(dictionaryModels, SceneVO.class);
        ResponseEnvelope<List<SceneVO>> responseEnv = new ResponseEnvelope<>(sceneVOs, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/invite/timeslot", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<List<TimeSlotVO>>> timeSlot() {
        DictionaryModel param = new DictionaryModel();
        param.setType(DictionaryType.TIME_SLOT.getCode());
        List<DictionaryModel> dictionaryModels = dictionaryService.selectPage(param);
        List<TimeSlotVO> timeSlotVOs = new ArrayList<>();
        for (DictionaryModel dictionaryModel : dictionaryModels) {
            TimeSlotVO timeSlotVO = new TimeSlotVO();
            String content = dictionaryModel.getContent();
            String[] timeSolts = content.split("-");
            timeSlotVO.setStart(Integer.parseInt(timeSolts[0]));
            timeSlotVO.setEnd(Integer.parseInt(timeSolts[1]));
            timeSlotVOs.add(timeSlotVO);
        }
        ResponseEnvelope<List<TimeSlotVO>> responseEnv = new ResponseEnvelope<>(timeSlotVOs, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
