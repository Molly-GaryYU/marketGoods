package com.fish.marketgoods.controller.pages.back;

import com.fish.marketgoods.pojo.entity.UserOrder;
import com.fish.marketgoods.service.UserOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


//作用是等于@ResponseBody 和@conteoller 一起使用
@Controller



@RequestMapping("pages/back/order")
public class OderController {

    @Resource
    /* 通过alt+enter方法快速创建接口*/
            UserOrderService orderService;

    @ResponseBody
    @RequestMapping("searchUserOrder/{searchInfo}")
    List<UserOrder> searchUserOrder(@PathVariable String searchInfo){
        System.err.println(searchInfo);
        System.err.println(orderService.searchUserOrder(searchInfo));
        return  orderService.searchUserOrder(searchInfo);
    }

    @RequestMapping("toAddOrder")
    String toAddOrder(){

        /*  model.addAttribute("goods",  goodsService.getAllGoods());*/
        return "pages/back/order/order-add";
    }

    @RequestMapping("toOrderInfo")
    String toOrderInfo(){

        /*  model.addAttribute("goods",  goodsService.getAllGoods());*/
        return "pages/back/order/order-edit";
    }

    @ResponseBody
    @RequestMapping("getAllOrders")
    List<UserOrder> getAllOrders(){

        /*  model.addAttribute("goods",  goodsService.getAllGoods());*/
        return orderService.getAllOrders();
    }

}
