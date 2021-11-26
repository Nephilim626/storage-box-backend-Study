package com.neusoft.project.storage.service.impl;

import com.neusoft.common.exception.CustomException;
import com.neusoft.common.utils.DateUtils;
import com.neusoft.common.utils.SecurityUtils;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.Advertisement;
import com.neusoft.project.storage.domain.UserEntity;
import com.neusoft.project.storage.mapper.AdvertisementMapper;
import com.neusoft.project.storage.service.AdvertisementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class AdvertisementServiceImpl implements AdvertisementService {

    @Resource
    private AdvertisementMapper advertisementMapper;


    @Override
    public List<Advertisement> selectAdvertisementList(Advertisement advertisement) {
        return advertisementMapper.selectAdvertisementList(advertisement);
    }

    @Override
    public int insertAdvertisement(Advertisement advertisement) {
        advertisement.setCreateBy(SecurityUtils.getUsername());
        advertisement.setCreateTime(DateUtils.getNowDate());
        advertisement.setSearchValue(advertisement.getTitle());
        short num1=new Short("1");
        advertisement.setIsEnable(num1);
       List<Advertisement> list =advertisementMapper.selectAdvertisementListDesc(advertisement);
        for (Advertisement a:list
             ) {
            if (advertisement.getSortNo()==a.getSortNo()){
                throw new CustomException("请勿重复添加");
            }

        }
      /*  if (list.size()==0){
            advertisement.setSortNo(1);
        }else {
           long num = list.get(0).getSortNo();
           num++;
            advertisement.setSortNo(num);
        }*/
        return advertisementMapper.insertAdvertisement(advertisement);
    }

    @Override
    public int updatetAdvertisement(Advertisement advertisement) {

        advertisement.setUpdateBy(SecurityUtils.getUsername());
        advertisement.setUpdateTime(DateUtils.getNowDate());
        advertisement.setSearchValue(advertisement.getTitle());
        List<Advertisement> list = advertisementMapper.selectAdvertisementList(advertisement);
        for (Advertisement a:list
        ) {
            if (advertisement.getSortNo()==a.getSortNo()){
                throw new CustomException("请勿重复添加,已存在"+advertisement.getTitle()+"广告,序号为"+advertisement.getSortNo()+"请重新输入");
            }
        }

        return advertisementMapper.updatetAdvertisement(advertisement);
    }

    @Override
    public int deleteAdvertisement(Long[] ids) {
        int count =0;
        for (int i = 0; i < ids.length; i++) {
            count+= advertisementMapper.deleteAdvertisement(ids[i]);
        }
        return count;
    }

    @Override
    public AjaxResult updateAdvertisementStatus(String operate, Long[] ids) {
        if (operate.equals("enable")) {
            int count = 0;
            for (int i = 0; i < ids.length; i++) {
                Advertisement advertisement1=advertisementMapper.selectAdvertisementById(ids[i]);
                if (advertisement1.getIsEnable()==0){
                    throw new CustomException("Id为"+advertisement1.getId()+"已启用,请勿重复启用");
                }
                Advertisement advertisement = new Advertisement();
                advertisement.setUpdateBy(SecurityUtils.getUsername());
                advertisement.setId(ids[i]);
                advertisement.setUpdateTime(DateUtils.getNowDate());
                short num=0;
                advertisement.setIsEnable(num);
                count+=advertisementMapper.updatetAdvertisementStatus(advertisement);

            }
            if (count >= 1) {
                return AjaxResult.success("启用成功");
            }
            return AjaxResult.error("启用失败");

        } else if (operate.equals("disable")) {
            int count = 0;
            for (int i = 0; i < ids.length; i++) {
                Advertisement advertisement1=advertisementMapper.selectAdvertisementById(ids[i]);
                if (advertisement1.getIsEnable()==1){
                    throw new CustomException("Id为"+advertisement1.getId()+"已停用,请勿重复停用");
                }
                Advertisement advertisement = new Advertisement();
                advertisement.setUpdateBy(SecurityUtils.getUsername());
                advertisement.setId(ids[i]);
                advertisement.setUpdateTime(DateUtils.getNowDate());
                short num=1;
                advertisement.setIsEnable(num);
                count+=advertisementMapper.updatetAdvertisementStatus(advertisement);
            }
            if (count >= 1) {
                return AjaxResult.success("停用成功");
            }
            return AjaxResult.error("停用失败");
        }else {
            return AjaxResult.error("用户状态修改失败");
        }
    }


}
