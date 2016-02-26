
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.*;
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

    public int selectIndentCount(IndentModel indentModel);

    public void receiveTrade(TradeModel tradeModel);

    public CashModel cancelIndent(Long id, Long userId);

    public CancelIndentModel prepareCancelIdent(Long id,Long userId);

    public void scheduleFinishIndent();

    public void scheduleStartIndent();

    public List<IndentModel> selectPage(IndentModel indentModel, Pageable pageable);

    public List<IndentModel> selectIndent(IndentModel indentModel, Pageable pageable);

    public void commentIndent(IndentCommentModel indentCommentModel);

}