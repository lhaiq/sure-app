package com.hengsu.sure.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.core.service.PushService;
import com.hengsu.sure.invite.model.InvitationModel;
import com.hengsu.sure.invite.service.InvitationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by haiquanli on 15/12/18.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean(UserService.class);
        InvitationService invitationService = context.getBean(InvitationService.class);
        PushService pushService = context.getBean(PushService.class);

        InvitationModel invitationModel = invitationService.findByPrimaryKey(7L);
        UserModel userModel = userService.findByPrimaryKey(3L);

        userModel.setClientId("61db9f8f092482bf77592740ed5eb2ee");
        JSONObject message = new JSONObject();
        message.put("pushId", "invitation_request");
        message.put("request", invitationModel);
        message.put("user", userModel);
        message.put("count", 1);
        pushService.pushMessage(message.toJSONString(), userModel);

    }
}
