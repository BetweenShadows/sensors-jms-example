package com.wolfisc.fakesensordevice.deviceinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class DeviceInfo implements Serializable {

    @JsonProperty("device_id")
    private int deviceId;

    @JsonProperty("temperature")
    private double temp;

    private double humidity;

    private String date;

    public DeviceInfo() {
    }

    public DeviceInfo(int deviceId, double temperatura, double humedad, String fechaGeneracion) {
        this.deviceId = deviceId;
        this.temp = temperatura;
        this.humidity = humedad;
        this.date = fechaGeneracion;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceInfo that = (DeviceInfo) o;
        return deviceId == that.deviceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId);
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "deviceId=" + deviceId +
                ", temp=" + temp +
                ", humidity=" + humidity +
                ", date='" + date + '\'' +
                '}';
    }
}
