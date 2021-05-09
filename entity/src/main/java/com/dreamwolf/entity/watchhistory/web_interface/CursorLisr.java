package com.dreamwolf.entity.watchhistory.web_interface;

import lombok.Data;

import java.util.List;

@Data
public class CursorLisr {
    private Cursor cursor;
    private List<ListCursor>  listCursors;

    public CursorLisr(Cursor cursor, List<ListCursor> listCursors) {
        this.cursor = cursor;
        this.listCursors = listCursors;
    }

    public CursorLisr() {
    }
}
