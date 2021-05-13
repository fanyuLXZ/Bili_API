package com.dreamwolf.entity.videoresource.web_inerface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoInfo {
    private String path;
    private long duration;
}
