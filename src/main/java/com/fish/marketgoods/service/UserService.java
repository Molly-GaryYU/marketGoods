package com.fish.marketgoods.service;

import com.fish.marketgoods.pojo.entity.User;

import java.util.List;

/**
 * 关于用于的一些操作
 */
/*    快速创建接口实现类alt+enter 选着implement*/
public interface UserService {

    boolean login(User user);

     User selectUserByPhone(int phone);

     User  selectUserById(Integer userId);

    boolean addUserByManger(User user);

    List<User>  getAllUser();

    boolean  updateUser(User user);

    void addUser(int phone, String password);

    boolean checkByPhone(int phone);

    String register(int phone, String password, String checkPassword);
}

