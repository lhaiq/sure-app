package com.hengsu.sure.sns.controller;

import com.hengsu.sure.auth.annotation.IgnoreAuth;
import com.hengsu.sure.sns.model.MTimeModel;
import com.hengsu.sure.sns.service.MTimeService;
import com.hengsu.sure.sns.vo.MTimeVO;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestApiController
@RequestMapping("/sure")
public class MTimeRestApiController {

    private final Logger logger = LoggerFactory.getLogger(MTimeRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private MTimeService mTimeService;

    @IgnoreAuth
    @RequestMapping(value = "/sns/mtime/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<MTimeVO>> getMTimeById(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        MTimeModel mTimeModel = mTimeService.findByPrimaryKey(id);
        MTimeVO mTimeVO = beanMapper.map(mTimeModel, MTimeVO.class);
        ResponseEnvelope<MTimeVO> responseEnv = new ResponseEnvelope<MTimeVO>(mTimeVO,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 添加时光
     *
     * @param mTimeVO
     * @return
     */
    @RequestMapping(value = "/sns/mtime", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> createMTime(
            @RequestBody MTimeVO mTimeVO) {
        MTimeModel mTimeModel = beanMapper.map(mTimeVO, MTimeModel.class);
        Integer result = mTimeService.create(mTimeModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/sns/mtime/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteMTimeByPrimaryKey(@PathVariable Long id) {
        Integer result = mTimeService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/sns/mtime/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateMTimeByPrimaryKeySelective(@PathVariable Long id,
                                                                                      @RequestBody MTimeVO mTimeVO) {
        MTimeModel mTimeModel = beanMapper.map(mTimeVO, MTimeModel.class);
        mTimeModel.setId(id);
        Integer result = mTimeService.updateByPrimaryKeySelective(mTimeModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
