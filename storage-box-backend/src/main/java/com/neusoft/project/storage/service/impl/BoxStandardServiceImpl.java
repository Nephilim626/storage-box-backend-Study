package com.neusoft.project.storage.service.impl;

import com.neusoft.common.exception.CustomException;
import com.neusoft.common.utils.SecurityUtils;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.*;
import com.neusoft.project.storage.mapper.BoxInfoMapper;
import com.neusoft.project.storage.mapper.BoxStandardMapper;
import com.neusoft.project.storage.mapper.UserMapper;
import com.neusoft.project.storage.service.BoxStandardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BoxStandardServiceImpl implements BoxStandardService {

    @Resource
    private BoxStandardMapper boxStandardMapper;

    @Resource
    private BoxInfoMapper boxInfoMapper;

    @Override
    public List<BoxStandardVO> selectBoxStandardList(BoxStandardEntity boxStandardEntity) {
        return boxStandardMapper.selectBoxStandardList(boxStandardEntity);
    }

    @Override
    public int insertBoxStandard(BoxStandardVO boxStandardVO) {

        int count=0;
        Date date = new Date();
        boxStandardVO.setCreateBy(SecurityUtils.getUsername());
        boxStandardVO.setCreateTime(date);
        boxStandardVO.setSearchValue(boxStandardVO.getBoxStandard());
        List<BoxStandardVO> boxStandardVOList=boxStandardMapper.selectBoxStandardList(boxStandardVO);
        if (boxStandardVO.getInventoryNumber()<0){
            throw new CustomException("当前规格"+boxStandardVO.getBoxStandard()+"已存在,对应积分为"+boxStandardVOList.get(0).getBoxUnitPrice()+"库存数量不得少于0，请重新输入");
        }
        BoxInfo boxInfo =new BoxInfo();
        boxInfo.setCreateBy(SecurityUtils.getUsername());
        boxInfo.setCreateTime(date);
        boxInfo.setRemark(boxStandardVO.getRemark());
        boxInfo.setBoxStandard(boxStandardVO.getBoxStandard());
        boxInfo.setBoxUnitPrice(boxStandardVO.getBoxUnitPrice());
        boxInfo.setSearchValue(boxStandardVO.getBoxStandard());
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyMMdd");
        long code= Long.parseLong(simpleDateFormat.format(date)+"000001");
        boxInfo.setBoxCode(code);
        boxInfo.setSortNo(code);
        boxStandardVO.setSortNo(code);
       if(boxStandardVOList.size()==0){
         count+= boxStandardMapper.insertBoxStandard(boxStandardVO);
       long num = boxStandardVO.getInventoryNumber();
       for (long i=0;i<num;i++){
            boxInfoMapper.insertBoxInfo(boxInfo);
            code+=1;
            boxInfo.setBoxCode(code);
            boxInfo.setSortNo(code);
            count++;
        }
        return count;
        }
      else {
           if (boxStandardVO.getBoxUnitPrice()==boxStandardVOList.get(0).getBoxUnitPrice()){
               List<BoxInfoVo> list = boxInfoMapper.selectBoxInfoListAll(boxInfo);
               Long num;
               if(list.size() == 0){
                  num=Long.parseLong(simpleDateFormat.format(date)+"000001");
               }else{
                   num=list.get(0).getBoxCode();
               }
                  for (long j=0;j<boxStandardVO.getInventoryNumber();j++){
                      num+=1;
                      boxInfo.setBoxCode(num);
                      boxInfo.setSortNo(num);
                      boxInfoMapper.insertBoxInfo(boxInfo);
                      count++;
                  }
              }else {
                  throw new CustomException("当前规格"+boxStandardVO.getBoxStandard()+"已存在,对应积分为"+boxStandardVOList.get(0).getBoxUnitPrice()+"请重新填写");
              }
           return count;
          }
        }


    @Override
    public AjaxResult deletetBoxStandard(Long[] ids) {
       BoxStandardEntity boxStandard=new BoxStandardEntity();
       Date date=new Date();
       boxStandard.setUpdateBy(SecurityUtils.getUsername());
       boxStandard.setUpdateTime(date);
       int count=0;
        for (long id:ids
             ) {
            boxStandard.setId(id);
            if (boxStandardMapper.selectBoxStandardById(id).getTotalNumber()==0){
          count+=boxStandardMapper.deletetBoxStandard(boxStandard);
        }
        }
        if (count==0){
            return AjaxResult.error("删除失败");
        }else if(count< ids.length){
            return AjaxResult.success("未能全部删除"+"删除条数为"+count);
        }else {return AjaxResult.success("已全部删除");
        }
    }

    @Override
    public List<BoxStandardSelect> selectBoxStandard() {
        return boxStandardMapper.selectBoxStandard();
    }


}

