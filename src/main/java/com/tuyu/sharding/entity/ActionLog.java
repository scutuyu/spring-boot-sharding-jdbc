package com.tuyu.sharding.entity;

/**
 * Created by tuyu on 1/11/17.
 */
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




    @Override

    public String toString() {
        return "ActionLog{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", group_id=" + group_id +
                ", serv='" + serv + '\'' +
                ", app='" + app + '\'' +
                ", up_flux=" + up_flux +
                ", down_flux=" + down_flux +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", serv='" + serv + '\'' +
                ", app='" + app + '\'' +
                '}';
    }



    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setServ(String serv) {
        this.serv = serv;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public Integer getGroupId() {

        return groupId;
    }

    public String getServ() {
        return serv;
    }

    public String getApp() {
        return app;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public void setUp_flux(Long up_flux) {
        this.up_flux = up_flux;
    }

    public void setDown_flux(Long down_flux) {
        this.down_flux = down_flux;
    }

    public Long getUp_flux() {
        return up_flux;
    }

    public Long getDown_flux() {
        return down_flux;
    }

}
