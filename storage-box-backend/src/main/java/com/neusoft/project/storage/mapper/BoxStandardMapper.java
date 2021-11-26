package com.neusoft.project.storage.mapper;

import com.neusoft.project.storage.domain.BoxStandardEntity;
import com.neusoft.project.storage.domain.BoxStandardSelect;
import com.neusoft.project.storage.domain.BoxStandardVO;
import com.neusoft.project.storage.domain.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxStandardMapper {
    List<BoxStandardVO> selectBoxStandardList(BoxStandardEntity boxStandardEntity);

    int insertBoxStandard(BoxStandardEntity boxStandardEntity);

    int updatetBoxStandard(BoxStandardEntity boxStandardEntity);

    int deletetBoxStandard(BoxStandardEntity boxStandard);

    BoxStandardVO selectBoxStandardById(Long id);

    List<BoxStandardSelect> selectBoxStandard();
}
