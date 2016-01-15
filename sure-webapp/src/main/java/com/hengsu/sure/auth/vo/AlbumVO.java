package com.hengsu.sure.auth.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;
import java.util.List;

@MapClass("com.hengsu.sure.auth.model.AlbumModel")
public class AlbumVO{
	
	private List<String> imageIds;

	public void setImageIds(List<String> imageIds) {
		this.imageIds = imageIds;
	}

	public List<String> getImageIds() {
		return imageIds;
	}
}