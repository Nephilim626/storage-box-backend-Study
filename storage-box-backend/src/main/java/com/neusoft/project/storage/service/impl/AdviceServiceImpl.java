package com.neusoft.project.storage.service.impl;

import com.neusoft.common.utils.DateUtils;
import com.neusoft.common.utils.SecurityUtils;
import com.neusoft.project.storage.domain.Advice;
import com.neusoft.project.storage.domain.AdviceVo;
import com.neusoft.project.storage.mapper.AdveiceMapper;
import com.neusoft.project.storage.mapper.AdvertisementMapper;
import com.neusoft.project.storage.service.AdviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Service
public class AdviceServiceImpl implements AdviceService {

    @Resource
    private AdveiceMapper adveiceMapper;

    @Override
    public List<AdviceVo> selectAdviceList(AdviceVo adviceVo) {
        return adveiceMapper.selectAdviceList(adviceVo);
    }

    @Override
    public int insertAdvice(Advice advice) {
        advice.setUserId(SecurityUtils.getUserId());
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyMMdd");
        long code= Long.parseLong(simpleDateFormat.format(DateUtils.getNowDate())+"000001");
        advice.setSortNo(code);
        advice.setCreateBy(SecurityUtils.getUsername());
        advice.setCreateTime(DateUtils.getNowDate());
        return adveiceMapper.insertAdvice(advice);
    }
}
