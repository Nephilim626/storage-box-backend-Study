package com.neusoft.project.storage.service;

import com.neusoft.project.storage.domain.AddressEntity;

import java.util.List;

public interface AddressService {
    AddressEntity selectDefaultAddress(AddressEntity address);
    List<AddressEntity> selectAddressList(AddressEntity address);

    int insertAddress(AddressEntity address);

    int updateAddress(AddressEntity address);

    int updateDefault(AddressEntity address);

    int deleteAddress(AddressEntity address);
}
