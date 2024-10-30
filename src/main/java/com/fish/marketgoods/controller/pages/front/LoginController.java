package com.fish.marketgoods.controller.pages.front;

import com.fish.marketgoods.pojo.entity.ShopCar;
import com.fish.marketgoods.pojo.entity.User;
import com.fish.marketgoods.pojo.vo.ShopCarVo;
import com.fish.marketgoods.service.ShopCarService;
import com.fish.marketgoods.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 check for login
 */
@Controller
@RequestMapping("/pages/front")
public class LoginController extends HttpServlet {

    @Resource
    ShopCarService shopCarService;

    @Resource
    UserService userService;

    /**
     * 你是这登录的时候才设置cookie 信息和过期时间 如果第一次访问 没有登录就没有cookie
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("loginPage")
    public String loginPage( HttpServletRequest request, HttpServletResponse response){
        // 保存cookie，实现自动登录
        Cookie nikeName = new Cookie("nikeName", "");
        // 设置cookie的持久化时间，0
        nikeName.setMaxAge(0);
        // 设置为当前项目下都携带这个cookie
        nikeName.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(nikeName);
        // 保存cookie，实现自动登录
        Cookie userId = new Cookie("userId", "");
        // 设置cookie的持久化时间，0
        userId.setMaxAge(0);
        // 设置为当前项目下都携带这个cookie
        userId.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(userId);

    /*    //获取cookie
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie :cookies){
            System.err.println("cookie的Name: " + cookie.getName());
            System.err.println("cookie的Value: " + cookie.getValue());
            if (cookie.getName().equals("userId")){
                System.err.println("有对应的cookie");
                System.err.println("cookie的时效: " + cookie.getMaxAge());
                System.err.println("cookie的Comment: " + cookie.getComment());
                System.err.println("cookie的Domain: " + cookie.getDomain());
                System.err.println("cookie的Name: " + cookie.getName());
                System.err.println("cookie的Path: " + cookie.getPath());
                System.err.println("cookie的Value: " + cookie.getValue());
                System.err.println("cookie的Secure: " + cookie.getSecure());
                System.err.println("cookie的Version: " + cookie.getVersion());
            }
        }*/
        return "pages/front.login/loginPage";
    }

    @RequestMapping("UserAddGoodsLogin")
    public String UserAddGoodsLogin(ShopCar shopCar, Model model){
        System.err.println("accept ShopCar");
        System.err.println(shopCar);
        model.addAttribute(shopCar);
        return "pages/front.login/loginPage";
    }
/*

    @RequestMapping("login")
    */
    /*需要注意一点login方法中的参数名称要与前端接受的参数的名称一样*//*

    public String login(User user , Model model, ShopCar shopCar){
         System.err.println(shopCar);
        if ("".equals(user.getPhone())||null==user.getPhone() ){ */
    /*电话为空直接判断失败*//*

            //model要在映射之前不然无法返回消息会爆红
            model.addAttribute("errorMsg","捏麻麻地手机号输入一下!");
            return "pages/front.login/loginPage"; */
    /*直接返回登录页面再次登录*//*

        }
       boolean loginResult = userService.login(user);
        if (loginResult){ //登录成功返回后台首页
            if (userService.selectUserByPhone(user.getPhone()).getNickName().equals("管理员")){
                return "pages/back/index" ;
            }else {
                shopCar.setUserId(user.getUserId());
                shopCarService.addGoodsToCar(shopCar);
                int a= Integer.parseInt(user.getPhone()) ;
                List<ShopCarVo> shopCar1=shopCarService.selectShopCarByUserId(a);
                model.addAttribute("count",shopCar1);
                model.addAttribute("User",userService.selectUserByPhone(user.getPhone()));
                return "pages/front/Customers/ShopCar";
            }
        }
        model.addAttribute("errorMsg","登录失败，连自己的手机号或密码都不知道吗！");
        return "pages/front.login/loginPage";
    }
*/


    @RequestMapping("login")
    /*需要注意一点login方法中的参数名称要与前端接受的参数的名称一样*/
    public String login(User user , Model model , HttpServletRequest request, HttpServletResponse response){
        System.err.println(user);
        if ("".equals(user.getPhone())||null==Integer.valueOf(user.getPhone())){ /*电话为空直接判断失败*/
            //model要在映射之前不然无法返回消息会爆红
            model.addAttribute("errorMsg","手机号不能为空啊亲！！！");
            return "pages/front.login/loginPage"; /*直接返回登录页面再次登录*/
        }
        boolean loginResult = userService.login(user);
        if (loginResult){ //登录成功返回后台首页
            if (userService.selectUserByPhone(user.getPhone()).getNickName().equals("管理员")){
                return "pages/back/index" ;
            }else {
                String userId1= String.valueOf(userService.selectUserByPhone(user.getPhone()).getUserId());
                String nikeName1= userService.selectUserByPhone(user.getPhone()).getNickName()+"";
                Cookie userId=new Cookie("userId",userId1);
                Cookie nikeName=new Cookie("nikeName",nikeName1);
                // 设置cookie的持久化时间，1天( 24 * 60 * 60)
                userId.setMaxAge( 24 * 60 * 60);
                nikeName.setMaxAge( 24 * 60 * 60);
                // 设置为当前项目下都携带这个cookie
                userId.setPath("/");
                nikeName.setPath("/");
                // 向客户端发送cookie
                response.addCookie(userId);
                response.addCookie(nikeName);
                //获取cookie
                Cookie[] cookies=request.getCookies();
                for (Cookie cookie :cookies){
                    System.err.println("cookie的Name: " + cookie.getName());
                    System.err.println("cookie的Value: " + cookie.getValue());
                    if (cookie.getName().equals("userId")){
                        System.err.println("有对应的cookie");
                        System.err.println("cookie的时效: " + cookie.getMaxAge());
                        System.err.println("cookie的Comment: " + cookie.getComment());
                        System.err.println("cookie的Domain: " + cookie.getDomain());
                        System.err.println("cookie的Name: " + cookie.getName());
                        System.err.println("cookie的Path: " + cookie.getPath());
                        System.err.println("cookie的Value: " + cookie.getValue());
                        System.err.println("cookie的Secure: " + cookie.getSecure());
                        System.err.println("cookie的Version: " + cookie.getVersion());
                    }
                }

                int a=user.getPhone() ;
                List<ShopCarVo> shopCar=shopCarService.selectShopCarByUserId(a);
                model.addAttribute("count",shopCar);
                model.addAttribute("User",userService.selectUserByPhone(user.getPhone()));
                return "pages/front/Customers/ShopCar";
            }

        }
        model.addAttribute("errorMsg","登录失败，连自己的手机号或密码都不知道吗！");
        return "pages/front.login/loginPage";
    }
    @RequestMapping("login2")
    /*需要注意一点login方法中的参数名称要与前端接受的参数的名称一样*/
    public String login2( ShopCar shopCar){
        System.err.println(shopCar);
        return "pages/front.login/loginPage";
    }
}
