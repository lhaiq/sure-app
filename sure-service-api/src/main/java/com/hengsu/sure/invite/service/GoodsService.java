
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.GoodsModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface GoodsService{
	
	public int create(GoodsModel goodsModel);
	
	public int createSelective(GoodsModel goodsModel);
	
	public GoodsModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(GoodsModel goodsModel);
	
	public int updateByPrimaryKeySelective(GoodsModel goodsModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(GoodsModel goodsModel);

	public List<GoodsModel> selectPage(GoodsModel goodsModel,Pageable pageable);
	
}