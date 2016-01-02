package com.hengsu.sure.core.service;


import com.hengsu.sure.core.model.ConfModel;

import java.util.List;

/**
 * Created by haiquan.li on 2015/8/24.
 * 2015/8/24 17:20
 */
public interface ConfService {

    public int deleteByPrimaryKey(String confKey);

    public long insert(ConfModel confModel);

    public ConfModel selectByPrimaryKey(String confKey);

    public int updateByPrimaryKey(ConfModel confModel);

    public Double getDouble(String confKey);
    public String getString(String confKey);

    public List<ConfModel> selectByKeyPrefix(String confKeyPrefix);
}
