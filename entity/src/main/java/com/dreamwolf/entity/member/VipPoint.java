package com.dreamwolf.entity.member;

import lombok.Data;

@Data
public class VipPoint {
    private Integer mid;
    private Integer pointBalance;

    public VipPoint(Integer mid, Integer pointBalance) {
        this.mid = mid;
        this.pointBalance = pointBalance;
    }
}
