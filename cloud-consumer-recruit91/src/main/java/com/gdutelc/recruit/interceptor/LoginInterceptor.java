package com.gdutelc.recruit.interceptor;

import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.exception.LoginException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录Session检查
 *
 * @author gregPerlinLi
 * @date 2022-08-16
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    IInterviewService interviewService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = (String) request.getSession().getAttribute("username");
        String sessionId = request.getSession().getId();
        if ( username != null ) {
            ResultVO<String> result = interviewService.loginVerify(username, sessionId);
            if ( result.getCode() == ResultStatusCodeConstant.SUCCESS ) {
                log.info("登录校验成功");
                return true;
            }
            log.info("登录校验失败，Redis中没有记录此用户的Session");
            throw new LoginException(ResultStatusCodeConstant.FAILED, "登录校验失败，Redis中没有记录此用户的Session");
        }
        log.info("登录校验失败，Session中没有用户");
        throw new LoginException(ResultStatusCodeConstant.FAILED, "登录校验失败，Session中没有用户");
    }
}
