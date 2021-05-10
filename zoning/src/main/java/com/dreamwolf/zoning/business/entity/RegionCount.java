package com.dreamwolf.zoning.business.entity;

import lombok.Data;

import java.util.Map;

@Data
public class RegionCount {
    private Map region_count;

    public RegionCount(Map region_count) {
        this.region_count = region_count;
    }

    public RegionCount() {
    }
}
