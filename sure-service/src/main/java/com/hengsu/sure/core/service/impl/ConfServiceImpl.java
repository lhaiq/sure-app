package com.hengsu.sure.core.service.impl;


import com.hengsu.sure.core.entity.Conf;
import com.hengsu.sure.core.model.ConfModel;
import com.hengsu.sure.core.repository.ConfRepository;
import com.hengsu.sure.core.service.ConfService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConfServiceImpl implements ConfService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ConfRepository confRepository;

    @Transactional
    @Override
    public int deleteByPrimaryKey(String confKey) {
        return confRepository.deleteByPrimaryKey(confKey);
    }

    @Transactional
    @Override
    public long insert(ConfModel confModel) {
        return confRepository.insert(beanMapper.map(confModel, Conf.class));
    }

    @Override
    public ConfModel selectByPrimaryKey(String confKey) {
        Conf conf = confRepository.selectByPrimaryKey(confKey);
        return beanMapper.map(conf,ConfModel.class);
    }

    @Override
    public int updateByPrimaryKey(ConfModel confModel) {
        Conf conf = beanMapper.map(confModel,Conf.class);
        return confRepository.updateByPrimaryKey(conf);
    }

    @Override
    public Double getDouble(String confKey) {
        ConfModel confModel = selectByPrimaryKey(confKey);
        return Double.parseDouble(confModel.getConfValue());
    }

    @Override
    public String getString(String confKey) {
        return selectByPrimaryKey(confKey).getConfValue();
    }

    @Override
    public List<ConfModel> selectByKeyPrefix(String confKeyPrefix) {
        List<Conf> confs = confRepository.selectByKeyPrefix(confKeyPrefix);
        return beanMapper.mapAsList(confs,ConfModel.class);
    }
}
