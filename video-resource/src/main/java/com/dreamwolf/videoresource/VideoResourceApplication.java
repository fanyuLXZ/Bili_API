package com.dreamwolf.videoresource;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.imageresource.webinterface.UploadFileResult;
import com.dreamwolf.entity.videoresource.web_inerface.VideoInfo;
import com.dreamwolf.safety.util.TokenUtil;
import com.dreamwolf.videoresource.service.SafetyService;
import com.dreamwolf.videoresource.util.FileUtil;
import com.dreamwolf.videoresource.util.VideoUtil;
import it.sauronsoftware.jave.EncoderException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RefreshScope
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MultipartAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.dreamwolf.videoresource.*")
public class VideoResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoResourceApplication.class, args);
    }

    @Bean("multipartResolver")
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxInMemorySize(10000);
        return resolver;
    }

    // 真实路径
    String realPath = ClassLoader.getSystemResource("").getPath().substring(1);

    // 服务器域名
    @Value("${domain}")
    String domain;

    @Resource
    SafetyService safetyService;

    /**
     * 上传文件接口
     * @param video 视频
     * @return
     *  code释意：
     *      0-成功
     *      1-文件为空
     *      2-token验证失败
     *      3-创建文件夹失败
     *      4-保存文件异常
     */
    @PostMapping("upload")
    public ResponseData<UploadFileResult> upload_file(MultipartFile video, HttpSession session, HttpServletRequest request){
        int code = 0;
        String message;
        UploadFileResult uploadFileResult = null;
        if (video==null||video.isEmpty()||video.getContentType()==null){
            code=1;
            message="文件不能为空";
        }else {
            //验证token
            ResponseData<Integer> response_uid = safetyService.logon_uid(TokenUtil.getToken(request));
            if (response_uid.getCode()==0){
                // 保存文件
                // 获取后缀
                String extension = video.getContentType().substring(video.getContentType().indexOf("/")+1);
                // 生成文件名
                String fileName = new Date().getTime()+"-"+session.getId()+"."+extension;
                // 创建文件夹对象
                File dest = new File(realPath+"static\\"+response_uid.getData());
                if (!dest.exists()&&!dest.mkdirs()) {
                    code=3;
                    message="内部服务错误";
                }else {
                    // 创建文件对象
                    File file = new File(dest.getPath()+"\\"+fileName);
                    try {
                        video.transferTo(file);
                        message="上传成功";
                        uploadFileResult = new UploadFileResult("//"+domain+"/"+response_uid.getData()+"/"+fileName,fileName);
                    } catch (IOException e) {
                        code=4;
                        message="内部服务错误";
                        e.printStackTrace();
                    }
                }
            }else {
                code=2;
                message="请先登录哦";
            }
        }
        return new ResponseData<>(code,message,1,uploadFileResult);
    }

    /**
     * 验证视频是否存在接口
     * @param videoPath 视频路径
     * @return
     * code 可能值
     * 0 成功
     * 1 视频路径为空
     * 2 视频不存在
     * 3 转化视频出错
     */
    @GetMapping("verify")
    public ResponseData<VideoInfo> verify(String videoPath){
        int code=0;
        String message = "";
        VideoInfo data = null;
        if (videoPath!=null&&!videoPath.equals("")){
            // 判断视频是否存在
            File videoFile = new File(realPath+"static\\"+videoPath.substring(3+domain.length()));
            if (videoFile.exists()){
                try {
                    MultipartFile multipartFile = new CommonsMultipartFile(FileUtil.createFileItem(realPath+"static\\"+videoPath.substring(3+domain.length())));
                    data = new VideoInfo(videoPath, VideoUtil.getVideoDuration(multipartFile)/1000);
                } catch (IOException | EncoderException e) {
                    e.printStackTrace();
                    code=3;
                    message="服务器内部异常";
                }
            }else {
                code=2;
                message="视频不存在";
            }
        }else {
            code=1;
            message="视频路径不能为空";
        }
        return new ResponseData<>(code,message,1,data);
    }
}
