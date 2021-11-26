package com.neusoft.project.storage.mapper;

import com.neusoft.project.storage.domain.Advertisement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementMapper {
    List<Advertisement> selectAdvertisementList(Advertisement advertisement);

    int insertAdvertisement(Advertisement advertisement);

    List<Advertisement> selectAdvertisementListDesc(Advertisement advertisement);

    int updatetAdvertisement(Advertisement advertisement);

    int deleteAdvertisement(Long id);

    int updatetAdvertisementStatus(Advertisement advertisement);

    Advertisement selectAdvertisementById(Long id);
}
