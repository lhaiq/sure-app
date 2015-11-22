
package com.hengsu.sure.sns.service;

import com.hengsu.sure.sns.model.MTimeModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MTimeService {

    public int create(MTimeModel mTimeModel);

    public int createSelective(MTimeModel mTimeModel);

    public MTimeModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(MTimeModel mTimeModel);

    public int updateByPrimaryKeySelective(MTimeModel mTimeModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(MTimeModel mTimeModel);

    public void deleteMTime(Long id, Long userId);

    public List<MTimeModel> listMTimeModels(MTimeModel mTimeModel,Pageable pageable);


}