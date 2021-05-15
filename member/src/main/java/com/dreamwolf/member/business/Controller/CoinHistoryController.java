package com.dreamwolf.member.business.Controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.CoinHistory;
import com.dreamwolf.entity.member.Userdata;
import com.dreamwolf.member.business.service.CoinHistoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CoinHistoryController {

    @Resource
    CoinHistoryService coinHistoryService;

    /**
     * 是否投币
     * 数据是否已经存在 存在返回true
     */
    @PostMapping("whether/coin")
    public ResponseData<Boolean> whethercoin(Integer uID,Integer vID){//参数1用户id 2.视频id
        int code = 0;
        String message="";
        boolean bool = true;
        if (uID!=null && vID!=null){//参数不能为空
            bool=coinHistoryService.coinhistory(uID,vID)==null;
        }else{
            code = 1;
            message="参数不能为空";
        }
        return new ResponseData<>(code,message,1,bool);
    }
}
