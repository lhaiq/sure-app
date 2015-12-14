package com.hengsu.sure.invite.repository;

import com.hengsu.sure.invite.entity.Statement;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("statement") Statement statement);

    int insertSelective(@Param("statement") Statement statement);

    Statement selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("statement") Statement statement);

    int updateByPrimaryKey(@Param("statement") Statement statement);

    int selectCount(@Param("statement") Statement statement);

    List<Statement> selectPage(@Param("statement") Statement statement, @Param("pageable") Pageable pageable);
}