package com.dreamwolf.video.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * 分区表 video的bvChildZoning视频子分区id
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zoning {

    private Integer zID;    //分区ID
    private String zName;   //分区名称

}
