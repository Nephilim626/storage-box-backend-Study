package com.neusoft.project.storage.service.impl;

import com.neusoft.common.utils.SecurityUtils;
import com.neusoft.project.storage.domain.PointsRecord;
import com.neusoft.project.storage.mapper.PointsMapper;
import com.neusoft.project.storage.service.PointRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class PointRecordServiceImpl implements PointRecordService {

    @Resource
    private PointsMapper pointsMapper;


    @Override
    public List<PointsRecord> selectPointRecordList(PointsRecord pointsRecord) {
        return pointsMapper.selectPointRecordList(pointsRecord);
    }

    @Override
    public Long selectPointByUserId() {
        return pointsMapper.selectPointByUserId(SecurityUtils.getUserId());
    }
}
