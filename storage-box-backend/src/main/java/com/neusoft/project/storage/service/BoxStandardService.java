package com.neusoft.project.storage.service;

import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.BoxStandardEntity;
import com.neusoft.project.storage.domain.BoxStandardSelect;
import com.neusoft.project.storage.domain.BoxStandardVO;

import java.util.List;

public interface BoxStandardService {
    List<BoxStandardVO> selectBoxStandardList(BoxStandardEntity boxStandardEntity);

    int insertBoxStandard(BoxStandardVO boxStandardVO);

    AjaxResult deletetBoxStandard(Long[] ids);

    List<BoxStandardSelect> selectBoxStandard();
}
