package com.neusoft.project.storage.controller;

import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.framework.web.page.TableDataInfo;
import com.neusoft.project.storage.domain.AdviceVo;
import com.neusoft.project.storage.domain.PointsRecord;
import com.neusoft.project.storage.service.PointRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/app/point")
public class AppPointRecordController extends BaseController {

    @Resource
    private PointRecordService pointRecordService;

    @Log(title = "积分记录列表（分页））",businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "积分记录列表（分页）",notes = "积分记录列表（分页）")
    public TableDataInfo list(PointsRecord pointsRecord){
        startPage();
        //查询后台端用户列表
        List<PointsRecord> list=pointRecordService.selectPointRecordList(pointsRecord);
        //返回响应请求分页数据
        return getDataTable(list);
    }


    @Log(title = " 获取当前用户积分）",businessType = BusinessType.OTHER)
    @GetMapping("/select")
    @ApiOperation(value = " 获取当前用户积分",notes = " 获取当前用户积分")
    public AjaxResult select( ){

        //查询后台端用户列表
        Long list=pointRecordService.selectPointByUserId();
        //返回响应请求分页数据
        return AjaxResult.success("查询成功",list);
    }
}
