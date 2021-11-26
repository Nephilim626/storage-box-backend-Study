package com.neusoft.project.storage.service.impl;

import com.neusoft.common.exception.CustomException;
import com.neusoft.common.utils.DateUtils;
import com.neusoft.common.utils.SecurityUtils;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.*;
import com.neusoft.project.storage.mapper.BoxInfoMapper;
import com.neusoft.project.storage.mapper.BoxStandardMapper;
import com.neusoft.project.storage.service.BoxInfoService;
import com.neusoft.project.storage.service.BoxStandardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BoxInfoServiceImpl implements BoxInfoService {

    @Resource
    private BoxInfoMapper boxInfoMapper;


    @Resource
    private BoxStandardMapper boxStandardMapper;


    @Override
    public List<BoxInfoVo> selectBoxInfoList(BoxInfo boxInfo) {
        return boxInfoMapper.selectBoxInfoList(boxInfo);
    }


    @Override
    public int insertBoxInfo(BoxInfo boxInfo) {
        boxInfo.setCreateBy(SecurityUtils.getUsername());
        boxInfo.setCreateTime(DateUtils.getNowDate());
        boxInfo.setSearchValue(boxInfo.getBoxStandard());
        BoxStandardEntity boxStandardEntity=new BoxStandardEntity();
        boxStandardEntity.setSearchValue(boxInfo.getBoxStandard());
       List<BoxStandardVO> listBox= boxStandardMapper.selectBoxStandardList(boxStandardEntity);
        if (listBox.size()==0){
            throw new CustomException("当前没有此规格的箱子,请重新添加");
        }
        List<BoxInfoVo> list = boxInfoMapper.selectBoxInfoList(boxInfo);
        Long num;
        if(list.size() == 0){
            num=Long.parseLong(DateUtils.getNowDate()+"000001");
        }else{
            num=list.get(0).getBoxCode();
        }
        num++;
        boxInfo.setBoxCode(num);
        boxInfo.setSortNo(num);
        int count =boxInfoMapper.insertBoxInfo(boxInfo);
        return count;
    }

    @Override
    public AjaxResult deletetInfo(Long[] ids) {
        int count=0;
        BoxInfo boxInfo=new BoxInfo();
        Date date=new Date();
        boxInfo.setUpdateBy(SecurityUtils.getUsername());
        boxInfo.setUpdateTime(date);
        for (Long id:ids
             ) {
            boxInfo.setId(id);
            count += boxInfoMapper.deletetInfo(boxInfo);
        }
        if (count==0){
            return AjaxResult.error("删除失败");
        }
        else {return AjaxResult.success("删除成功");
        }
    }

    @Override
    public List<BoxStandardSelectVO> selectValidbox(BoxInfo boxInfo) {
       List<BoxStandardSelectVO> list = boxInfoMapper.selectValidbox(boxInfo);

        return list;
    }
}
