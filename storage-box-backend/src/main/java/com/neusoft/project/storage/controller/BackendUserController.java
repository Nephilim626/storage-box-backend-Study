package com.neusoft.project.storage.controller;


import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.framework.web.page.TableDataInfo;
import com.neusoft.project.storage.domain.UserEntity;
import com.neusoft.project.storage.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/backend/user")
@Api(tags = {"5.3.2用户管理"},description = "用户列表（分页），用户新增，用户编辑")
public class BackendUserController extends BaseController {
    @Resource
    private IUserService userService;
//
//    查询用户列表
//
//    @Param userEntity
//    @return 分页结果
//
    @Log(title = "用户列表（分页）",businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "用户列表（分页）",notes = "查询用户列表")
    /*TableDataInfo(分页)*/
    public TableDataInfo list(UserEntity userEntity){
        /*获取分页信息*/
        startPage();
        //查询后台端用户列表
        List<UserEntity> list=userService.selectUserList(userEntity);
        //返回响应请求分页数据
        return getDataTable(list);
    }


    @Log(title = "5.3.2.2 用户新增",businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ApiOperation(value = "5.3.2.2 用户新增",notes = "新增用户")
    /*TableDataInfo(分页)*/
    public AjaxResult add(@RequestBody UserEntity userEntity){
        if(userService.insertUser(userEntity)>=1){
            return AjaxResult.success("新增成功");
        }else {
            return AjaxResult.error("新增失败");
        }
    }


    @Log(title = "5.3.2.2 用户编辑",businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    @ApiOperation(value = "5.3.2.2 用户编辑",notes = "用户修改")
    public  AjaxResult edit( @RequestBody UserEntity userEntity){
        if(userService.updatetUser(userEntity)>=1){
            return AjaxResult.success("修改成功");
        }else {
            return AjaxResult.error("修改失败");
        }
    }

    @Log(title = "5.3.2.2 用户删除",businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{userIds}")
    @ApiOperation(value = "5.3.2.2 用户删除",notes = "用户删除")
    public AjaxResult delete(@PathVariable("userIds") Long[] userIds){
        if (userService.deleteUser(userIds)>=1){
            return AjaxResult.success("删除成功");
        }else {
            return AjaxResult.error("删除失败");
        }
    }

    @Log(title = "5.3.2.2 用户密码重置",businessType = BusinessType.UPDATE)
    @PutMapping("/reset/{userIds}")
    @ApiOperation(value = "5.3.2.2 用户密码重置",notes = "用户密码重置")
    public AjaxResult reset(@PathVariable("userIds") Long[]  userIds){
        if (userService.reset(userIds)>=1){
            return AjaxResult.success("密码重置成功");
        }else {
            return AjaxResult.error("密码重置失败");
        }
    }

   @Log(title = "5.3.2.2 用户状态修改",businessType = BusinessType.UPDATE)
    @PutMapping("/{operate}/{userIds}")
    @ApiOperation(value = "5.3.2.2 用户状态修改",notes = "用户状态修改")
    public AjaxResult editUserStatus(@PathVariable("operate") String operate,@PathVariable("userIds") Long[] userIds){
       return userService.updatetUserStatus( operate,userIds);
       }
   }



