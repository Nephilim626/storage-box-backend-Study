package com.neusoft.project.storage.mapper;

import com.neusoft.project.storage.domain.AddressEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {
    AddressEntity selectDefaultAddress(AddressEntity address);
    List<AddressEntity> selectAddressList(AddressEntity address);

    int insertAddress(AddressEntity address);

    int restDefault(Long userId);

    int updateAddress(AddressEntity address);

    int updateDefault(AddressEntity address);

    int deleteAddress(AddressEntity address);
}
