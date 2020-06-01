package org.redeoza.xestion.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeoAPI {

    @JsonProperty("update_date")
    private String updateDate;

    @JsonProperty("size")
    private int size;

    @JsonProperty("data")
    private List<GeoAPIEntities> entitiesList;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<GeoAPIEntities> getEntitiesList() {
        return entitiesList;
    }

    public void setEntitiesList(List<GeoAPIEntities> entitiesList) {
        this.entitiesList = entitiesList;
    }
}
