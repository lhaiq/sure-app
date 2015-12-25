package com.hengsu.sure.auth.repository;

import com.hengsu.sure.auth.entity.SubAccount;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubAccountRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("subaccount") SubAccount subaccount);

    int insertSelective(@Param("subaccount") SubAccount subaccount);

    SubAccount selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("subaccount") SubAccount subaccount);

    int updateByPrimaryKey(@Param("subaccount") SubAccount subaccount);

    int selectCount(@Param("subaccount") SubAccount subaccount);

    List<SubAccount> selectPage(@Param("subaccount") SubAccount subaccount, @Param("pageable") Pageable pageable);
}