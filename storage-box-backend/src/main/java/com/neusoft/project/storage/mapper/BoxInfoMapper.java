package com.neusoft.project.storage.mapper;

import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxInfoMapper {

    int insertBoxInfo(BoxInfo boxInfo);

    List<BoxInfoVo> selectBoxInfoList(BoxInfo boxInfo);


    int deletetInfo(BoxInfo boxInfo);


    List<BoxInfoVo> selectBoxInfoListAll(BoxInfo boxInfo);

    List<BoxStandardSelectVO> selectValidbox(BoxInfo boxInfo);

    List<BoxInfo> selectValidBoxInfoList(BoxInfo boxInfo);

    void updateInfoIsUsed(BoxInfo vBoxinfo);
}
