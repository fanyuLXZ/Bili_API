package com.dreamwolf.entity.dynamic.web_interface;

import lombok.Data;

import java.util.List;

@Data
public class ItemsNumObject {
    private List<DynamicNumObject> items;

    public ItemsNumObject(List<DynamicNumObject> items) {
        this.items = items;
    }

    public ItemsNumObject() {
    }
}
