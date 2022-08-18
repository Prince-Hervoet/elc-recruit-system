package com.gdutelc.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.entities.Comment;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.mapper.CommentMapper;
import com.gdutelc.recruit.service.interfaces.ICommentService;
import com.gdutelc.recruit.utils.GenericUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 对学生的评价 服务实现类
 * </p>
 *
 * @author gregPerlinLi
 * @since 2022-08-08
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public ResultVO addComment(Comment comment) throws IllegalAccessException {
        if(!GenericUtils.ofNullable(comment)||!GenericUtils.allOfNullable(comment)){
            return new ResultVO(ResultStatusCodeConstant.PARAM_VALIDATE_EXCEPTION,"参数有误",null);
        }
        int insert = commentMapper.insert(comment);
        if(insert == 1){
            return new ResultVO(ResultStatusCodeConstant.SUCCESS,"发布成功",null);
        }
        return new ResultVO(ResultStatusCodeConstant.NOT_FIND,"插入失败",null);

    }
}
