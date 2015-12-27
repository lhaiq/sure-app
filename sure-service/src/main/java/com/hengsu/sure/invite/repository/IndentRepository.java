package com.hengsu.sure.invite.repository;

import com.hengsu.sure.invite.entity.Indent;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IndentRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("indent") Indent indent);

    int insertSelective(@Param("indent") Indent indent);

    Indent selectByPrimaryKey(@Param("id") Long id);

    Indent selectByNO(@Param("indentNo") String indentNo);

    int updateByPrimaryKeySelective(@Param("indent") Indent indent);

    int updateByPrimaryKey(@Param("indent") Indent indent);

    int selectCount(@Param("indent") Indent indent);

    List<Indent> selectPage(@Param("indent") Indent indent, @Param("pageable") Pageable pageable);

    List<Indent> selectFinishing(@Param("status")Integer status,@Param("now")Date now);
}