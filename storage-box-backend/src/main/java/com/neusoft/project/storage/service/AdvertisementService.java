package com.neusoft.project.storage.service;

import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.Advertisement;

import java.util.List;

public interface AdvertisementService {

    List<Advertisement> selectAdvertisementList(Advertisement advertisement);


    int insertAdvertisement(Advertisement advertisement);

    int updatetAdvertisement(Advertisement advertisement);

    int deleteAdvertisement(Long[] ids);

    AjaxResult updateAdvertisementStatus(String operate, Long[] ids);
}
