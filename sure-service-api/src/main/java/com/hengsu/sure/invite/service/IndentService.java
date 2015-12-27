
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.CancelIndentModel;
import com.hengsu.sure.invite.model.IndentModel;
import com.hengsu.sure.invite.model.TradeModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IndentService {

    public int create(IndentModel indentModel);

    public int createSelective(IndentModel indentModel);

    public IndentModel findByPrimaryKey(Long id);

    public IndentModel findByNo(String no);

    public int updateByPrimaryKey(IndentModel indentModel);

    public int updateByPrimaryKeySelective(IndentModel indentModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(IndentModel indentModel);

    public void receiveTrade(TradeModel tradeModel);

    public void cancelIndent(Long id, Long userId);

    public CancelIndentModel prepareCancelIdent(Long id,Long userId);

    public List<IndentModel> selectPage(IndentModel indentModel, Pageable pageable);

}