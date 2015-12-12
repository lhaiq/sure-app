package com.hengsu.sure.invite.repository;

import com.hengsu.sure.invite.entity.Trade;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("trade") Trade trade);

    int insertSelective(@Param("trade") Trade trade);

    Trade selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("trade") Trade trade);

    int updateByPrimaryKey(@Param("trade") Trade trade);

    int selectCount(@Param("trade") Trade trade);

    List<Trade> selectPage(@Param("trade") Trade trade, @Param("pageable") Pageable pageable);
}