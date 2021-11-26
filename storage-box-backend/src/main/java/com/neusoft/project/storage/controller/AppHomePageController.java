package com.neusoft.project.storage.controller;

import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.Advertisement;
import com.neusoft.project.storage.domain.PointsRecord;
import com.neusoft.project.storage.service.AdvertisementService;
import com.neusoft.project.storage.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/app/advertisement")
@Api(tags = {"5.2.2首页"},description = "首页")
public class AppHomePageController extends BaseController {

   @Resource
    private AdvertisementService advertisementService;

   @Resource
   private IUserService userService;

    @Log(title = "广告列表（非分页）",businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "广告列表（非分页）",notes = "查询广告列表")
    public AjaxResult list(Advertisement advertisement){

        List<Advertisement> list=advertisementService.selectAdvertisementList(advertisement);
        //返回响应请求分页数据
        if (list.size()!=0){
            return AjaxResult.success("查询成功",list);
        }
        return AjaxResult.error("查询失败");
    }

    @Log(title = "5.2.2.2广告积分获取",businessType = BusinessType.OTHER)
    @PutMapping("/points")
    @ApiOperation(value = "广告积分获取",notes = "广告积分获取")
    public AjaxResult points(@RequestBody PointsRecord points){
       if( userService.updatetUserPoints(points)>=1){
           return AjaxResult.success("获取积分成功");
       }
        return AjaxResult.error("获取积分失败");
    }

}
