package com.neusoft.project.storage.service;

import com.neusoft.project.storage.domain.Advice;
import com.neusoft.project.storage.domain.AdviceVo;

import java.util.List;

public interface AdviceService {
    List<AdviceVo> selectAdviceList(AdviceVo adviceVo);

    int insertAdvice(Advice advice);
}
