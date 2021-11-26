package com.neusoft.project.storage.service.impl;

import com.neusoft.common.exception.CustomException;
import com.neusoft.common.utils.DateUtils;
import com.neusoft.common.utils.SecurityUtils;
import com.neusoft.framework.web.domain.AjaxResult;
import com.neusoft.project.storage.domain.PassWord;
import com.neusoft.project.storage.domain.PointsRecord;
import com.neusoft.project.storage.domain.UserEntity;
import com.neusoft.project.storage.mapper.PointsMapper;
import com.neusoft.project.storage.mapper.UserMapper;
import com.neusoft.project.storage.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private  PointsMapper pointsMapper;

    @Override
    public List<UserEntity> selectUserList(UserEntity userEntity) {

        return userMapper.selectUserList(userEntity);
    }

    @Override
    public int insertUser(UserEntity userEntity) {
        userEntity.setUserType("01");
        //密码封装
        userEntity.setPassword(SecurityUtils.encryptPassword("123456"));

        userEntity.setStatus('0');

        userEntity.setCreateBy(SecurityUtils.getUsername());

        Date date = new Date();

        userEntity.setCreateTime(date);

        int count = userMapper.insertUser(userEntity);

        return count;
    }

    @Override
    public int insertCustomer(UserEntity userEntity) {
        userEntity.setUserType("02");
        //密码封装
        userEntity.setPassword(SecurityUtils.encryptPassword("123456"));

        userEntity.setStatus('0');

        userEntity.setCreateBy(SecurityUtils.getUsername());

        Date date = new Date();

        userEntity.setCreateTime(date);

        int count = userMapper.insertUser(userEntity);

        return count;
    }


    @Override
    public int updatetUser(UserEntity userEntity) {

        userEntity.setUpdateBy(SecurityUtils.getUsername());
        Date date = new Date();

        userEntity.setUpdateTime(date);
        return userMapper.updatetUser(userEntity);
    }


    @Override
    public int deleteUser(Long[] userIds) {
            int count=0;
        for (int i = 0; i < userIds.length; i++) {
           count+= userMapper.deleteUser(userIds[i]);
        }
        return count;
    }

    @Override
    public int reset(Long[] userIds) {
        int count=0;
        for (int i = 0; i < userIds.length; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setPassword(SecurityUtils.encryptPassword("123456"));
            ;
            userEntity.setUpdateBy(SecurityUtils.getUsername());
            Date date = new Date();
            userEntity.setUserId(userIds[i]);
            userEntity.setUpdateTime(date);
          count+=  userMapper.reset(userEntity);

        }
        return count;
    }

    @Override
    public AjaxResult updatetUserStatus(String operate, Long[] userIds) {
        if (operate.equals("enable")) {
            int count = 0;
            for (int i = 0; i < userIds.length; i++) {
                UserEntity user=userMapper.selectUserByUserId(userIds[i]);
                if (user.getStatus()=='0'){
                    throw  new CustomException("请勿重复启用");
                }
                UserEntity userEntity = new UserEntity();
                userEntity.setUpdateBy(SecurityUtils.getUsername());
                Date date = new Date();
                userEntity.setUserId(userIds[i]);
                userEntity.setUpdateTime(date);
                userEntity.setStatus('0');
                count+=userMapper.updatetUserStatus(userEntity);

            }
            if (count >= 1) {
                return AjaxResult.success("启用成功");
            }
                return AjaxResult.error("启用失败");

        } else if (operate.equals("disable")) {
            int count = 0;
            for (int i = 0; i < userIds.length; i++) {
                UserEntity user=userMapper.selectUserByUserId(userIds[i]);
                if (user.getStatus()=='1'){
                    throw  new CustomException("请勿重复停用");
                }
                UserEntity userEntity = new UserEntity();
                userEntity.setUpdateBy(SecurityUtils.getUsername());
                Date date = new Date();
                userEntity.setUserId(userIds[i]);
                userEntity.setUpdateTime(date);
                userEntity.setStatus('1');
                count+= userMapper.updatetUserStatus(userEntity);
            }
            if (count >= 1) {
                return AjaxResult.success("停用成功");
            }
                return AjaxResult.error("停用失败");
        }else {
            return AjaxResult.error("用户状态修改失败");
        }
    }
    @Override
    public int updatePassword(PassWord passWord) {
        int count=0;
       UserEntity user= userMapper.selectUserByUserName(SecurityUtils.getUsername());
        if(SecurityUtils.matchesPassword(passWord.getOldPassword(),user.getPassword())){
            UserEntity userEntity=new UserEntity();
            userEntity.setUpdateBy(SecurityUtils.getUsername());
            userEntity.setUpdateTime(DateUtils.getNowDate());
            userEntity.setUserName(SecurityUtils.getUsername());
            userEntity.setPassword(SecurityUtils.encryptPassword(passWord.getNewPassword()));
            count=userMapper.updatePassword(userEntity);
            return count;
        }else {
        return count;
    }
    }

    //5.2.1 APP注册
    @Override
    public AjaxResult registUser(UserEntity userEntity) {
        userEntity.setSearchValue(userEntity.getUserName());
        if(userMapper.selectUserListApp(userEntity)!=null){
            return AjaxResult.error("新增用户'"+userEntity.getUserName()+"'失败,登陆账号已存在");
        }
        userEntity.setSearchValue(userEntity.getPhonenumber());
        if(userMapper.selectUserListApp(userEntity)!=null){
            return AjaxResult.error("新增用户'"+userEntity.getUserName()+"'失败,手机号码已存在");
        }
        userEntity.setSearchValue(userEntity.getEmail());
        if(userMapper.selectUserListApp(userEntity)!=null){
            return AjaxResult.error("新增用户'"+userEntity.getUserName()+"'失败,邮箱已存在");
        }

        userEntity.setUserType("02");
        //密码封装
        userEntity.setPassword(SecurityUtils.encryptPassword(userEntity.getPassword()));

        userEntity.setStatus('0');

        userEntity.setCreateTime(DateUtils.getNowDate());

        userEntity.setCurrentPoints(626);
        userEntity.setCreateBy(userEntity.getUserName());
       if(userMapper.insertUser(userEntity)>=1){
           //插入积分记录
        UserEntity user= userMapper.selectUserByUserName(userEntity.getUserName());
           PointsRecord points=new PointsRecord();
           points.setUserId(user.getUserId());
           short num=1;
           points.setWay(num);
           points.setCreateBy(user.getUserName());
           points.setCreateTime(DateUtils.getNowDate());

           SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyMMdd");
           long code= Long.parseLong(simpleDateFormat.format(DateUtils.getNowDate())+"000001");
           points.setSortNo(code);
           long num1 =626;
           points.setPoints(num1);
           pointsMapper.insertPointRecord(points);
           return AjaxResult.success("注册成功");
       }

        return AjaxResult.error("注册失败");
    }

    //增加用户积分
    @Override
    public int updatetUserPoints(PointsRecord points) {
        UserEntity userEntity=new UserEntity();
        userEntity.setUserId(SecurityUtils.getUserId());
        UserEntity user=userMapper.selectUserByUserId(SecurityUtils.getUserId());
        userEntity.setCurrentPoints(user.getCurrentPoints()+points.getPoints());
        userEntity.setUpdateBy(SecurityUtils.getUsername());
        userEntity.setUpdateTime(DateUtils.getNowDate());
        points.setUserId(user.getUserId());
        short num=2;
        points.setWay(num);
        points.setCreateBy(user.getUserName());
        points.setCreateTime(DateUtils.getNowDate());
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyMMdd");
        long code= Long.parseLong(simpleDateFormat.format(DateUtils.getNowDate())+"000001");
        points.setSortNo(code);
        pointsMapper.insertPointRecord(points);
        return userMapper.addUserPoints(userEntity);
    }
}
