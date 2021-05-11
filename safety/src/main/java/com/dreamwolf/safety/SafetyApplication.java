package com.dreamwolf.safety;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.User;
import com.dreamwolf.entity.member.UserVerify;
import com.dreamwolf.safety.service.MemberService;
import com.dreamwolf.safety.util.TokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@RestController
@RefreshScope
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.dreamwolf.safety.*")
public class SafetyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SafetyApplication.class, args);
    }

    @Resource
    MemberService memberService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Value("${domain}")
    String domain;

    public void saveToken(Integer uid,HttpServletResponse response){
        // 验证成功
        String uidString = uid.toString();
        // 生成token
        String token = TokenUtil.creat();
        // 将token存入cookie
        Cookie cookie = new Cookie("token",token);
        cookie.setPath("/");
        cookie.setDomain(domain);
        response.addCookie(cookie);
        // 判断此uid是否已经存过 有则删除
        BoundHashOperations<String, String, String> tokens = stringRedisTemplate.boundHashOps("tokens");
        Map<String,String> tokensMap = tokens.entries();
        if (tokensMap!=null){
            for (Entry<String,String> entry:tokensMap.entrySet()){
                if (entry.getValue().equals(uidString)){
                    tokens.delete(entry.getKey());
                    break;
                }
            }
        }
        // 将token和id存入redis
        tokens.put(token,uidString);
    }

    /**
     * 登陆请求
     * 依赖：
     *  Member服务 -> /user/verify
     * @param tel 手机号
     * @param code 验证码
     * @param username 用户名
     * @param password 密码
     * @param response 返回对象
     * @return
     *  code可能值：
     *  0 成功
     *  其他code见 Member服务 -> /user/verify
     *  其他值详见ResponseData类注释
     */
    @PostMapping("/login")
    public ResponseData<Integer> login(String tel, Integer code, String username, String password, HttpServletResponse response) {
        int responseDataCode = 0;
        String message = "登陆成功";
        Integer responseDataUid = null;
        // 判断是使用手机验证码登陆还是账号密码登陆
        if (tel != null && !tel.equals("") && code != null) {
            // 手机验证码登陆
        }else if(username != null && !username.equals("") && password != null && !password.equals("")){
            // 账号密码登陆
            // 远程调用member服务的验证接口
            ResponseData<UserVerify> verifyResult= memberService.verify(username,password);
            // 判断是否验证成功
            if (verifyResult.getCode()==0){
                saveToken(verifyResult.getData().getUid(),response);
            }else {
                code=-400;
                message = verifyResult.getMessage();
            }
        }
        return new ResponseData<>(responseDataCode,message,1,responseDataUid);
    }

    /**
     * 根据tokon获取uid的请求
     * @param token token
     * @return
     *  code可能值：
     *  0 成功
     *  1 token为空
     *  2 token过期
     *  其他值详见ResponseData类注释
     */
    @GetMapping("/logon-uid")
    public ResponseData<Integer> logon_uid(String token){
        int code = 0;
        String message = "";
        Integer uid = null;
        if (token==null||token.equals("")){
            code=1;
            message="token不能为空";
        }else {
            BoundHashOperations<String, String, String> tokens = stringRedisTemplate.boundHashOps("tokens");
            String current_uid = tokens.get(token);
            if (current_uid==null){
                code=2;
                message="token已过期";
            }else {
                uid = Integer.parseInt(current_uid);
            }
        }
        return new ResponseData<>(code,message,1,uid);
    }

    /**
     * 判断是否登陆接口
     * 依赖：
     *  safety -> landed_uid
     * @param request 请求对象
     * @return
     *  ResponseData.data:是否登陆
     */
    @GetMapping("/qrcode/isLogin")
    public ResponseData<Boolean> isLogin(HttpServletRequest request){
        String token = TokenUtil.getToken(request);
        ResponseData<Integer> responseData_uid = logon_uid(token);
        return new ResponseData<>(0,"",1,responseData_uid.getCode()==0);
    }

    /**
     * 注册接口
     * @param nickname 昵称
     * @param password 密码
     * @param phone 手机号
     * @param response 返回对象
     * @return
     */
    @PostMapping("/register")
    public ResponseData<Boolean> register(String nickname,String password,String phone,HttpServletResponse response){
        ResponseData<User> responseData = memberService.register(nickname,password,phone);
        if (responseData.getCode()==0){
            saveToken(responseData.getData().getuID(),response);
        }
        return new ResponseData<>(responseData.getCode(),responseData.getMessage(),responseData.getTtl(),responseData.getCode()==0);
    }
}
