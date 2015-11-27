package com.hengsu.sure.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.google.common.collect.ImmutableList;
import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.core.service.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haiquanli on 15/11/25.
 */
@Service
public class PushServiceImpl implements PushService {

    private final Logger logger = LoggerFactory.getLogger(PushServiceImpl.class);

    @Value("${push.app.id}")
    private String appId;

    @Value("${push.app.key}")
    private String appKey;


//    @Autowired
    private IGtPush push;

    @Override
    public void pushMessage(String data, List<UserModel> users) {

        TransmissionTemplate transmissionTemplate = transmissionTemplate(data);
        ListMessage message = new ListMessage();
        message.setData(transmissionTemplate);

        //设置消息离线，并设置离线时间
        message.setOffline(true);

        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);

        //设置推送目标
        List<Target> targets = new ArrayList<>();
        for (UserModel user : users) {
            Target target = new Target();
            target.setAppId(appId);
            target.setClientId(user.getClientId());
            targets.add(target);
        }

        //获取taskId
        String taskId = push.getContentId(message);
        logger.info("push request:{}", JSON.toJSON(targets));
        //使用taskID对目标进行推送
        //TODO 生产环境
//        IPushResult result = push.pushMessageToList(taskId, targets);
//        logger.info("push response: {}", result.getResponse().toString());

    }

    @Override
    public void pushMessage(String data, UserModel user) {
        pushMessage(data, ImmutableList.of(user));
    }


    private TransmissionTemplate transmissionTemplate(String data) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionType(2);
        template.setTransmissionContent(data);
        return template;
    }
}
