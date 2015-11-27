package com.hengsu.sure.auth.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;
import java.util.List;

@MapClass("com.hengsu.sure.auth.model.AlbumModel")
public class AlbumVO{
	
	private List<Long> imageIds;

	public void setImageIds(List<Long> imageIds) {
		this.imageIds = imageIds;
	}

	public List<Long> getImageIds() {
		return imageIds;
	}
}