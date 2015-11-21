package com.hengsu.sure.auth.service;

/**
 * Created by haiquanli on 15/11/21.
 */
public interface FaceService {

    public boolean isSimilar(String registerFaceId, String loginFaceId);
}
