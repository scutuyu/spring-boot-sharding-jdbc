package com.tuyu.sharding.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by tuyu on 1/12/17.
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
