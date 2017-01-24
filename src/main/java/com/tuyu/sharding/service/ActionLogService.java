package com.tuyu.sharding.service;

import com.tuyu.sharding.entity.ActionLog;
import com.tuyu.sharding.mappper.ActionLogMapper;
import com.tuyu.sharding.repository.ActionLogRepository;
import com.tuyu.sharding.sql.SqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tuyu on 1/11/17.
 */
@Service
@Transactional
public class ActionLogService {

//    @Autowired
//    ActionLogRepository actionLogRepository;
//
//    @Transactional(readOnly = true)
//    public void selectByDate(){
//        System.out.println(actionLogRepository.selectByDate("hello"));
//    }
    @Autowired
    SqlMapper sqlMapper;

//    @Autowired
//    ActionLogMapper actionLogMapper;

    public void selectAll(){
//        System.out.println(sqlMapper.selectAll());
    }

    public void saveActionLog(){
        ActionLog actionLog = new ActionLog();
        actionLog.setId(2l);
        actionLog.setDate("20171226");
        actionLog.setName("TUTUTUTU");
        sqlMapper.saveUserActionLog(actionLog.getId(),actionLog.getDate(),actionLog.getName());
    }

//    public void saveActionLog(ActionLog actionLog){
//        if (actionLog.getId() != null){
//            actionLogMapper.updateByPrimaryKey(actionLog);
//        }else {
//            actionLogMapper.insert(actionLog);
//        }
//    }
}
