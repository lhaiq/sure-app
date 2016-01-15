
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.IndentCommentModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndentCommentService {

    public int create(IndentCommentModel indentCommentModel);

    public int createSelective(IndentCommentModel indentCommentModel);

    public IndentCommentModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(IndentCommentModel indentCommentModel);

    public int updateByPrimaryKeySelective(IndentCommentModel indentCommentModel);

    public int deleteByPrimaryKey(Long id);

    public void calculateScore();

    public int selectCount(IndentCommentModel indentCommentModel);

    int selectCommentCount(Long id);

    List<IndentCommentModel> selectCommentPage(Long id, Pageable pageable);


}