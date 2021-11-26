package com.neusoft.project.storage.controller;

import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.framework.web.page.TableDataInfo;
import com.neusoft.project.storage.domain.OrderRequest;
import com.neusoft.project.storage.domain.OrderVo;
import com.neusoft.project.storage.domain.PassWord;
import com.neusoft.project.storage.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/backend/home")
@Api(tags = {"5.3.1密码修改"},description = "密码修改")
public class BackendPassController extends BaseController {

    @Resource
    private IUserService userService;

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
}
