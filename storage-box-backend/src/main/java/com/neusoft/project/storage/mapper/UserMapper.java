package com.neusoft.project.storage.mapper;

import com.neusoft.project.storage.domain.PassWord;
import com.neusoft.project.storage.domain.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<UserEntity> selectUserList(UserEntity userEntity);
    int insertUser(UserEntity userEntity);
    int updatetUser(UserEntity userEntity);
    int updatetUserStatus(UserEntity userEntity);
    int deleteUser(long userid);
    int reset(UserEntity userEntity);

    UserEntity selectUserByUserId(Long userId);
    int updatePassword(UserEntity user);

    UserEntity selectUserByUserName(String userName);

    UserEntity selectUserListApp(UserEntity userEntity);

    int addUserPoints(UserEntity userEntity);
}
