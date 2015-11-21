package com.hengsu.sure.auth.service.impl;

import com.hengsu.sure.auth.service.FaceService;
import org.springframework.stereotype.Service;

/**
 * Created by haiquanli on 15/11/21.
 */
@Service
public class FaceServiceImpl implements FaceService {
    @Override
    public boolean isSimilar(String registerFaceId, String loginFaceId) {
        return true;
    }
}
