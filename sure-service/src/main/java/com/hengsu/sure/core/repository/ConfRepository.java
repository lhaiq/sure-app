package com.hengsu.sure.core.repository;


import com.hengsu.sure.core.entity.Conf;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by allen on 7/15/15.
 */
@Repository
public interface ConfRepository {
    int deleteByPrimaryKey(@Param("confKey") String confKey);

    long insert(@Param("conf") Conf conf);

    Conf selectByPrimaryKey(@Param("confKey") String confKey);

    int updateByPrimaryKey(@Param("conf") Conf conf);

    List<Conf> selectByKeyPrefix(@Param("confKeyPrefix") String confKeyPrefix);
}