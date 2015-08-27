package com.intuit.OTA.model;

/**
 * Created with IntelliJ IDEA.
 * User: audupa
 * Date: 8/27/15
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Device {
    private Long id;
    private String deviceId;
    private String ipAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    private String platformName;
    private String platformVersion;
}
