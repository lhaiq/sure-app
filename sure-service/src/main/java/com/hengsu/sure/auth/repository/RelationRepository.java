package com.hengsu.sure.auth.repository;

import com.hengsu.sure.auth.entity.Relation;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("relation") Relation relation);

    int insertSelective(@Param("relation") Relation relation);

    Relation selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("relation") Relation relation);

    int updateByPrimaryKey(@Param("relation") Relation relation);

    int selectCount(@Param("relation") Relation relation);

    List<Relation> selectPage(@Param("relation") Relation relation, @Param("pageable") Pageable pageable);
}