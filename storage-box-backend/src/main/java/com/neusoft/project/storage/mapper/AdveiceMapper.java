package com.neusoft.project.storage.mapper;

import com.neusoft.project.storage.domain.Advice;
import com.neusoft.project.storage.domain.AdviceVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdveiceMapper {
    List<AdviceVo> selectAdviceList(AdviceVo adviceVo);

    int insertAdvice(Advice advice);
}
