package com.hengsu.sure.auth.service;

/**
 * Created by haiquanli on 15/11/21.
 */
public interface FaceService {

    public boolean isSimilar(String personId, String loginFaceId);

    public String createPerson(String phone, String faceId);
}
