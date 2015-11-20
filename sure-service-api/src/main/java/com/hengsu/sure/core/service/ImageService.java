
package com.hengsu.sure.core.service;

import com.hengsu.sure.core.model.ImageModel;
import java.util.Date;

public interface ImageService{
	
	public int create(ImageModel imageModel);
	
	public int createSelective(ImageModel imageModel);
	
	public ImageModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ImageModel imageModel);
	
	public int updateByPrimaryKeySelective(ImageModel imageModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(ImageModel imageModel);
	
}