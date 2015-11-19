package com.hengsu.sure.auth.repository;

import com.hengsu.sure.auth.entity.Friend;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("friend") Friend friend);

    int insertSelective(@Param("friend") Friend friend);

    Friend selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("friend") Friend friend);

    int updateByPrimaryKey(@Param("friend") Friend friend);

    int selectCount(@Param("friend") Friend friend);

    List<Friend> selectPage(@Param("friend") Friend friend, @Param("pageable") Pageable pageable);
}