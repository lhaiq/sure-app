package com.hengsu.sure.auth.repository;

import com.hengsu.sure.auth.entity.User;

import java.util.List;
import java.util.Map;

import com.hengsu.sure.auth.model.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("user") User user);

    int insertSelective(@Param("user") User user);

    User selectByPrimaryKey(@Param("id") Long id);

    Double selectBalanceById(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("user") User user);

    int updateByPrimaryKey(@Param("user") User user);

    int selectCount(@Param("user") User user);

    List<User> selectPage(@Param("user") User user, @Param("pageable") Pageable pageable);

    List<User> queryNearUserWithRole(@Param("param") Map<String, Object> param, @Param("pageable") Pageable pageable);

    List<User> queryNearUser(@Param("param") Map<String, Object> param, @Param("pageable") Pageable pageable);

    User findUserByPhone(String phone);

    User findUserByFaceId(String faceId);

    User findUserByClientId(String clientId);

    void updateClientNull(Long userId);
}