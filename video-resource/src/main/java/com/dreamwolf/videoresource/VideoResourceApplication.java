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

    // ????????????
    String realPath = ClassLoader.getSystemResource("").getPath().substring(1);

    // ???????????????
    @Value("${domain}")
    String domain;

    @Resource
    SafetyService safetyService;

    /**
     * ??????????????????
     * @param video ??????
     * @return
     *  code?????????
     *      0-??????
     *      1-????????????
     *      2-token????????????
     *      3-?????????????????????
     *      4-??????????????????
     */
    @PostMapping("upload")
    public ResponseData<UploadFileResult> upload_file(MultipartFile video, HttpSession session, HttpServletRequest request){
        int code = 0;
        String message;
        UploadFileResult uploadFileResult = null;
        if (video==null||video.isEmpty()||video.getContentType()==null){
            code=1;
            message="??????????????????";
        }else {
            //??????token
            ResponseData<Integer> response_uid = safetyService.logon_uid(TokenUtil.getToken(request));
            if (response_uid.getCode()==0){
                // ????????????
                // ????????????
                String extension = video.getContentType().substring(video.getContentType().indexOf("/")+1);
                // ???????????????
                String fileName = new Date().getTime()+"-"+session.getId()+"."+extension;
                // ?????????????????????
                File dest = new File(realPath+"static\\"+response_uid.getData());
                if (!dest.exists()&&!dest.mkdirs()) {
                    code=3;
                    message="??????????????????";
                }else {
                    // ??????????????????
                    File file = new File(dest.getPath()+"\\"+fileName);
                    try {
                        video.transferTo(file);
                        message="????????????";
                        uploadFileResult = new UploadFileResult("//"+domain+"/"+response_uid.getData()+"/"+fileName,fileName);
                    } catch (IOException e) {
                        code=4;
                        message="??????????????????";
                        e.printStackTrace();
                    }
                }
            }else {
                code=2;
                message="???????????????";
            }
        }
        return new ResponseData<>(code,message,1,uploadFileResult);
    }

    /**
     * ??????????????????????????????
     * @param videoPath ????????????
     * @return
     * code ?????????
     * 0 ??????
     * 1 ??????????????????
     * 2 ???????????????
     * 3 ??????????????????
     */
    @GetMapping("verify")
    public ResponseData<VideoInfo> verify(String videoPath){
        int code=0;
        String message = "";
        VideoInfo data = null;
        if (videoPath!=null&&!videoPath.equals("")){
            // ????????????????????????
            File videoFile = new File(realPath+"static\\"+videoPath.substring(3+domain.length()));
            if (videoFile.exists()){
                try {
                    MultipartFile multipartFile = new CommonsMultipartFile(FileUtil.createFileItem(realPath+"static\\"+videoPath.substring(3+domain.length())));
                    data = new VideoInfo(videoPath, VideoUtil.getVideoDuration(multipartFile)/1000);
                } catch (IOException | EncoderException e) {
                    e.printStackTrace();
                    code=3;
                    message="?????????????????????";
                }
            }else {
                code=2;
                message="???????????????";
            }
        }else {
            code=1;
            message="????????????????????????";
        }
        return new ResponseData<>(code,message,1,data);
    }
}
