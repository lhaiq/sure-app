package com.hengsu.sure.sns.service.impl;

import com.hengsu.sure.core.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.sns.entity.Comment;
import com.hengsu.sure.sns.repository.CommentRepository;
import com.hengsu.sure.sns.model.CommentModel;
import com.hengsu.sure.sns.service.CommentService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CommentRepository commentRepo;

    @Transactional
    @Override
    public int create(CommentModel commentModel) {
        return commentRepo.insert(beanMapper.map(commentModel, Comment.class));
    }

    @Transactional
    @Override
    public int createSelective(CommentModel commentModel) {
        return commentRepo.insertSelective(beanMapper.map(commentModel, Comment.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return commentRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public CommentModel findByPrimaryKey(Long id) {
        Comment comment = commentRepo.selectByPrimaryKey(id);
        return beanMapper.map(comment, CommentModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(CommentModel commentModel) {
        return commentRepo.selectCount(beanMapper.map(commentModel, Comment.class));
    }

    @Override
    public void deleteCommentByMid(Long mid) {
        commentRepo.deleteCommentByMid(mid);
    }

    @Override
    public void deleteComment(Long id, Long userId) {

        //检查是否为自己的评论或点赞
        CommentModel commentModel = findByPrimaryKey(id);
        if (commentModel.getUserid() != userId) {
            ErrorCode.throwBusinessException(ErrorCode.COMMENT_DELETE_ERROR);
        }

        deleteByPrimaryKey(id);

    }

    @Override
    public List<CommentModel> listComments(CommentModel commentModel, Pageable pageable) {
        Comment comment = beanMapper.map(commentModel, Comment.class);
        List<Comment> comments = commentRepo.selectPage(comment, pageable);
        return beanMapper.mapAsList(comments, CommentModel.class);
    }


    @Transactional
    @Override
    public int updateByPrimaryKey(CommentModel commentModel) {
        return commentRepo.updateByPrimaryKey(beanMapper.map(commentModel, Comment.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(CommentModel commentModel) {
        return commentRepo.updateByPrimaryKeySelective(beanMapper.map(commentModel, Comment.class));
    }

}
