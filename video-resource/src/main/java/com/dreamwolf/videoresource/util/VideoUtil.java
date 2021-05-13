package com.dreamwolf.videoresource.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class VideoUtil {

    public static long getVideoDuration(MultipartFile file) throws IOException, EncoderException {
        Encoder encoder = new Encoder();
        long ms = 0;
        // 获取文件类型
        String fileName = file.getContentType();
        // 获取文件后缀
        String pref = fileName.indexOf("/") != -1 ? fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length()) : null;
        String prefix = "." + pref;
        // 用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile(UUID.randomUUID().toString().replace("-", ""), prefix);
        // MultipartFile to File
        file.transferTo(excelFile);
        MultimediaInfo m = encoder.getInfo(excelFile);
        ms = m.getDuration();
        //程序结束时，删除临时文件
        VideoUtil.deleteFile(excelFile);
        return ms;
    }
 
    /**
     * @Description: 删除文件
     * @author: Hanweihu
     * @date: 2019/7/30 8:35
     * @params: [files]
     * @return: void
     */
    private static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}