package com.neusoft.project.storage.controller;

import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.UserEntity;
import com.neusoft.project.storage.mapper.UserMapper;
import com.neusoft.project.storage.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/app")
@Api(tags = {"5.2.1注册"},description = "注册")
public class AppRegistController extends BaseController {

    @Resource
    private IUserService userService;

    @Log(title = "5.2.1注册",businessType = BusinessType.INSERT)
    @PostMapping("/regist")
    @ApiOperation(value = "5.2.1注册",notes = "注册")
    public AjaxResult add(@RequestBody UserEntity userEntity){
        return userService.registUser(userEntity);
    }


}
