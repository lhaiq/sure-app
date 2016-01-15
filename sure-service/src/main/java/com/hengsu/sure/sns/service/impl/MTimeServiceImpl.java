package com.hengsu.sure.sns.service.impl;

import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.core.service.ImageService;
import com.hengsu.sure.sns.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.sns.entity.MTime;
import com.hengsu.sure.sns.repository.MTimeRepository;
import com.hengsu.sure.sns.model.MTimeModel;
import com.hengsu.sure.sns.service.MTimeService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MTimeServiceImpl implements MTimeService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private MTimeRepository mTimeRepo;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @Transactional
    @Override
    public int create(MTimeModel mTimeModel) {
        return mTimeRepo.insert(beanMapper.map(mTimeModel, MTime.class));
    }

    @Transactional
    @Override
    public int createSelective(MTimeModel mTimeModel) {
        return mTimeRepo.insertSelective(beanMapper.map(mTimeModel, MTime.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return mTimeRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public MTimeModel findByPrimaryKey(Long id) {
        MTime mTime = mTimeRepo.selectByPrimaryKey(id);
        MTimeModel mTimeModel = beanMapper.map(mTime, MTimeModel.class);
        return mTimeModel;
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(MTimeModel mTimeModel) {
        return mTimeRepo.selectCount(beanMapper.map(mTimeModel, MTime.class));
    }

    @Transactional
    @Override
    public void deleteMTime(Long id, Long userId) {

        //先查出相关信息
        MTimeModel mTimeModel = findByPrimaryKey(id);

        //只能删除自己的时光
        if (userId != mTimeModel.getUserId()) {
            ErrorCode.throwBusinessException(ErrorCode.MTIME_DELETE_ERROR);
        }

        //删除评论
        commentService.deleteCommentByMid(id);

        //删除图片
//        List<Long> imageIds = mTimeModel.getImageIds();
//        for (Long imageId : imageIds) {
//            imageService.deleteByPrimaryKey(imageId);
//        }

        //删除自身
        deleteByPrimaryKey(id);

    }

    @Override
    public List<MTimeModel> listMTimeModels(MTimeModel mTimeModel, Pageable pageable) {
        List<MTime> mTimes = mTimeRepo.selectPage(beanMapper.map(mTimeModel, MTime.class), pageable);
        List<MTimeModel> mTimeModels = beanMapper.mapAsList(mTimes, MTimeModel.class);
        return mTimeModels;
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(MTimeModel mTimeModel) {
        return mTimeRepo.updateByPrimaryKey(beanMapper.map(mTimeModel, MTime.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(MTimeModel mTimeModel) {
        return mTimeRepo.updateByPrimaryKeySelective(beanMapper.map(mTimeModel, MTime.class));
    }
}
