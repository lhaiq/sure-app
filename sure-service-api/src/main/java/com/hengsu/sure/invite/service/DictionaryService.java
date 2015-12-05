
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.DictionaryModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DictionaryService {

    public int create(DictionaryModel dictionaryModel);

    public int createSelective(DictionaryModel dictionaryModel);

    public DictionaryModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(DictionaryModel dictionaryModel);

    public int updateByPrimaryKeySelective(DictionaryModel dictionaryModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(DictionaryModel dictionaryModel);

    public List<DictionaryModel> selectPage(DictionaryModel dictionaryModel);


}