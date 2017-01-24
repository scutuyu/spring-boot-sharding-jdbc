package com.tuyu.sharding;

import com.tuyu.sharding.entity.ActionLog;
import com.tuyu.sharding.mappper.ActionLogMapper;
import com.tuyu.sharding.repository.ActionLogRepository;
import com.tuyu.sharding.service.ActionLogService;
import com.tuyu.sharding.sql.SqlMapper;
import com.tuyu.sharding.util.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@SpringBootApplication
//@EnableScheduling
//@MapperScan(basePackages = "com.tuyu.sharding.mapper",markerInterface = MyMapper.class)
public class SpringBootShardingJdbcApplication implements CommandLineRunner{


	@Autowired
	ActionLogService actionLogService;

	@Autowired
	SqlMapper sqlMapper;

	@Autowired
	ActionLogRepository actionLogRepository;

	@Autowired
			@Qualifier("acDataSource")
	DataSource dataSource;

//	@Autowired
//	ActionLogMapper actionLogMapper;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootShardingJdbcApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		//查询逻辑表数据——测试通过
//		actionLogService.selectAll();
		//根据时间和租户Id查找所有行为日志
//		List<ActionLog> list = sqlMapper.selectAll("20161226","20161227","30");
//		for (ActionLog actionLog : list){
//			System.out.println(actionLog);
//		}
		//根据时间、租户Id进行serv排行,需要自己合并，排序
//		List<Map<String,Object>> sorvSortlist = sqlMapper.servSortEnhance("20161226","20161227","30");
//		Map<String,Integer> servSortMap = formateServOrApp(sorvSortlist,"serv");
//		List<Map.Entry<String,Integer>> sortList = new ArrayList<Map.Entry<String,Integer>>(servSortMap.entrySet());
//		Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {
//			@Override
//			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//				return o2.getValue().compareTo(o1.getValue());
//			}
//		});
//		int i = 0 ;
//		Map<String,Object> returnMap = new LinkedHashMap<String ,Object>();
//		for (Map.Entry<String,Integer> entry : sortList){
//			if (i<5){
//				returnMap.put(entry.getKey(),entry.getValue());
//				i++;
//			}
//		}
//		System.out.println(returnMap);
		//根据时间、租户Id对于流量的serv进行排行
//		List<Map<String,Object>> fluxServSortList = sqlMapper.fluxServSort("20161226","20161227","30");
//		for(Map<String,Object> map : fluxServSortList){
//			System.out.println(map);
//		}
		//用户排行
//		List<Map<String,Object>> userNameSortList = sqlMapper.userNameSort("20161226","20161227","30");
//		System.out.println(formatUserNameSort(userNameSortList, 9));

		//向逻辑表插入数据——测试成功
		ActionLog actionLog = new ActionLog();
		actionLog.setId(44444l);
		actionLog.setDate("20161226");
		actionLog.setName("17");
		actionLogRepository.insertWithId(actionLog);
		//查询ac网关源数据——测试成功
//		String sql = "select *  from 20161226_action limit 2";
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
//		for (Map<String,Object> map : list){
//			System.out.println("user=" + map.get("user").toString());
//			System.out.println("record_id=" + map.get("record_id").toString());
//		}

		//定时任务——测试成功
		//MyTask是定时任务
	}

	public Map<String,Integer> formateServOrApp(List<Map<String,Object>> originList,String servOrApp){
		if (originList == null || originList.size() == 0)
			return null;
		Map<String,Integer> resultMap = new HashMap<String,Integer>();
		Map<String,Object> originMap = new HashMap<String,Object>();
		for (Map<String,Object> map : originList){
			String name = map.get(servOrApp).toString();
			String user = map.get("name").toString();
			if (originMap.containsKey(name + "_" + user)){

			}else {
				originMap.put(name + "_" + user,map.get("name"));
				if (resultMap.containsKey(name)){
					resultMap.put(name,resultMap.get(name) + 1);
				}else {
					resultMap.put(name,1);
				}
			}
		}
		return resultMap;
	}

	public static Map<String,Object> formatUserNameSort(List<Map<String,Object>> originList,int topx){
		if (originList == null || originList.size() == 0)
			return null;
		Map<String,Integer> resultMap = new HashMap<String,Integer>();
		for (Map<String,Object> map : originList){
			String name = map.get("name").toString();
			int num = Integer.parseInt(map.get("num").toString());
			if (resultMap.containsKey(name)){
				resultMap.put(name,resultMap.get(name) + num);
			}else {
				resultMap.put(name,num);
			}
		}
		List<Map.Entry<String,Integer>> resultList = new ArrayList<Map.Entry<String,Integer>>(resultMap.entrySet());
		Collections.sort(resultList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				int returnValue =  o2.getValue().compareTo(o1.getValue());
				//当map的value相等时就比较key，以key的升序排列
				if (returnValue == 0){
					return o1.getKey().compareTo(o2.getKey());
				}else {
					return returnValue;
				}
			}
		});
		Map<String,Object> map  = new LinkedHashMap<String,Object>();
		for (int i = 0 ; i < topx ; i++){
			map.put(resultList.get(i).getKey(),resultList.get(i).getValue());
		}
		return map;
	}
}
