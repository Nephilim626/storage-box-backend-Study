package com.neusoft.project.storage.domain;

import com.neusoft.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel(value = "用户对象",description = "用户对象")
public class UserEntity extends BaseEntity {
    /*用户ID*/
    private long userId;
  /*  部门ID*/
    private long deptId;
    /*用户账号*/
    private String userName;
    /*用户昵称*/
    private String nickName;
    /*用户类型（00系统用户；01后台端用户；02手机端用户）*/
    private String userType;
    /*用户邮箱*/
    private String email;
    /*手机号码*/
    private String phonenumber;
    /*用户性别（0男 1女 2未知）*/
    private char sex;
    /*头像地址*/
    private String avatar;
    /*密码*/
    private String password;
    /*帐号状态（0正常 1停用）*/
    private char status;
    /*最后登陆IP*/
    private String loginIp;
    /*最后登陆时间*/
    private String loginDate;

    private long currentPoints;

}
