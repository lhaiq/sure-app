package com.hengsu.sure.auth.service.impl;

import com.hengsu.sure.core.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.auth.entity.Album;
import com.hengsu.sure.auth.repository.AlbumRepository;
import com.hengsu.sure.auth.model.AlbumModel;
import com.hengsu.sure.auth.service.AlbumService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private AlbumRepository albumRepo;

    @Transactional
    @Override
    public int create(AlbumModel albumModel) {
        return albumRepo.insert(beanMapper.map(albumModel, Album.class));
    }

    @Transactional
    @Override
    public int createSelective(AlbumModel albumModel) {
        Album album = beanMapper.map(albumModel, Album.class);
        int ret = albumRepo.insertSelective(album);
        albumModel.setId(album.getId());
        return ret;
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return albumRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public AlbumModel findByPrimaryKey(Long id) {
        Album album = albumRepo.selectByPrimaryKey(id);
        return beanMapper.map(album, AlbumModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(AlbumModel albumModel) {
        return albumRepo.selectCount(beanMapper.map(albumModel, Album.class));
    }

    @Transactional
    @Override
    public void deleteAlbum(Long id, Long userId) {

        //判断是否是自己的照片
        AlbumModel albumModel = findByPrimaryKey(id);
        if (albumModel.getUserId() != userId) {
            ErrorCode.throwBusinessException(ErrorCode.DELETE_ONLY_SELF);
        }

        deleteByPrimaryKey(id);

    }

    @Override
    public List<AlbumModel> selectPage(AlbumModel albumModel, Pageable pageable) {
        List<Album> albums = albumRepo.selectPage(beanMapper.map(albumModel, Album.class), pageable);
        return beanMapper.mapAsList(albums, AlbumModel.class);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(AlbumModel albumModel) {
        return albumRepo.updateByPrimaryKey(beanMapper.map(albumModel, Album.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(AlbumModel albumModel) {
        return albumRepo.updateByPrimaryKeySelective(beanMapper.map(albumModel, Album.class));
    }

}
