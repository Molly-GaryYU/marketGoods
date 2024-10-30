package com.fish.marketgoods.controller.pages.front;

import com.fish.marketgoods.pojo.entity.Goods;
import com.fish.marketgoods.pojo.entity.ShopCar;
import com.fish.marketgoods.pojo.entity.User;
import com.fish.marketgoods.pojo.entity.UserOrder;
import com.fish.marketgoods.pojo.vo.ShopCarVo;
import com.fish.marketgoods.service.GoodsService;
import com.fish.marketgoods.service.ShopCarService;
import com.fish.marketgoods.service.UserOrderService;
import com.fish.marketgoods.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
check for login
 */
@Controller
@RequestMapping("/pages/front/Customers")
public class UserController {


    @Resource
    UserService userService;
    @Resource
    ShopCarService shopCarService;
    @Resource
    GoodsService goodsService;
    @Resource
    UserOrderService userOrderService;

    //先跳转到页面，页面再通过ajax申请数据即可
    @RequestMapping("toUserInfo")
    String toEditUser(){
        return "pages/back/user/user-add";
    }

 /*   @ResponseBody
    @PostMapping("updateUser")
    String updateUser(@RequestBody User user ){
        System.err.println(user);
        if (  userService.updateUser(user)){
            return "update success"  ;
        }else {
            return "update default"  ;
        }
    }
*/

    @RequestMapping("toRegister")
    public String toRegister(){

        return "pages/front/Customers/register";
    }

    @ResponseBody
    @RequestMapping("getAllUser")
    List<User> getAllUser(){
       System.err.println(userService.getAllUser());
        return  userService.getAllUser();
    }

    @RequestMapping("ShopCar")
   public String ShopCar(Model model,HttpServletRequest request){
        boolean flag=true;
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie : cookies ){
            if (cookie.getName().equals("userId")){
                List<ShopCarVo> shopCar=shopCarService.selectShopCarByUserId(Integer.parseInt(cookie.getValue()));
                model.addAttribute("count", shopCar);
                model.addAttribute("User" ,userService.selectUserById(Integer.parseInt(cookie.getValue())));
                model.addAttribute("alertInfo",null);
                flag=false;
            }
        }
        if(flag) {
            List<ShopCarVo> shopCar=shopCarService.selectShopCarByUserId(4);
            model.addAttribute("count",shopCar);
            model.addAttribute("User" ,userService.selectUserByPhone(4));
            model.addAttribute("alertInfo","您好，请先登录！");
        }

        return "pages/front/Customers/ShopCar";
    }

    /**
     *  deleteGoodsByGoodsId/{id} 这里的{}是请求占位符 你的请求应该是 deleteGoodsByGoodsId/123 这个样式
     *  http://localhost:8080/pages/front/Customers/deleteGoodsByGoodsId/?id=1  为啥要带上？id=  这个不是get url 传参
     * 记住这个是请求占位符 {} 这个是请求前是使用{}占位，只有在请求的时候才知道请求的资源。因为是Integer 修饰 所以是所有的数字都可以  一般用于主键查询 删除 但是我有点不明白?是为什么会出现的我没打
     * 那是因为你的前端的框架处理的 也就是（） deleteGoodsByGoodsId/?id=1   deleteGoodsByGoodsId/(id=${c.getGoodsId()})}  （）应该是前端框架处理的占位符。会将（）里的解析完前面拼接一个？
     * deleteGoodsByGoodsId/'+ ${c.getGoodsId()} 这种就是使用简单的字符串拼接 没有（） 就不会走框架  看明白没这个明白了 所以？不是这次报错的原因
     * ？在url中是什么作用你知道吗  不知道  ？是将url与参数拼接在一起使用的   懂了 所以你这个报错就是你前端的url与你后端定义的格式不一致导致的， 记住404 就是前端请求地址与后端定义的不一致造成的。
     * 不一致： 1、就是url 写错了 2、url后端不存在
     * @param model
     * @param id
     * @return
     */
    @GetMapping("deleteGoodsByGoodsId/{id}")/*/{goodsId}  /{Id}   @PathVariable("Id") Integer Id,*/
    public String deleteGoodsByGoodsId( Model model, @PathVariable("id") Integer id){
        shopCarService.deleteGoodsByGoodsId(id);
        List<ShopCarVo> shopCar=shopCarService.selectShopCarByUserId(2);
        model.addAttribute("count",shopCar);
        model.addAttribute("User",userService.selectUserByPhone(2));
        model.addAttribute("alertInfo","快去再添加点商品吧~");
        return "pages/front/Customers/ShopCar";
    }

    @RequestMapping("cleanShopCar")
    public String cleanShopCar( Model model){
        shopCarService.cleanShopCar();
        List<ShopCarVo> shopCar=shopCarService.selectShopCarByUserId(2);
        model.addAttribute("User",userService.selectUserByPhone(2));
        model.addAttribute("alertInfo","您的购物车已经情况了，快去添加商品吧");
        model.addAttribute("count",shopCar);
        return "pages/front/Customers/ShopCar";
    }
    @GetMapping("delCounts/{id}/{count}")
    public String delCounts( Model model,@PathVariable("id") Integer id ,@PathVariable("count")Integer count){
        System.err.println(count.intValue());
        count-- ;
        ShopCar a=new ShopCar();
        a.setCount(count);
        a.setShopCarId(id);
        if(count!=0 ){
            shopCarService.UpdataForCountBygetShopCarId(a);
        }else {
            model.addAttribute("alertInfo","该商品数量已经达到最小值的上限了，不能再减了");
        }
        System.err.println(id.intValue());
        List<ShopCarVo> shopCar=shopCarService.selectShopCarByUserId(2);
        model.addAttribute("User",userService.selectUserByPhone(2));
        model.addAttribute("count",shopCar);
        return "pages/front/Customers/ShopCar";
    }

    @ResponseBody
    @RequestMapping("register") //   /{phone}/{password} @PathVariable("phone") @PathVariable("password")
    public String register(  String  phone1 ,String password,String checkPassword){
        int  phone=Integer.parseInt(phone1.trim());  //错误1：使用时valueof，2.phone类型应该是int而不是integer 3.phone1空格错误
       String tip= userService.register(phone , password, checkPassword);
       System.err.println(tip);
     return tip;
    }

    @GetMapping("addCounts/{id}/{count}")
    public String addCounts( Model model,@PathVariable("id") Integer id ,@PathVariable("count")Integer count){
        System.err.println(count.intValue());
        count++ ;
        ShopCar a=new ShopCar();
        a.setCount(count);
        a.setShopCarId(id);
        shopCarService.UpdataForCountBygetShopCarId(a);
        List<ShopCarVo> shopCar=shopCarService.selectShopCarByUserId(2);
        model.addAttribute("User",userService.selectUserByPhone(2));
        model.addAttribute("alertInfo",null);
        model.addAttribute("count",shopCar);
        return "pages/front/Customers/ShopCar";
    }

    @GetMapping("goodsDetail/{id}")
    public String goodsDetail(Model model, @PathVariable("id") Integer id , HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        if (cookies!=null){
            for (Cookie cookie :cookies){
                System.err.println(cookie.getName());
                System.err.println(cookie.getValue());
                if (cookie.getName().equals("nikeName")){
                    model.addAttribute("nikeName",cookie.getValue());
                }
            }
        }
        if (cookies!=null){
            for (Cookie cookie :cookies){
                if (cookie.getName().equals("userId")){
                    model.addAttribute("userId",cookie.getValue());
                    int c=Integer.parseInt(cookie.getValue()) ;
                    List<ShopCarVo> shopCar=  shopCarService.selectShopCarByUserId(c);
                    int goodsCount=0;
                    for (ShopCarVo shopCarVo : shopCar){
                        goodsCount+=shopCarVo.getCount();
                    }
                    System.err.println(goodsCount);
                    model.addAttribute("goodsCount",goodsCount);
                }
            }
        }


        model.addAttribute("goods", goodsService.getGoodsDetailByGoodsId(id));
        return "pages/front/Customers/goodsDetail";
    }

    //ajax前后分离获取goods通过goodsId
    @ResponseBody
    @RequestMapping("getGoodsInfoByUserId/{goodsId}")
    public Goods getGoodsInfoByUserId(@PathVariable Integer goodsId ){
        System.err.println(goodsId);
        return goodsService.getGoodsDetailByGoodsId(goodsId);
    }
