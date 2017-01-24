package com.tuyu.sharding.mappper;

import com.tuyu.sharding.entity.ActionLog;
import com.tuyu.sharding.util.MyMapper;

/**
 * Created by tuyu on 1/12/17.
 */
public interface ActionLogMapper extends MyMapper<ActionLog>{
    void insertWithId(ActionLog model);
}
