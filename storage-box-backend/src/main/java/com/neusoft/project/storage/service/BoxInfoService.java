package com.neusoft.project.storage.service;

import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.BoxInfo;
import com.neusoft.project.storage.domain.BoxInfoVo;
import com.neusoft.project.storage.domain.BoxStandardSelectVO;
import com.neusoft.project.storage.domain.BoxStandardVO;

import java.util.List;

public interface BoxInfoService {
    List<BoxInfoVo> selectBoxInfoList(BoxInfo boxInfo);

    int insertBoxInfo(BoxInfo boxInfo);

    AjaxResult deletetInfo(Long[] ids);

    List<BoxStandardSelectVO> selectValidbox(BoxInfo boxInfo);
}
