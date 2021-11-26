package com.neusoft.project.storage.mapper;

import com.neusoft.project.storage.domain.PointsRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointsMapper {

    int insertPointRecord(PointsRecord points);

    List<PointsRecord> selectPointRecordList(PointsRecord pointsRecord);

    Long selectPointByUserId(Long userId);
}
