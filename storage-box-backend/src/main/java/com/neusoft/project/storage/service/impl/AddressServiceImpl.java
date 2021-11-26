package com.neusoft.project.storage.service.impl;

import com.neusoft.common.utils.SecurityUtils;
import com.neusoft.project.storage.domain.AddressEntity;
import com.neusoft.project.storage.mapper.AddressMapper;
import com.neusoft.project.storage.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public AddressEntity selectDefaultAddress(AddressEntity address) {
        address.setUserId(SecurityUtils.getUserId());
       return addressMapper.selectDefaultAddress(address);
    }
    @Override
    public List<AddressEntity> selectAddressList(AddressEntity address) {
        address.setUserId(SecurityUtils.getUserId());
        return addressMapper.selectAddressList(address);
    }

    @Override
    public int insertAddress(AddressEntity address) {
        address.setUserId(SecurityUtils.getUserId());
        address.setCreateBy(SecurityUtils.getUsername());
        address.setDelFlag("0");
        if (addressMapper.selectAddressList(address).size() == 0) {
            short num=0;
            address.setIsDefault(num);
        }
        if (address.getIsDefault() == 0) {
            addressMapper.restDefault(SecurityUtils.getUserId());
        }
        return addressMapper.insertAddress(address);
    }

    @Override
    public int updateAddress(AddressEntity address) {
        if (address.getIsDefault() == 0) {
            addressMapper.restDefault(SecurityUtils.getUserId());
        }
        address.setUpdateBy(SecurityUtils.getUsername());
        return addressMapper.updateAddress(address);
    }

    /**
     * 修改，默认地址
     *
     * @param address
     * @return
     */
    @Override
    public int updateDefault(AddressEntity address) {
        address.setUpdateBy(SecurityUtils.getUsername());
        addressMapper.restDefault(SecurityUtils.getUserId());
        return addressMapper.updateDefault(address);
    }

    @Override
    public int deleteAddress(AddressEntity address) {
        address.setUserId(SecurityUtils.getUserId());
        address.setUpdateBy(SecurityUtils.getUsername());
        if (addressMapper.selectDefaultAddress(address) != null) {
            if (addressMapper.selectDefaultAddress(address).getId() == address.getId()) {
                if (addressMapper.deleteAddress(address) > 0) {
                    AddressEntity ad = new AddressEntity();
                    ad.setUserId(SecurityUtils.getUserId());
                    ad.setId(addressMapper.selectAddressList(ad).get(0).getId());
                    return addressMapper.updateDefault(ad);
                }
                return 0;
            } else {
                return addressMapper.deleteAddress(address);
            }
        }
        return 0;
    }

}
