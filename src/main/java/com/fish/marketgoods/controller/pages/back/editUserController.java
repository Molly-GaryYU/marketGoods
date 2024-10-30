package com.fish.marketgoods.controller.pages.back;

import com.fish.marketgoods.pojo.entity.User;
import com.fish.marketgoods.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("pages/back/editUserController")
public class editUserController {
    @Resource
    UserService userService;

    @RequestMapping("toEditUser")
    String toEditUser(){
        return "pages/back/user/user-edit";
    }


    @RequestMapping("addUserByManger")
    String addUserByManger(User user){
        System.err.println(user);
      /*  if (userService.addUserByManger(user)){
            return "success";
        }else {
            return "defeat";
        }*/
      return  null;
    }






}
