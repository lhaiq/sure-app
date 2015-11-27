package com.hengsu.sure.auth.repository;

import com.hengsu.sure.auth.entity.Album;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("album") Album album);

    int insertSelective(@Param("album") Album album);

    Album selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("album") Album album);

    int updateByPrimaryKey(@Param("album") Album album);

    int selectCount(@Param("album") Album album);

    List<Album> selectPage(@Param("album") Album album, @Param("pageable") Pageable pageable);
}