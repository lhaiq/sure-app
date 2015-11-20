
package com.hengsu.sure.sns.service;

import com.hengsu.sure.sns.model.MTimeModel;

public interface MTimeService{
	
	public int create(MTimeModel mTimeModel);
	
	public int createSelective(MTimeModel mTimeModel);
	
	public MTimeModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(MTimeModel mTimeModel);
	
	public int updateByPrimaryKeySelective(MTimeModel mTimeModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(MTimeModel mTimeModel);
	
}