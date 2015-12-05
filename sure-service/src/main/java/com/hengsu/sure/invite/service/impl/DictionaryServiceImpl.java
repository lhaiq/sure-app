package com.hengsu.sure.invite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Dictionary;
import com.hengsu.sure.invite.repository.DictionaryRepository;
import com.hengsu.sure.invite.model.DictionaryModel;
import com.hengsu.sure.invite.service.DictionaryService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private DictionaryRepository dictionaryRepo;

    @Transactional
    @Override
    public int create(DictionaryModel dictionaryModel) {
        return dictionaryRepo.insert(beanMapper.map(dictionaryModel, Dictionary.class));
    }

    @Transactional
    @Override
    public int createSelective(DictionaryModel dictionaryModel) {
        return dictionaryRepo.insertSelective(beanMapper.map(dictionaryModel, Dictionary.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return dictionaryRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public DictionaryModel findByPrimaryKey(Long id) {
        Dictionary dictionary = dictionaryRepo.selectByPrimaryKey(id);
        return beanMapper.map(dictionary, DictionaryModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(DictionaryModel dictionaryModel) {
        return dictionaryRepo.selectCount(beanMapper.map(dictionaryModel, Dictionary.class));
    }

    @Override
    public List<DictionaryModel> selectPage(DictionaryModel dictionaryModel) {
        Dictionary dictionary = beanMapper.map(dictionaryModel, Dictionary.class);
        List<Dictionary> dictionaries = dictionaryRepo.selectPage(dictionary,
                new PageRequest(0, Integer.MAX_VALUE));
        return beanMapper.mapAsList(dictionaries, DictionaryModel.class);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(DictionaryModel dictionaryModel) {
        return dictionaryRepo.updateByPrimaryKey(beanMapper.map(dictionaryModel, Dictionary.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(DictionaryModel dictionaryModel) {
        return dictionaryRepo.updateByPrimaryKeySelective(beanMapper.map(dictionaryModel, Dictionary.class));
    }

}
