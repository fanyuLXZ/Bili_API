package com.dreamwolf.entity.member;

import lombok.Data;

@Data
public class Label {
    private String path;
    private String text;
    private String label_theme;
    private String text_color;
    private String bg_style;
    private String bg_color;
    private String border_color;

    public Label(String path, String text, String label_theme, String text_color, String bg_style, String bg_color, String border_color) {
        this.path = path;
        this.text = text;
        this.label_theme = label_theme;
        this.text_color = text_color;
        this.bg_style = bg_style;
        this.bg_color = bg_color;
        this.border_color = border_color;
    }
}
