package com.dreamwolf.entity.imageresource.webinterface;

import lombok.Data;

@Data
public class UploadFileResult {
    private String filePath="";
    private String fileName="";

    public UploadFileResult(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public UploadFileResult() {
    }
}
