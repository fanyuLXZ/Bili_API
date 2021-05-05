package com.dreamwolf.imageresource;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.imageresource.webinterface.UploadFileResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RefreshScope
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ImageResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImageResourceApplication.class, args);
    }

    // 真实路径
    String realPath = ClassLoader.getSystemResource("").getPath().substring(1);

    /**
     * 上传文件接口
     * @param image 图片
     * @param type 类型 如：头像 视频封面等
     * @return
     *  code释意：
     *      0-成功
     *      1-文件为空
     *      2-格式不支持
     *      3-创建文件夹失败
     *      4-保存文件异常
     */
    @PostMapping("upload-file")
    public ResponseData<UploadFileResult> upload_file(MultipartFile image,String type, HttpSession session){
        int code = 0;
        String message = "";
        UploadFileResult uploadFileResult = null;
        if (image==null||image.isEmpty()){
            code=1;
            message="文件不能为空";
        }else if (image.getContentType()==null||
                (!image.getContentType().equals("image/jpeg")&&
                !image.getContentType().equals("image/png"))){
            code=2;
            message="格式不支持";
        }else {
            // 保存文件
            // 获取后缀
            String extension = image.getContentType().substring(image.getContentType().indexOf("/")+1);
            // 生成文件名
            String fileName = new Date().getTime()+"-"+session.getId()+"."+extension;
            // 创建文件夹对象
            File dest = new File(realPath+"static\\"+type);
            if (!dest.exists()&&!dest.mkdirs()) {
                code=3;
                message="内部服务错误";
            }else {
                // 创建文件对象
                File file = new File(dest.getPath()+"\\"+fileName);
                try {
                    image.transferTo(file);
                    message="上传成功";
                    uploadFileResult = new UploadFileResult(type+"/"+fileName,fileName);
                } catch (IOException e) {
                    code=4;
                    message="内部服务错误";
                    e.printStackTrace();
                }
            }
        }
        return new ResponseData<UploadFileResult>(code,message,1,uploadFileResult);
    }
    @GetMapping("a")
    public String a(){
        return ClassLoader.getSystemResource("").getPath().substring(1);
    }
}
