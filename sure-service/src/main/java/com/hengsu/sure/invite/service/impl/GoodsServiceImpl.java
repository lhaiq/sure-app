package com.hengsu.sure.invite.service.impl;

import com.hengsu.sure.core.Constants;
import com.hengsu.sure.sns.model.MTimeModel;
import com.hkntv.pylon.data.common.annotation.ReadWrite;
import com.hkntv.pylon.data.common.util.ReadWriteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Goods;
import com.hengsu.sure.invite.repository.GoodsRepository;
import com.hengsu.sure.invite.model.GoodsModel;
import com.hengsu.sure.invite.service.GoodsService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private GoodsRepository goodsRepo;

    @Transactional
    @Override
    public int create(GoodsModel goodsModel) {
        return goodsRepo.insert(beanMapper.map(goodsModel, Goods.class));
    }

    @Transactional
    @Override
    public int createSelective(GoodsModel goodsModel) {
        return goodsRepo.insertSelective(beanMapper.map(goodsModel, Goods.class));
    }

    @Transactional
    @Override
    @ReadWrite(type = ReadWriteType.READ)
    public int deleteByPrimaryKey(Long id) {
        return goodsRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public GoodsModel findByPrimaryKey(Long id) {
        Goods goods = goodsRepo.selectByPrimaryKey(id);
        return beanMapper.map(goods, GoodsModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(GoodsModel goodsModel) {
        return goodsRepo.selectCount(beanMapper.map(goodsModel, Goods.class));
    }

    @Override
    public List<GoodsModel> selectPage(GoodsModel goodsModel, Pageable pageable) {
        Goods goods = beanMapper.map(goodsModel, Goods.class);
        List<Goods> goodses = goodsRepo.selectPage(goods, pageable);
        List<GoodsModel> goodsModels = beanMapper.mapAsList(goodses, GoodsModel.class);
//        for (GoodsModel goodsModel1 : goodsModels) {
//            setImageIds(goodsModel1);
//        }
        return goodsModels;
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(GoodsModel goodsModel) {
        return goodsRepo.updateByPrimaryKey(beanMapper.map(goodsModel, Goods.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(GoodsModel goodsModel) {
        return goodsRepo.updateByPrimaryKeySelective(beanMapper.map(goodsModel, Goods.class));
    }


}
