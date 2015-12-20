package com.hengsu.sure.auth.service.impl;

import com.hengsu.sure.auth.service.FaceService;
import org.springframework.stereotype.Service;

/**
 * Created by haiquanli on 15/11/21.
 */
@Service
public class FaceServiceImpl implements FaceService {

    private static final String RECOGNITION_COMPARE="https://apicn.faceplusplus.com/v2/recognition/compare?api_secret=YOUR_API_SECRET&api_key=YOUR_API_KEY&face_id2=b864da69abc1bcf50e5e1a0ea8228077&face_id1=f31f90f78c11d5ba26f67e1302a3014d";



    @Override
    public boolean isSimilar(String registerFaceId, String loginFaceId) {
        //TODO 后面再做
        return true;
    }
}
