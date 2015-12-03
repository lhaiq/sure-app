
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.CityModel;

import java.util.List;

public interface CityService {

    public int create(CityModel cityModel);

    public int createSelective(CityModel cityModel);

    public CityModel findByPrimaryKey(Long id);

    public CityModel findByName(String name);

    public int updateByPrimaryKey(CityModel cityModel);

    public int updateByPrimaryKeySelective(CityModel cityModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(CityModel cityModel);

    public List<CityModel> selectProvinces();

    public List<CityModel> selectPage(CityModel cityModel);

}