package com.neusoft.project.storage.service;

import com.neusoft.project.storage.domain.PointsRecord;

import java.util.List;

public interface PointRecordService {
    List<PointsRecord> selectPointRecordList(PointsRecord pointsRecord);

    Long selectPointByUserId();
}
