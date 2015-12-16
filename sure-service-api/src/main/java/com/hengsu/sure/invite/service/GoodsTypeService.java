
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.GoodsTypeModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GoodsTypeService{
	
	public int create(GoodsTypeModel goodsTypeModel);
	
	public int createSelective(GoodsTypeModel goodsTypeModel);
	
	public GoodsTypeModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(GoodsTypeModel goodsTypeModel);
	
	public int updateByPrimaryKeySelective(GoodsTypeModel goodsTypeModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(GoodsTypeModel goodsTypeModel);

	public List<GoodsTypeModel> selectPage(GoodsTypeModel goodsTypeModel,Pageable pageable);
	
}