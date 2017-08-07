package com.cby.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Ma on 2017/8/4.
 */
@Entity
public class SystemInfo {
    @Id
    @GeneratedValue
    private Integer id;
    private String brand; //手机品牌
    private String model; //型号
    private String systemVersion; //系统版本号
    private String systemVersionSDK; //SDK版本号
    private String CpuName;  //cpu名称
    private String cpuMaxFreq; //手机CPU最大频率
    private String CpuMinFreq; //最小频率
    private String CpuCurrentFreq; //当前频率
    private String CpuNumber; //cpu数量
    private String SN; //产品序列号
    private String UUID; //手机唯一标识符
    private String macAddress;
    private String phone;
    private String IMEICode;
    private String PhoneTelSimName;
    private String WifeName;
    private String IPAddress;
    private String location;
    private String BaiduLocation;



    private Date time;
    public SystemInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getSystemVersionSDK() {
        return systemVersionSDK;
    }

    public void setSystemVersionSDK(String systemVersionSDK) {
        this.systemVersionSDK = systemVersionSDK;
    }

    public String getCpuName() {
        return CpuName;
    }

    public void setCpuName(String cpuName) {
        CpuName = cpuName;
    }

    public String getCpuMaxFreq() {
        return cpuMaxFreq;
    }

    public void setCpuMaxFreq(String cpuMaxFreq) {
        this.cpuMaxFreq = cpuMaxFreq;
    }

    public String getCpuMinFreq() {
        return CpuMinFreq;
    }

    public void setCpuMinFreq(String cpuMinFreq) {
        CpuMinFreq = cpuMinFreq;
    }

    public String getCpuCurrentFreq() {
        return CpuCurrentFreq;
    }

    public void setCpuCurrentFreq(String cpuCurrentFreq) {
        CpuCurrentFreq = cpuCurrentFreq;
    }

    public String getCpuNumber() {
        return CpuNumber;
    }

    public void setCpuNumber(String cpuNumber) {
        CpuNumber = cpuNumber;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIMEICode() {
        return IMEICode;
    }

    public void setIMEICode(String IMEICode) {
        this.IMEICode = IMEICode;
    }

    public String getPhoneTelSimName() {
        return PhoneTelSimName;
    }

    public void setPhoneTelSimName(String phoneTelSimName) {
        PhoneTelSimName = phoneTelSimName;
    }

    public String getWifeName() {
        return WifeName;
    }

    public void setWifeName(String wifeName) {
        WifeName = wifeName;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBaiduLocation() {
        return BaiduLocation;
    }

    public void setBaiduLocation(String baiduLocation) {
        BaiduLocation = baiduLocation;
    }

    @Override
    public String toString() {
        return "SystemInfo{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                ", systemVersionSDK='" + systemVersionSDK + '\'' +
                ", CpuName='" + CpuName + '\'' +
                ", cpuMaxFreq='" + cpuMaxFreq + '\'' +
                ", CpuMinFreq='" + CpuMinFreq + '\'' +
                ", CpuCurrentFreq='" + CpuCurrentFreq + '\'' +
                ", CpuNumber='" + CpuNumber + '\'' +
                ", SN='" + SN + '\'' +
                ", UUID='" + UUID + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", IMEICode='" + IMEICode + '\'' +
                ", PhoneTelSimName='" + PhoneTelSimName + '\'' +
                ", WifeName='" + WifeName + '\'' +
                ", IPAddress='" + IPAddress + '\'' +
                ", location='" + location + '\'' +
                ", BaiduLocation='" + BaiduLocation + '\'' +
                ", time=" + time +
                '}';
    }
}
