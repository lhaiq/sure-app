
package com.hengsu.sure.auth.service;

import com.hengsu.sure.auth.model.AlbumModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface AlbumService {

    public int create(AlbumModel albumModel);

    public int createSelective(AlbumModel albumModel);

    public AlbumModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(AlbumModel albumModel);

    public int updateByPrimaryKeySelective(AlbumModel albumModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(AlbumModel albumModel);

    public void deleteAlbum(Long id, Long userId);

    public List<AlbumModel> selectPage(AlbumModel albumModel, Pageable pageable);

}