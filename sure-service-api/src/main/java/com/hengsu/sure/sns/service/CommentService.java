
package com.hengsu.sure.sns.service;

import com.hengsu.sure.sns.model.CommentModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    public int create(CommentModel commentModel);

    public Long addComment(CommentModel commentModel);

    public CommentModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(CommentModel commentModel);

    public int updateByPrimaryKeySelective(CommentModel commentModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(CommentModel commentModel);

    public void deleteCommentByMid(Long mid);

    public void cancelStatue(Long mid,Long userId);

    public void deleteComment(Long id, Long userId);

    public List<CommentModel> listComments(CommentModel commentModel,Pageable pageable);


}