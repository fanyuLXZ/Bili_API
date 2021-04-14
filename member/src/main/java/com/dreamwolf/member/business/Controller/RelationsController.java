package com.dreamwolf.member.business.Controller;


import com.dreamwolf.member.business.service.IRelationsService;
import com.dreamwolf.member.business.service.IUserService;
import com.dreamwolf.member.business.service.IUserdataService;
import com.dreamwolf.member.business.service.IVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户关系表 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class RelationsController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IUserdataService iUserdataService;
    @Autowired
    IVipService iVipService;
    @Autowired
    IRelationsService iRelationsService;
}

