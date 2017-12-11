package com.zixing.lbsdemo;

/**
 * Created by zixing on 2017/12/10.
 */

public class WaterStation {
    private double lat;
    private double lng;
    private String stationName;
    private int allowance;
    private int empty;

    public WaterStation(double lat, double lng, String stationName, int allowance, int empty) {
        this.lat = lat;
        this.lng = lng;
        this.stationName = stationName;
        this.allowance = allowance;
        this.empty = empty;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public int getEmpty() {
        return empty;
    }

    public void setEmpty(int empty) {
        this.empty = empty;
    }
}
