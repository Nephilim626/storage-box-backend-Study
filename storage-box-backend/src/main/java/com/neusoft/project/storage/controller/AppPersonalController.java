package com.neusoft.project.storage.controller;

import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.Advice;
import com.neusoft.project.storage.domain.PassWord;
import com.neusoft.project.storage.domain.UserEntity;
import com.neusoft.project.storage.service.AdvertisementService;
import com.neusoft.project.storage.service.AdviceService;
import com.neusoft.project.storage.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/personal")
public class AppPersonalController extends BaseController {
    @Resource
    private IUserService userService;

    @Resource
    private AdviceService adviceService;


    @Log(title = "密码修改 ",businessType = BusinessType.OTHER)
    @PutMapping("/updatePassword")
    @ApiOperation(value = "密码修改",notes = "密码修改")
    public AjaxResult updatePassword( PassWord passWord){
        if(userService.updatePassword(passWord)>=1){
            return AjaxResult.success("密码修改成功");
        }else {
            //返回响应请求分页数据
            return AjaxResult.error("密码修改失败");}
    }

    @Log(title = "意见建议 ",businessType = BusinessType.OTHER)
    @PostMapping("/advice")
    @ApiOperation(value = "意见建议",notes = "意见建议")
    public AjaxResult advice( @RequestBody Advice advice){
        if(adviceService.insertAdvice(advice)>=1){
            return AjaxResult.success("新增成功");
        }else return AjaxResult.error("新增失败");

    }

}
