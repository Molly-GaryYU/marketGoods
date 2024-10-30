package com.fish.marketgoods.service.impl;

import com.fish.marketgoods.dao.UserDao;
import com.fish.marketgoods.pojo.entity.User;
import com.fish.marketgoods.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service //交给springIoc容器管理 后边就可以直接自动装配使用了
public class UserServiceImpl implements UserService {

    @Resource
    UserDao usreDao;

    @Override
    public boolean login(User user) {
        User dbUser= usreDao.selectByPhone(user.getPhone());
        if (dbUser==null){
            return  false;
        }else {
            if (user.getPassword()==null){ //防止下方判断报错
                return  false;
            }
            //比较字符串要用equal方法
            if (user.getPassword().equals(dbUser.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public User selectUserByPhone(int phone) {

        return usreDao.selectByPhone(phone);
    }

    @Override
    public User selectUserById(Integer userId) {
        return usreDao.selectByUserId(userId);

    }

    @Override
    public boolean addUserByManger(User user) {
        if (usreDao.insertSelective(user)==0){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public List<User> getAllUser() {

        return usreDao.getAllUser();
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }


    @Override
    public void addUser(int phone, String password) {
        User user1=new User();
        user1.setPhone(phone);
        user1.setPassword(password);
        usreDao.insert(user1);
    }

    @Override
    public boolean checkByPhone(int phone) {

        System.err.println("service");
        if (usreDao.checkByPhone(phone)==0){
            System.err.println("false");
            return  false;
        }else{
            System.err.println("false");
            return true;
        }

    }

    @Override
    public String register(int phone, String password, String checkPassword) {

        if (phone!=0 && password!=null  && checkPassword!=null){
            System.err.println("进入服务类 都不为空");
            if(password.equals(checkPassword)){
                System.err.println("密码确认成功");
              User  user1 =usreDao.selectByPhone(phone);
                if ( "".equals(user1.getPhone())||null==Integer.valueOf(user1.getPhone()) ){
                    System.err.println("手机已注册");
                    return "抱歉您的手机已注册";
                }
                else {
                    System.err.println("注册进入");
                    usreDao.insertSelective(user1);
                    System.err.println("注册成功");
                    return "注册成功请点击返回首页";
                }
            }else{
                return "抱歉您前后输入的密码不相同";
            }
        }else {
            return "抱歉输入不能为空";
        }
    }

}
