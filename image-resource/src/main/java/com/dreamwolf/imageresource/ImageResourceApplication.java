package com.dreamwolf.imageresource;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.imageresource.webinterface.UploadFileResult;
import com.luciad.imageio.webp.WebPWriteParam;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RefreshScope
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ImageResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImageResourceApplication.class, args);
    }

    // 真实路径
    String realPath = ClassLoader.getSystemResource("").getPath().substring(1);
    // 服务器域名
    @Value("${domain}")
    String domain;

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
    @PostMapping("upload")
    public ResponseData<UploadFileResult> upload(MultipartFile image,String type, HttpSession session){
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
                    uploadFileResult = new UploadFileResult("//"+domain+"/"+type+"/"+fileName,fileName);
                } catch (IOException e) {
                    code=4;
                    message="内部服务错误";
                    e.printStackTrace();
                }
            }
        }
        return new ResponseData<UploadFileResult>(code,message,1,uploadFileResult);
    }
    @Value("#{${webpArgsConfig}}")
    Map<String,String> webpArgsMap;

    /**
     * 图片资源请求
     * @param request 请求对象
     * @return
     * code 可能值
     *
     */
    @GetMapping("/**")
    public void webp_get(HttpServletRequest request, HttpServletResponse response){
        String message=null;
        // 获取请求路径
        String uri = request.getRequestURI();
        // 解析文件格式
        String type = uri.substring(uri.lastIndexOf(".")+1);
        // 获取返回体的输出对象
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            // 判断是否走webp
            if (type.equals("webp")){
                try {
                    //以@分开路径名和webp参数
                    int doWebpIndex = uri.lastIndexOf(".webp");
                    int atIndex = uri.lastIndexOf("@");
                    if (doWebpIndex!=-1&&atIndex!=1){
                        String imagePath = uri.substring(0,atIndex);
                        // 判断图片是否存在
                        String imageRealPath = realPath+"static\\"+imagePath;
                        File imageFile = new File(imageRealPath);
                        if (imageFile.exists()){
                            // 获取所有webp参数
                            String[] webpArgsStrings = uri.substring(atIndex+1,doWebpIndex).split("_");
                            Set<String> webpArgsMapKeys = webpArgsMap.keySet();
                            Map<String,Integer> webpArgs=new HashMap<>();
                            for (String webpArgsString:webpArgsStrings){
                                // 解析参数名
                                String webpArgsName = webpArgsString.substring(webpArgsString.length()-1);
                                // 获取参数条件
                                String webpArgsCondition = webpArgsMap.get(webpArgsName);
                                // 判断是否存在
                                if (webpArgsCondition!=null){
                                    // 解析参数值
                                    String webpArgsValueString = webpArgsString.substring(0,webpArgsString.lastIndexOf(webpArgsName));
                                    try {
                                        // 判断是否符合条件
                                        Integer webpArgsValue = Integer.parseInt(webpArgsValueString);
                                        if (webpArgsCondition.equals("")||WebpImageResourceUtil.conditioningWebpArgs(webpArgsValue,webpArgsCondition)){
                                            webpArgs.put(webpArgsName,Integer.parseInt(webpArgsValueString));
                                        }else {
                                            throw new WebpImageResourceAnalysisException("参数:\""+webpArgsString+"\"范围为:"+webpArgsCondition);
                                        }
                                    }catch (NumberFormatException e){
                                        throw new WebpImageResourceAnalysisException("参数:\""+webpArgsString+"\"范围为:"+webpArgsCondition);
                                    }
                                }else {
                                    throw new WebpImageResourceAnalysisException("多余参数："+webpArgsString);
                                }
                            }
                            // 获取原图片宽高
                            BufferedImage sourceImg = ImageIO.read(new FileInputStream(imageFile));
                            // 计算需要传入转换方法的宽高
                            int width=sourceImg.getWidth();
                            int height=sourceImg.getHeight();
                            int argWidth = webpArgs.get("w");
                            int argHeight = webpArgs.get("h");
                            if (width>argWidth&&height>argHeight){
                                if (width-argWidth>height-argHeight){
                                    width=argWidth;
                                    height=0;
                                }else {
                                    height=argHeight;
                                    width=0;
                                }
                            }
                            // 判断需要转换的图片是否已经存在
                            String webpImageRealPath = realPath+"static\\"+uri;
                            File webpImageFile = new File(webpImageRealPath);
                            if (!webpImageFile.exists()){
                                // 转换图片
                                if (!WebpImageResourceUtil.executeCWebp(imageRealPath,webpImageRealPath,webpArgs.get("q"),width,height)){
                                    throw new WebpImageResourceAnalysisException("内部服务器错误");
                                }else {
                                    // 由于cmd有异步问题 所以等待几秒
                                    long timeout = System.currentTimeMillis() + 5 * 60 * 60;
                                    while (!webpImageFile.exists()){
                                        try {
                                            TimeUnit.MILLISECONDS.sleep(100);//毫秒
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if (System.currentTimeMillis()>timeout){
                                            throw new WebpImageResourceAnalysisException("内部服务器错误：超时");
                                        }
                                    }
                                }
                            }
                            byte[] webpImageBytes = WebpImageResourceUtil.getBytesByFile(webpImageFile);
                            if (webpImageBytes!=null){
                                os.write(webpImageBytes);
                            }else {
                                throw new WebpImageResourceAnalysisException("内部服务器错误");
                            }
                        }else {
                            throw new WebpImageResourceAnalysisException("未找到资源");
                        }
                    }else {
                        throw new WebpImageResourceAnalysisException("webp请使用\"@\"进行参数传递");
                    }
                }catch (WebpImageResourceAnalysisException e){
                    message = e.getMessage();
                }
            }else {
                try {
                    BufferedImage image = ImageIO.read(new FileInputStream(new File(realPath+"\\static\\"+uri)));
                    response.setContentType("image/"+type);
                    os = response.getOutputStream();
                    if (image != null) {
                        ImageIO.write(image, type, os);
                    }
                } catch (IOException e) {
                    message="未找到资源";
                }
            }
            // 如果message不等于空代表图片获取成功 否则失败，返回错误信息
            if (message!=null){
                try {
                    response.setContentType("application/json;charset=UTF-8");
                    IOUtils.write(message, os);
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
