package com.neusoft.project.storage.controller;

import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.framework.web.page.TableDataInfo;
import com.neusoft.project.storage.domain.Advertisement;
import com.neusoft.project.storage.domain.BoxInfo;
import com.neusoft.project.storage.domain.BoxInfoVo;
import com.neusoft.project.storage.domain.UserEntity;
import com.neusoft.project.storage.service.AdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/backend/advertisement")
@Api(tags = {"5.3.7广告管理"},description = "广告列表（分页），广告新增，广告编辑")
public class BackendAdvertisementController extends BaseController {


    @Resource
    private AdvertisementService advertisementService;

    @Log(title = "广告列表（分页）",businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "广告列表（分页）",notes = "广告列表")
    public TableDataInfo list( Advertisement advertisement){
        startPage();
        //查询后台端用户列表
        List<Advertisement> list=advertisementService.selectAdvertisementList(advertisement);
        //返回响应请求分页数据
        return getDataTable(list);
    }

    @Log(title = "5.3.7.2 广告新增",businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ApiOperation(value = "5.3.7.2 广告新增",notes = "广告新增")
    public AjaxResult add(@RequestBody  Advertisement advertisement){
        if(advertisementService.insertAdvertisement(advertisement)>=1){
            return AjaxResult.success("新增成功");
        }else {
            return AjaxResult.error("新增失败");
        }
    }

    @Log(title = "5.3.7.3 广告编辑",businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    @ApiOperation(value = "5.3.7.3 广告编辑",notes = "广告编辑")
    public  AjaxResult edit( @RequestBody Advertisement advertisement){
        if(advertisementService.updatetAdvertisement(advertisement)>=1){
            return AjaxResult.success("修改成功");
        }else {
            return AjaxResult.error("修改失败");
        }
    }

    @Log(title = "5.3.7.4 广告删除",businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    @ApiOperation(value = "5.3.7.4 广告删除",notes = "广告删除")
    public AjaxResult delete(@PathVariable("ids") Long[] ids){
        if (advertisementService.deleteAdvertisement(ids)>=1){
            return AjaxResult.success("删除成功");
        }else {
            return AjaxResult.error("删除失败");
        }
    }

    @Log(title = "5.3.7.5 广告状态修改",businessType = BusinessType.UPDATE)
    @PutMapping("/{operate}/{ids}")
    @ApiOperation(value = "5.3.7.5 广告状态修改",notes = "广告状态修改")
    public AjaxResult editAdvertisementStatus(@PathVariable("operate") String operate,@PathVariable("ids") Long[] ids){
        return advertisementService.updateAdvertisementStatus( operate,ids);
    }

}
