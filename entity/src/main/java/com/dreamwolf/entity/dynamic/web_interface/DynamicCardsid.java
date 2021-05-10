package com.dreamwolf.entity.dynamic.web_interface;

import com.dreamwolf.entity.dynamic.Cards;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinitions;
import java.util.List;

@Data
public class DynamicCardsid {
    private Cards card;

    public DynamicCardsid(Cards cards) {
        this.card = cards;
    }

    public DynamicCardsid() {
    }
}
