package com.hengsu.sure.auth.service.impl;

import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.hengsu.sure.auth.service.FaceService;
import com.hengsu.sure.core.ErrorCode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by haiquanli on 15/11/21.
 */
@Service
public class FaceServiceImpl implements FaceService {

    private final Logger logger = LoggerFactory.getLogger(FaceServiceImpl.class);

    @Autowired
    private HttpRequests httpRequests;

    @Override
    public boolean isSimilar(String phone, String loginFaceId) {
        try {
            JSONObject result = httpRequests.recognitionVerify(
                    new PostParameters().setPersonName("sure_" + phone).setFaceId(loginFaceId));
            logger.info("verify account: phone-{},login face id-{}, result={}", phone, loginFaceId, result);
            return result.getBoolean("is_same_person");
        } catch (Exception e) {
            ErrorCode.throwBusinessException(ErrorCode.VERIFY_PERSON_ERROR);
        }
        return false;
    }

    public void createPerson(String phone, String faceId) {
        phone = "sure_" + phone;
        try {
            JSONObject createResult = httpRequests.personCreate(new PostParameters().setPersonName(phone));
            logger.info("create person:{}, result:{}", phone, createResult);
            JSONObject addFaceResult = httpRequests.personAddFace(new PostParameters().setFaceId(faceId));
            logger.info("add face id:{} , result: {}", faceId, addFaceResult);
            JSONObject trainResult = httpRequests.trainVerify(new PostParameters().setPersonName(phone));
            logger.info("train person: {},result :{}", phone, trainResult);
        } catch (Exception e) {
            ErrorCode.throwBusinessException(ErrorCode.CREATE_PERSON_ERROR);
        }
    }
}
