package com.hengsu.sure.invite.repository;

import com.hengsu.sure.invite.entity.IndentComment;
import java.util.List;

import com.hengsu.sure.invite.entity.IndentCommentScore;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IndentCommentRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("indentcomment") IndentComment indentcomment);

    int insertSelective(@Param("indentcomment") IndentComment indentcomment);

    IndentComment selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("indentcomment") IndentComment indentcomment);

    int updateByPrimaryKey(@Param("indentcomment") IndentComment indentcomment);

    int selectCount(@Param("indentcomment") IndentComment indentcomment);

    List<IndentComment> selectPage(@Param("indentcomment") IndentComment indentcomment, @Param("pageable") Pageable pageable);

    List<IndentCommentScore> selectScore();
}