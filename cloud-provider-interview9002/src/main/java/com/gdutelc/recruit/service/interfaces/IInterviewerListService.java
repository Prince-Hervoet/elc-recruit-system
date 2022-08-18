package com.gdutelc.recruit.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdutelc.recruit.domain.entities.InterviewerList;
import com.gdutelc.recruit.domain.vo.ResultVO;

/**
 * <p>
 * 面试官列表 服务类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
public interface IInterviewerListService extends IService<InterviewerList> {

    /**
     * 面试官登录业务
     *
     * @param username 面试官用户名
     * @param password 密码
     * @param sessionId 请求Session ID
     * @return {@link ResultVO}，其中数据为该面试官所在的部门
     */
    ResultVO<Integer> login(String username, String password, String sessionId);

}
