package com.hengsu.sure.invite.repository;

import com.hengsu.sure.invite.entity.Goods;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("goods") Goods goods);

    int insertSelective(@Param("goods") Goods goods);

    Goods selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("goods") Goods goods);

    int updateByPrimaryKey(@Param("goods") Goods goods);

    int selectCount(@Param("goods") Goods goods);

    List<Goods> selectPage(@Param("goods") Goods goods, @Param("pageable") Pageable pageable);
}