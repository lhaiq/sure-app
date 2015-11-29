package com.hengsu.sure.invite.repository;

import com.hengsu.sure.invite.entity.Rent;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("rent") Rent rent);

    int insertSelective(@Param("rent") Rent rent);

    Rent selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("rent") Rent rent);

    int updateByPrimaryKey(@Param("rent") Rent rent);

    int selectCount(@Param("rent") Rent rent);

    List<Rent> selectPage(@Param("rent") Rent rent, @Param("pageable") Pageable pageable);
}