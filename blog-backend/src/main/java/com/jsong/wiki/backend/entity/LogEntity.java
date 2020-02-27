package com.jsong.wiki.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "log")
public class LogEntity {
    private int id;
    private Integer userId;
    private String userName;
    private String operate;
    private Integer operateTime;
    private String ip;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "operate")
    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    @Basic
    @Column(name = "operate_time")
    public Integer getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Integer operateTime) {
        this.operateTime = operateTime;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntity logEntity = (LogEntity) o;
        return id == logEntity.id &&
                Objects.equals(userId, logEntity.userId) &&
                Objects.equals(userName, logEntity.userName) &&
                Objects.equals(operate, logEntity.operate) &&
                Objects.equals(operateTime, logEntity.operateTime) &&
                Objects.equals(ip, logEntity.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userName, operate, operateTime, ip);
    }
}
