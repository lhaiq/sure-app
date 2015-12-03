package com.hengsu.sure.invite.repository;

import com.hengsu.sure.invite.entity.City;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("city") City city);

    int insertSelective(@Param("city") City city);

    City selectByPrimaryKey(@Param("id") Long id);

    City selectByName(@Param("name") String name);

    int updateByPrimaryKeySelective(@Param("city") City city);

    int updateByPrimaryKey(@Param("city") City city);

    int selectCount(@Param("city") City city);

    List<City> selectProvinces();

    List<City> selectPage(@Param("city") City city, @Param("pageable") Pageable pageable);
}