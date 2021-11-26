package com.neusoft.project.storage.controller;

import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.framework.web.page.TableDataInfo;
import com.neusoft.project.storage.domain.BoxStandardEntity;
import com.neusoft.project.storage.domain.BoxStandardSelect;
import com.neusoft.project.storage.domain.BoxStandardVO;
import com.neusoft.project.storage.domain.UserEntity;
import com.neusoft.project.storage.service.BoxStandardService;
import com.neusoft.project.storage.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/backend/box/standard")
@Api(tags = {"5.3.4箱子规格"},description = "箱子规格列表（分页），箱子规格新增，箱子规格下拉列表")
public class BackendBoxStandardController extends BaseController {

    @Resource
    private BoxStandardService boxStandardService;



    @Log(title = "箱子规格列表（分页）",businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "箱子规格列表（分页）",notes = "查询箱子规格列表")
    /*TableDataInfo(分页)*/
    public TableDataInfo list( BoxStandardEntity boxStandardEntity){
        /*获取分页信息*/
        startPage();
        //查询后台端用户列表
        List<BoxStandardVO> list=boxStandardService.selectBoxStandardList(boxStandardEntity);
        //返回响应请求分页数据
        return getDataTable(list);
    }

    @Log(title = "5.3.4.2 箱子标准新增",businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ApiOperation(value = "5.3.4.2 箱子标准新增",notes = "箱子标准新增")
    public AjaxResult add(@RequestBody  BoxStandardVO boxStandardVO){
        if(boxStandardService.insertBoxStandard(boxStandardVO)>=1){
            return AjaxResult.success("新增成功");
        }else {
            return AjaxResult.error("新增失败");
        }
    }

    @Log(title = "5.3.4.3 箱子规格删除",businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    @ApiOperation(value = "5.3.4.3 箱子规格删除",notes = "箱子规格删除")
    public AjaxResult delete(@PathVariable("ids") Long[] ids){
        return  boxStandardService.deletetBoxStandard(ids);
    }

    @Log(title = "5.3.4.4 箱子规格下拉列表",businessType = BusinessType.OTHER)
    @GetMapping("/select")
    @ApiOperation(value = "5.3.4.4 箱子规格下拉列表",notes = "箱子规格下拉列表")
    public AjaxResult select(){
      List<BoxStandardSelect> list= boxStandardService.selectBoxStandard();
       if (list!=null){
           return  AjaxResult.success("查询成功",list);
       }
       return AjaxResult.error("查询失败");
    }

}
