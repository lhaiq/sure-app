package com.hengsu.sure.invite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.City;
import com.hengsu.sure.invite.repository.CityRepository;
import com.hengsu.sure.invite.model.CityModel;
import com.hengsu.sure.invite.service.CityService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CityRepository cityRepo;

    @Transactional
    @Override
    public int create(CityModel cityModel) {
        return cityRepo.insert(beanMapper.map(cityModel, City.class));
    }

    @Transactional
    @Override
    public int createSelective(CityModel cityModel) {
        return cityRepo.insertSelective(beanMapper.map(cityModel, City.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return cityRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public CityModel findByPrimaryKey(Long id) {
        City city = cityRepo.selectByPrimaryKey(id);
        return beanMapper.map(city, CityModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public CityModel findByName(String name) {
        City city = cityRepo.selectByName(name);
        return beanMapper.map(city, CityModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(CityModel cityModel) {
        return cityRepo.selectCount(beanMapper.map(cityModel, City.class));
    }

    @Override
    public List<CityModel> selectProvinces() {
        List<City> cities = cityRepo.selectProvinces();
        return beanMapper.mapAsList(cities, CityModel.class);
    }

    @Override
    public List<CityModel> selectPage(CityModel cityModel) {
        City city = beanMapper.map(cityModel, City.class);
        List<City> cities = cityRepo.selectPage(city, new PageRequest(0, Integer.MAX_VALUE));
        return beanMapper.mapAsList(cities, CityModel.class);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(CityModel cityModel) {
        return cityRepo.updateByPrimaryKey(beanMapper.map(cityModel, City.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(CityModel cityModel) {
        return cityRepo.updateByPrimaryKeySelective(beanMapper.map(cityModel, City.class));
    }

}
