package com.dreamwolf.entity.dynamic.web_interface;

import com.dreamwolf.entity.dynamic.Cards;
import lombok.Data;

import java.util.List;

@Data
public class DynamicCards {
    private List<Cards> cards;

    public DynamicCards(List<Cards> cards) {
        this.cards = cards;
    }

    public DynamicCards() {
    }
}
