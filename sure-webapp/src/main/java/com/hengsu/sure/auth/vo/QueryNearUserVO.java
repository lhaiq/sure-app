package com.hengsu.sure.auth.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by haiquanli on 16/1/12.
 */
public class QueryNearUserVO {

    @NotNull
    private Double longitude;
    @NotNull
    private Double latitude;

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
