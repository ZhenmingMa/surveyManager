package com.cby.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Ma on 2017/8/4.
 */
@Entity
public class AppInfo {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer systemInfoId;
    private String appName;
    private String appVersion;
    private String packageName;
    private String Status;
    private Date time;

    public AppInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSystemInfoId() {
        return systemInfoId;
    }

    public void setSystemInfoId(Integer systemInfoId) {
        this.systemInfoId = systemInfoId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
