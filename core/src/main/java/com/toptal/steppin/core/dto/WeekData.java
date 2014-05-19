package com.toptal.steppin.core.dto;

/**
 * Created by ggomes on 18/05/14.
 */
public class WeekData {
    private Long distanceInMeters;
    private Integer averageSpeedInKmH;

    public WeekData(Long distanceInMeters, Integer averageSpeedInKmH){
        super();
        this.distanceInMeters = distanceInMeters;
        this.averageSpeedInKmH = averageSpeedInKmH;
    }

    public Long getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Long distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public Integer getAverageSpeedInKmH() {
        return averageSpeedInKmH;
    }

    public void setAverageSpeedInKmH(Integer averageSpeedInKmH) {
        this.averageSpeedInKmH = averageSpeedInKmH;
    }
}
