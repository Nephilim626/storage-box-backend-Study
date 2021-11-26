package com.neusoft.project.storage.controller;

import com.neusoft.framework.aspectj.lang.annotation.Log;
import com.neusoft.framework.aspectj.lang.enums.BusinessType;
import com.neusoft.framework.web.controller.BaseController;
import com.neusoft.framework.web.page.TableDataInfo;
import com.neusoft.project.storage.domain.AdviceVo;
import com.neusoft.project.storage.domain.BoxInfo;
import com.neusoft.project.storage.domain.BoxInfoVo;
import com.neusoft.project.storage.service.AdviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/backend/advice")
@Api(tags = {"5.3.8意见建议"},description = "意见建议列表（分页）")
public class BackendAdviceController extends BaseController {

    @Resource
    private AdviceService adviceService;


    @Log(title = "意见建议列表（分页）",businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "意见建议列表（分页）",notes = "意见建议列表")
    public TableDataInfo list( AdviceVo adviceVo){
        startPage();
        //查询后台端用户列表
        List<AdviceVo> list=adviceService.selectAdviceList(adviceVo);
        //返回响应请求分页数据
        return getDataTable(list);
    }

}
