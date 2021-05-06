package com.dreamwolf.safety;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.safety.util.TokenUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
//@EnableFeignClients(basePackages = "com.dreamwolf.safety.*")
public class SafetyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SafetyApplication.class, args);
    }

//    @Resource
//    MemberService memberService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/login")
    public Map login(String tel, Integer code, String username, String password, HttpServletResponse response, HttpServletRequest request) {
        HashMap<String,Object> map = new HashMap<>();
        // 判断是使用手机验证码登陆还是账号密码登陆
        if (tel != null && !tel.equals("") && code != null) {
            // 手机验证码登陆

        }else if(username != null && !username.equals("") && password != null && !password.equals("")){
            // 账号密码登陆
            // 远程调用member服务的验证接口
//            Map verifyResult= memberService.verify(username,password);
            Map<String,Object> verifyResult= new HashMap<>();
            verifyResult.put("succeed",true);
            verifyResult.put("uid",1);
            // 判断是否验证成功
            if ((boolean)verifyResult.get("succeed")){
                // 验证成功
                // 生成token
                String token = TokenUtil.creat();
                // 将token存入cookie
                Cookie cookie = new Cookie("token",token);
                response.addCookie(cookie);
                // 判断此uid是否已经存过 有则删除
                BoundHashOperations<String, String, String> tokens = stringRedisTemplate.boundHashOps("tokens");
                Map<String,String> tokensMap = tokens.entries();
                if (tokensMap!=null){
                    String uid = verifyResult.get("uid").toString();
                    for (Entry<String,String> entry:tokensMap.entrySet()){
                        if (entry.getValue().equals(uid)){
                            tokens.delete(entry.getKey());
                            break;
                        }
                    }
                }
                // 将token和id存入redis
                tokens.put(token,verifyResult.get("uid").toString());
                // 填返回数据
                map.put("code", "0");
                map.put("message","登陆成功！");
            }else {
                map.put("code","-400");
                map.put("message",verifyResult.get("message"));
            }
        }
        return map;
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
    @GetMapping("/landed-uid")
    public ResponseData<String> landed_uid(String token){
        int code = 0;
        String message = "";
        String uid = null;
        if (token==null||token.equals("")){
            code=1;
            message="token不能为空";
        }else {
            BoundHashOperations<String, String, String> tokens = stringRedisTemplate.boundHashOps("tokens");
            Boolean hasKey = tokens.hasKey(token);
            if (hasKey==null || !hasKey){
                code=2;
                message="token已过期";
            }else {
                uid = tokens.get(token);
            }
        }
        return new ResponseData<>(code,message,1,uid);
    }
}
