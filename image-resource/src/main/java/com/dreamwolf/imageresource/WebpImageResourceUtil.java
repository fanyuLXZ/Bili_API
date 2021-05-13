package com.dreamwolf.imageresource;

import lombok.NonNull;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * webp图片工具类
 */
public class WebpImageResourceUtil {
    /**
     * 判断web图片参数是否符合条件
     * @param arg 参数
     * @param condition 条件
     * @return 是否符合条件
     */
    public static boolean conditioningWebpArgs(@NonNull Integer arg,@NonNull  String condition) {
        // 解析条件

        // 尝试使用`范围`解析
        String[] range = condition.split("-");
        if (range.length!=1){
            return arg>=Integer.parseInt(range[0])&&arg<=Integer.parseInt(range[1]);
        }

        // 尝试使用`或`解析
        range = condition.split("\\|");
        if (range.length!=1){
            String strArg = arg.toString();
            for (String i:range) {
                if (strArg.equals(i)){
                    return true;
                }
            }
            return false;
        }
        throw new RuntimeException("参数条件格式异常");
    }

    public static boolean executeCWebp(String inputFile, String outputFile, Integer quality,Integer width,Integer height) {
        boolean result = false;
        ClassLoader cl = ImageFormatConverter.class.getClassLoader();
        String cwebpPath = cl.getResource("cwebp.exe").getPath();
        try {
            StringBuilder command = new StringBuilder(cwebpPath);
            command.append(" -q ").append(quality == 0 ? 75 : quality);
            command.append(" -alpha_q ").append(quality == 0 ? 75 : quality);
            command.append(" ").append(inputFile);
            command.append(" -o ").append(outputFile);
            command.append(" -resize ").append(width).append(" ").append(height);
            Runtime.getRuntime().exec(command.toString());
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] getBytesByFile(File file) {
        try {
            //获取输入流
            FileInputStream fis = new FileInputStream(file);

            //新的 byte 数组输出流，缓冲区容量1024byte
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            //缓存
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            //改变为byte[]
            byte[] data = bos.toByteArray();
            //
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
