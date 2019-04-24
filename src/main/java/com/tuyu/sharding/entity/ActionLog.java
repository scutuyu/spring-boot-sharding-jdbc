package com.tuyu.sharding.entity;

import lombok.Data;

/**
 * Created by tuyu on 1/11/17.
 */
@Data
public class ActionLog {

    private Long id;

    private String date;

    private Integer group_id = 30;

    private Long up_flux;

    private Long down_flux;

    private Integer groupId;

    private String name;

    private String serv;

    private String app;

}
