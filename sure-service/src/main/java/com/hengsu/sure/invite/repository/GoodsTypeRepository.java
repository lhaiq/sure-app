package com.hengsu.sure.invite.repository;

import com.hengsu.sure.invite.entity.GoodsType;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsTypeRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("goodstype") GoodsType goodstype);

    int insertSelective(@Param("goodstype") GoodsType goodstype);

    GoodsType selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("goodstype") GoodsType goodstype);

    int updateByPrimaryKey(@Param("goodstype") GoodsType goodstype);

    int selectCount(@Param("goodstype") GoodsType goodstype);

    List<GoodsType> selectPage(@Param("goodstype") GoodsType goodstype, @Param("pageable") Pageable pageable);
}