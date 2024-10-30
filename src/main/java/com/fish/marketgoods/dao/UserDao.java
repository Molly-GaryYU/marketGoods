package com.fish.marketgoods.dao;

import com.fish.marketgoods.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserDao {

    /*根据主键删除*/

    int deleteByPrimaryKey(Integer userId);

    /*插入数据*/
    int insert(User record);

    /**
     * 选择性插入数据
     * 数据库修改是一种方式，还有一种就是在保存数据的时候时间要设置，然新增的时候没有问题，但是爱查询的时候就会出现时间问题 0 转日期错误
     */
    int insertSelective(User record);

    /*根据主键查询*/
    User selectByPrimaryKey(@Param("userId")  Integer userId);

    /*部分更新*/
    int updateByPrimaryKeySelective(User record);

    /*全部更新*/
    int updateByPrimaryKey(User record);

    /**
     *根据真实名称返回用户信息
     * @param realName 传递的真是姓名的值
     * @return 返回一个user对象
     */

    User selectUserByRealName(String realName);

    /**
     * 根据用户的电话查询用户的基本信息
     * @param phone
     * @return
     */
    User selectByPhone(int phone);

    User selectByUserId(Integer userId);

    List<User>  getAllUser();

    int checkByPhone(int phone);

    /* void addUser (int phone1, String password);*/
}