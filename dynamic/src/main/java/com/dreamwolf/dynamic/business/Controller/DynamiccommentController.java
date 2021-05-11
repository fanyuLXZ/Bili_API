package com.dreamwolf.dynamic.business.Controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.dynamic.Page;
import com.dreamwolf.entity.dynamic.Reply;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.web_interface.CommListMap;
import com.dreamwolf.entity.dynamic.Dynamiccomment;
import com.dreamwolf.entity.dynamic.Userdata;
import com.dreamwolf.dynamic.business.service.*;
import com.dreamwolf.entity.member.*;
import com.dreamwolf.entity.member.web_interface.Bang;
import com.dreamwolf.safety.util.TokenUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class DynamiccommentController {
    @Resource
    DynamiccommentService dynamiccommentService;
    @Resource
    MemberService memberService;
    @Resource
    CommentService commentService;
    @Resource
    SafetyService safetyService;

    //动态的最新信息
    @SentinelResource(value = "entrance",fallback="handlerEntrance")
    @RequestMapping("/entrance")
    public ResponseData<Bang> entrance(HttpServletRequest request){
        ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
        if (logon_uid_result.getCode()==0) {
            Integer id = logon_uid_result.getData();
            int code = 0;
            String message = "";
            ResponseData<Bang> bangverify = memberService.verify(id);
            Bang bang = bangverify.getData();
            if (bangverify.getCode() != 0) {
                code = 1;
                message = "接口出现问题";
            }
            return new ResponseData<Bang>(code, message, 1, bang);
        }else {
            return new ResponseData<Bang>(logon_uid_result.getCode(), logon_uid_result.getMessage(), 1, null);
        }
    }
    public Map handlerEntrance(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //dynamicComment表所有信息
    @SentinelResource(value = "dynamicComment",fallback="handlerDynamicComment")
    @RequestMapping("/dynamicComment")
    public ResponseData<List<Dynamiccomment>> dynamicComment(){
        QueryWrapper<Dynamiccomment> wrapper = new QueryWrapper<>();
        wrapper.eq("udID","1");
        List<Dynamiccomment> relations=dynamiccommentService.list(wrapper);
        return new ResponseData<List<Dynamiccomment>>(0,"",1,relations);
    }
    public Map handlerDynamicComment(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //动态评论简略信息
    @SentinelResource(value = "reply",fallback="handlerReply")
    @GetMapping("/reply")
    public ResponseData<Reply> reply(Integer dynamic_id, Integer sort){//dynamic_id 动态id sort 1按照热度 2按照时间排序
        int code = 0;
        String message="";
        Reply reply=null;
        if(dynamic_id==null){
            dynamic_id=1;
        }
        if(  sort==null){
            sort=1;
        }
        QueryWrapper<Dynamiccomment> queryWrapper=new QueryWrapper();
        queryWrapper.eq("udID",dynamic_id);
        List<Dynamiccomment> dynamiccomment=dynamiccommentService.list(queryWrapper);//返回对应动态id的父评论
        Integer[] list = new Integer[dynamiccomment.size()];//数组 用来储存所有父评论id
        for (int i=0;i<dynamiccomment.size();i++){
            list[i]=dynamiccomment.get(i).getcID();
        }
        ResponseData<List<CommListMap>> replies=null;
        if (list.length>0){
            replies=commentService.commselecarlist(sort,list);
        }
        Integer i=dynamiccomment.size();
        if(replies!=null){
            for(CommListMap commListMap:replies.getData()){
                i+=commListMap.getCount();
            }
        }
        List<CommListMap> listcommlist=new ArrayList<>();
        Page page=new Page(i,dynamiccomment.size(),1,10);
        reply=new Reply(page,replies!=null?replies.getData():listcommlist);
        return  new ResponseData<Reply>(code,message,1,reply);
    }
    public Map handlerReply(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //动态详细评论信息
    @SentinelResource(value = "replymain",fallback="handlerReplymain")
    @GetMapping("/reply/main")
    public ResponseData<List<CommListMap>> replymain(Integer sort,Integer dynamic_id,Integer next){//sort1按热度 2按时间 动态dynamic_id  next页码
        int code = 0;
        String message="";
        ResponseData<List<CommListMap>> replies=null;
        if(sort!=null && dynamic_id!=null && next!=null){
            QueryWrapper<Dynamiccomment> queryWrapper=new QueryWrapper();
            queryWrapper.eq("udID",dynamic_id);
            List<Dynamiccomment> dynamiccomment=dynamiccommentService.list(queryWrapper);//返回对应动态id的父评论
            Integer[] list = new Integer[dynamiccomment.size()];//数组 用来储存所有父评论id
            for (int i=0;i<dynamiccomment.size();i++){
                list[i]=dynamiccomment.get(i).getcID();
            }
            if (list.length>0){
                replies=commentService.commselecarlistpage(sort,list,next);
            }else{
                message="当前动态没有评论";
            }
        }else{
            code = 1;
            message="请传入参数";
        }
        return new ResponseData<List<CommListMap>>(code,message,1,replies.getData());
    }
    public Map handlerReplymain(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //member发表评论人对象 接口调接口
    @GetMapping("/memberid")
    public ResponseData<Member> memberid(Integer id){
        int code = 0;
        String message="";
        Member member=null;
        if (id!=null){
            ResponseData<User> user=memberService.useruid(id);
            ResponseData<Userdata> userdata=memberService.userdata(id);
            if (userdata.getCode()!=0){
                code=1;
                message="接口出现问题";
            }
            Level_info level_info=new Level_info(userdata.getData().getLevel());
            ResponseData<Vip> vip= memberService.vip(id);
            VipStatus vipStatus=new VipStatus(vip.getData()!=null);
            member=new Member(user.getData().getuID(),user.getData().getNickName(),user.getData().getSex()==1?"男":"女",user.getData().getHeadImgPath(),level_info,vipStatus);
        }else{
            code=1;
            message="id不能为空";
        }
        return new ResponseData<Member>(code,message,1,member);
    }

}

