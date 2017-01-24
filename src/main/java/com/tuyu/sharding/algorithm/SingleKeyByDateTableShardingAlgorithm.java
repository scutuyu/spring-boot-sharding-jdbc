package com.tuyu.sharding.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import com.tuyu.sharding.util.DateUtil;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * Created by tuyu on 1/11/17.
 */
public class SingleKeyByDateTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {
    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        return "user_action_log_" + shardingValue.getValue();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        return null;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>();
        Range range = shardingValue.getValueRange();
        String beginDateStr =  "" + range.lowerEndpoint();
        String endDateStr = "" + range.upperEndpoint();
        getAllDate(result,beginDateStr,endDateStr);
        return result;
    }

    public void getAllDate(Collection<String> result,String beginDateStr,String endDateStr){
        Date beginDate = DateUtil.stringToDate(beginDateStr,"YYYYMMdd");
        Date endDate = DateUtil.stringToDate(endDateStr,"YYYYMMdd");
        //调用daysBetweenDates函数计算两个日期之间的天数时，第一个参数是较大的日期
        int days = DateUtil.daysBetweenDates(endDate,beginDate);
        for (int i = 0 ; i <= days ; i++){
            String itemDateStr = DateUtil.dateToString(DateUtil.getDateBetween(beginDate,i),"YYYYMMdd");
            result.add("user_action_log_" + itemDateStr);
        }
    }
}
