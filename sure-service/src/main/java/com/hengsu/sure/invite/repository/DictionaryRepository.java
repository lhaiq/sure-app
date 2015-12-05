package com.hengsu.sure.invite.repository;

import com.hengsu.sure.invite.entity.Dictionary;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("dictionary") Dictionary dictionary);

    int insertSelective(@Param("dictionary") Dictionary dictionary);

    Dictionary selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("dictionary") Dictionary dictionary);

    int updateByPrimaryKey(@Param("dictionary") Dictionary dictionary);

    int selectCount(@Param("dictionary") Dictionary dictionary);

    List<Dictionary> selectPage(@Param("dictionary") Dictionary dictionary, @Param("pageable") Pageable pageable);
}