package com.hengsu.sure.core.service;

import java.util.List;

/**
 * Created by haiquanli on 15/11/19.
 */
public interface AuthCodeService {

    public void sendAuthCode(List<String> phones, String templateId, String[] data);


}