/*    //ajax前后分离获取User通过userId
    @ResponseBody
    @RequestMapping("getUserInfoByUserId/{userId}")
    public User getUserInfoByUserId(@PathVariable Integer userId ){
        System.err.println(userId);
        return .selectGoodsByUserId(userId);
    }*/

    //ajax前后分离获取以往订单通过userId
    @ResponseBody
    @RequestMapping("getUserOrderByUserId/{userId}")
    public List<UserOrder> getUserOrderByUserId(@PathVariable Integer userId ){
        System.err.println(userId);
        return userOrderService.selectUserOrderByUserId(userId);
    }

    @ResponseBody            //作用把对象转换为json格式
    @PostMapping("addGoodsToCar")
    public String addGoodsToCar( ShopCar shopCar,Model model){
     System.err.println(shopCar);

        if (shopCar.getUserId()!=0){
            shopCarService.addGoodsToCar(shopCar);
            model.addAttribute("userId",  shopCar.getUserId());
            model.addAttribute("nikeName",userService.selectUserById(shopCar.getUserId()).getNickName());
            model.addAttribute("goods", goodsService.getGoodsDetailByGoodsId(shopCar.getGoodsId()));

        return  "已经添加入购物车了哦~臭宝";
        }else {
            model.addAttribute("userId",  shopCar.getUserId());
            model.addAttribute("nikeName",userService.selectUserById(shopCar.getUserId()).getNickName());
            model.addAttribute("goods", goodsService.getGoodsDetailByGoodsId(shopCar.getGoodsId()));
            return  "请先登录哦~臭宝";
        }
  /*      model.addAttribute();*/
/*        UserAndGoods userAndGoods=new UserAndGoods();*/ //js微服务
    /*    if ( shopCarService.addGoodsToCar(shopCar)){
            System.err.println("access true");
            //判断用户不为空执行入库操作并且返回商品页面
            String name="尊贵的上帝:"+  userService.selectUserById(shopCar.getUserId()).getNickName();
            userAndGoods.setGoods(goodsService.getGoodsDetailByGoodsId(shopCar.getGoodsId()));
            userAndGoods.setShopCar(shopCar);
            userAndGoods.setUserName(name);
            userAndGoods.setUrl("http://localhost:8080/pages/front/Customers/goodsDetail/"+shopCar.getGoodsId());
            userAndGoods.setUserId(shopCar.getUserId());
            userAndGoods.setAddOrNot(true);
            return userAndGoods;
        }else {
            System.err.println("access false");
            //判断错误直接跳转登录页面（携带shopCar参数）
            userAndGoods.setShopCar(shopCar);
            userAndGoods.setUrl("/pages/front/UserAddGoodsLogin");
            userAndGoods.setAddOrNot(false);
            return userAndGoods;
        }*/

    }


    @RequestMapping("orderGenerate")
    public String orderGenerate(){


     return "pages/front/Customers/orderGenerate";
    }



}
