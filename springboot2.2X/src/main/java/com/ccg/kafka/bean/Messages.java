package com.ccg.kafka.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *@className Messages
 *@Description
 *@Author chungaochen
 *Date 2020/4/30 18:39
 *Version 1.0
 **/
//public class Messages implements Serializable {//kafka配置已指定序列化和反序列化方式
//private static final long serialVersionUID = 8157091047565518557L;
public class Messages  {


    private Long id;

    private String msg;

    private Date sendTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}