package com.neusoft.project.storage.service;

import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.PassWord;
import com.neusoft.project.storage.domain.PointsRecord;
import com.neusoft.project.storage.domain.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> selectUserList(UserEntity userEntity);

    int insertUser(UserEntity userEntity);

    int updatetUser(UserEntity userEntity);

    int deleteUser(Long[] userEntity);

    int reset(Long[] userIds);

    AjaxResult updatetUserStatus(String operate, Long[] userIds);
    int insertCustomer(UserEntity userEntity);

    int updatePassword(PassWord passWord);

    AjaxResult registUser(UserEntity userEntity);

    int updatetUserPoints(PointsRecord points);
}
