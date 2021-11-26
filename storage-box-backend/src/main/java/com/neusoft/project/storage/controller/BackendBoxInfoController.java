package com.neusoft.project.storage.controller;
import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.framework.web.page.TableDataInfo;
import com.neusoft.project.storage.domain.BoxInfo;
import com.neusoft.project.storage.domain.BoxInfoVo;
import com.neusoft.project.storage.domain.BoxStandardEntity;
import com.neusoft.project.storage.domain.BoxStandardVO;
import com.neusoft.project.storage.service.BoxInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/backend/box/info")
@Api(tags = {"5.3.5箱子信息"},description = "箱子信息列表（分页），箱子信息新增，箱子信息删除")
public class BackendBoxInfoController extends BaseController {

    @Resource
    private BoxInfoService boxInfoService;

    @Log(title = "箱子信息列表（分页）",businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "箱子信息列表（分页）",notes = "箱子信息列表")
    public TableDataInfo list( BoxInfo boxInfo){
        startPage();
        //查询后台端用户列表
        List<BoxInfoVo> list=boxInfoService.selectBoxInfoList(boxInfo);
        //返回响应请求分页数据
        return getDataTable(list);
    }

    @Log(title = "5.3.5.2 箱子信息新增",businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ApiOperation(value = "5.3.5.2 箱子信息新增",notes = "箱子信息新增")
    public AjaxResult add(@RequestBody  BoxInfo boxInfo){
        if(boxInfoService.insertBoxInfo(boxInfo)>=1){
            return AjaxResult.success("新增成功");
        }else {
            return AjaxResult.error("新增失败");
        }
    }

    @Log(title = "5.3.5.3 箱子信息删除",businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    @ApiOperation(value = "5.3.5.3 箱子信息删除",notes = "箱子信息删除")
    public AjaxResult delete(@PathVariable("ids") Long[] ids){
        return  boxInfoService.deletetInfo(ids);
    }





}
