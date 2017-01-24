package com.tuyu.sharding.repository;

import com.tuyu.sharding.entity.ActionLog;

import java.util.List;

/**
 * Created by tuyu on 1/11/17.
 */
public interface ActionLogRepository {

    List<ActionLog> selectByDate(String hello);

    void insertWithId(ActionLog model);
}
