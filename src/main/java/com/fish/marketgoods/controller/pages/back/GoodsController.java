package com.fish.marketgoods.controller.pages.back;

import com.fish.marketgoods.dao.GoodsDao;
import com.fish.marketgoods.pojo.entity.Goods;
import com.fish.marketgoods.service.GoodsService;
import com.fish.marketgoods.service.TypeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;


//作用是等于@ResponseBody 和@conteoller 一起使用
@Controller



@RequestMapping("pages/back/goods")
public class GoodsController {
    GoodsDao goodsDao ;

    @Resource
           /* 通过alt+enter方法快速创建接口*/
    GoodsService goodsService;

    @Resource
    /* 通过alt+enter方法快速创建接口*/
            TypeService typesService;


    @Value("${custom.fileUploadPrefix}")
    private String fileUploadPrefix;



    @RequestMapping("goodsInfo")
    String goodsInfo(Model model){
      /*  model.addAttribute("goods",  goodsService.getAllGoods());*/
        return "pages/back/goods/goods-Info";
    }

    //通过模糊查询获取相关商品
    @ResponseBody
    @RequestMapping("searchGoods/{searchInfo}")
    List<Goods> searchGoods(@PathVariable String searchInfo ){
        System.err.println("access");
        System.err.println(goodsService.searchGoodsByInfo(searchInfo));
        return  goodsService.searchGoodsByInfo(searchInfo);
    }

    @ResponseBody
    @RequestMapping("getGoods")
    List<Goods> getGoods(){
        return  goodsService.getAllGoods();
    }

    @RequestMapping("addPre")
    String addPre(Model model){
        model.addAttribute("types",typesService.selectTypeByParentsId(0));
        return "pages/back/goods/goods-addPre";
    }

    @RequestMapping("goodsDetail/{goodsId}")
    String goodsDetail(@PathVariable Integer goodsId, Model model, HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie :cookies){
            System.err.println(cookie.getValue());
            if (cookie.getName().equals("nikeName")){
                model.addAttribute("nikeName",cookie.getValue());
            }else {
                System.err.println("none userInfo");
                model.addAttribute("nikeName","您好,请登录");
            }
        }
        model.addAttribute("goods",goodsService.getGoodsDetailByGoodsId(goodsId));
        return "pages/back/goods/goods-detail";
    }


    @RequestMapping("getGoodsByTypeId/{typeId}")
    //因为使用了路径传参的方式所以必须要加入@PathVariable注解
    //并且前端中的路径要在getGoodsByTypeId再加入一个/否者无法识别跳转
   public String getGoodsByTypeId(@PathVariable Integer typeId, Model model){
    System.err.println(typeId);
       List<Goods> goods= goodsService.getGoodsByTypeId(typeId);
        System.err.println(goods);
        model.addAttribute("goods2",goods);

    /*    if (goods==null){
            System.err.println("查询无此类商品");
           model.addAttribute("msg","查询无此类商品");
        }else {
            model.addAttribute("good",goods);
        }*/
        return "index";
    }

    //管理员更新商品信息
    @ResponseBody
    @RequestMapping("updateGoodsInfo")
    String updateGoodsInfo(Goods goods){
        System.err.println(goods);
        if ( goodsService.updateGoodsInfo(goods)){
            return "更新成功";
        }else {
            return "更新失败" ;
        }

    }

    @RequestMapping("add")
    @ResponseBody
    /*让返回的值字符输出而不知转入html*/
    //MultipartFile pic MultipartFile的作用是获取前端传回来的图片，pic的名字必须与前端图片的名字相同
   /* Map<String,Object>*/
     public String add(Goods goods, MultipartFile pic){

         //getOriginalFilename()获取上传的图片的名字，且记只有文件名字没有文件的路径
        String path="\\img\\index\\"+pic.getOriginalFilename() ;
        goods.setImg(path);
        System.err.println(goods);
        System.err.println(path);
        File img=new File(fileUploadPrefix+path);
        if ("on".equals(goods.getOnSale())){
            goods.setOnSale("onSale");

        }else{
            goods.setOnSale("None");
        }
        System.err.println(goods);

        if(goodsService.add(goods)){
            return "入库成功！！！";
        }else{
            return "false";
        }
    }
}
//1.可能是包引入错误
//2.可能是mapper中的sql语句写错了（database自动生成）
//3.问题解决主要是因为onsale中的值为中文了，以后有待改进
//4.出现过空指针的问题主要是因为忘记创建goodService的接口，直接使用了dao代理入库