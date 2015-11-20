package com.hengsu.sure.auth.interceptor;

import com.hengsu.sure.auth.annotation.IgnoreAuth;
import com.hengsu.sure.core.Context;
import com.hengsu.sure.core.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by haiquanli on 15/11/20.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("authContext")
    private Context<String, Long> authContext;

    private final static String AUTHORIZATION = "Authorization";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean isIgnore = checkIgnore(handler);

        if (!isIgnore) {
            //取得auth token
            String authToken = request.getHeader(AUTHORIZATION);
            if (StringUtils.isEmpty(authToken)) {
                ErrorCode.throwBusinessException(ErrorCode.AUTH_TOKEN_MUST);
            }

            Long userId = authContext.get(authToken);
            if (null == userId) {
                ErrorCode.throwBusinessException(ErrorCode.AUTH_TOKEN_INVALID);
            }

            //将UserId设置到request里面
            request.setAttribute("userId", userId);
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean checkIgnore(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IgnoreAuth ignoreAuth = handlerMethod.getMethodAnnotation(IgnoreAuth.class);
        if (null != ignoreAuth) {
            return true;
        }

        return false;
    }
}
