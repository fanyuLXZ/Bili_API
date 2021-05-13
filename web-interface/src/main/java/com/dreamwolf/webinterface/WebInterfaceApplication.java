package com.dreamwolf.webinterface;


import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.dynamic.web_interface.ItemsNumObject;
import com.dreamwolf.entity.member.Member;
import com.dreamwolf.entity.web_interface.NavStatResult;
import com.dreamwolf.safety.util.TokenUtil;
import com.dreamwolf.webinterface.service.DynamicService;
import com.dreamwolf.webinterface.service.MemberService;
import com.dreamwolf.webinterface.service.SafetyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//扫描Mapper 文件夹
@RefreshScope
@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.dreamwolf.webinterface.service")
public class WebInterfaceApplication {
  public static void main(String[] args) {
    SpringApplication.run(WebInterfaceApplication.class, args);
  }

  @Resource
  MemberService memberService;
  @Resource
  DynamicService dynamicService;
  @Resource
  SafetyService safetyService;

  @GetMapping("/nav/stat")
  public ResponseData<NavStatResult> navStat(HttpServletRequest request){
    int code = 0;
    String message = "";
    NavStatResult data = null;
    ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
    if (logon_uid_result.getCode()==0){
      data = new NavStatResult();
      ResponseData<Member> cardInfo = memberService.cardInfoById(logon_uid_result.getData());
      ResponseData<ItemsNumObject> dynamicNum = dynamicService.dynamic_num(new int[]{logon_uid_result.getData()});
      if (cardInfo.getCode()==0){
        data.setFollower(cardInfo.getData().getFriend().toString());
        data.setFollowing(cardInfo.getData().getFans().toString());
      }
      if (dynamicNum.getCode()==0){
        data.setDynamic_count(dynamicNum.getData().getItems().get(0).getNum().toString());
      }
    }else {
      code=-101;
      message="账号未登录";
    }
    return new ResponseData<>(code,message,1,data);
  }
}
