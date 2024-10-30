package com.fish.marketgoods.controller;


import com.fish.marketgoods.config.CustomConfig;
import com.fish.marketgoods.dao.UserDao;
import com.fish.marketgoods.pojo.dto.UserInfo;
import com.fish.marketgoods.pojo.entity.Goods;
import com.fish.marketgoods.pojo.vo.RegistVo;
import com.fish.marketgoods.pojo.vo.ShopCarVo;
import com.fish.marketgoods.service.GoodsService;
import com.fish.marketgoods.service.ShopCarService;
import com.fish.marketgoods.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *  前后端分离：后端只提供数据，不管页面跳转，所以相应的是数据，用@Controller 要在方法上加@ResponseBody
 *  而 @RestController 等价于 @Controller  +  @ResponseBody
 */
//作用是等于@ResponseBody 和@conteoller 一起使用
/*@RestController*/
@Controller
public class indexController {

    @Resource
    UserDao userDao;

    @Resource
    TypeService typeService;

    //这个语句的意义
    /**
     * 这个语义是 获取配置文件（custom.fileUploadPrefix）中的值
     */
//    @Value("${custom.fileUploadPrefix}")
//    private String fileUploadPrefix;

    @Autowired
    private CustomConfig customConfig;

    @Resource
    ShopCarService shopCarService;

    @Resource
    /* 通过alt+enter方法快速创建接口*/
            GoodsService goodsService;

    @RequestMapping("/")
    /*@ResponseBody*/
       //表示返回json字符串给前端，否者return会返回名叫"hellow，bro"的前端页面
       //因为如果方法类型为String会返回return中的字符的值的页面，而不是console输出
  public  String index(Model model, HttpServletRequest request, HttpServletResponse response){
      Cookie[] cookies=request.getCookies();
        //你这里要确定是否登录才能看。。 如果不登陆也能看得话，要判断cookie的， 因为第一次访问没有cookie信息的，所以这里处理空指针的
        //判断我再前端的js实现了所以这里没写 cookie 是登录后端生成的，你直接访问ip和端口 怎么能走到你的js中 就是每次访问都会查看一次cookie
//        所以这里你要处理null 的问题  这个循环是个语法糖 如果cookies 为null会报空指针的
//        int var6 = cookies.length; 这里就是空指针的原因 明白了
        if (cookies != null) {
            for (Cookie cookie :cookies){
                if (cookie.getName().equals("nikeName")){
                    model.addAttribute("nikeName",cookie.getValue());
                }else {
                    System.err.println("none userInfo");
                    model.addAttribute("nikeName","您好,请登录");
                }
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

    /*    for (Cookie cookie :cookies){

        }*/
        model.addAttribute("types",typeService.selectTypeByParentsId(0));
       /* List<Goods> goods= goodsService.getGoodsByTypeId(11);*/
        List<Goods> goods= goodsService.getAllGoods();
        model.addAttribute("goods",goods);
        return "index";
    }

    /**
     *  当点击上传的时候 调这个接口，将文件的访问地址返回。将这个地址放在表单中隐藏。 然后提交表单，将信息存入数据库
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/files")
    public String upload(MultipartFile file) throws IOException {
        //这个是写入的目录  可以使用相对路径来实现，一时想不起来了 所以写了绝对路径
        String basePath = "D:\\market-goods\\src\\main\\view\\static";
        /*getOriginalFilename()作用:得到上传时候的文件名*/
        //file.getOriginalFilename() 获取文件的名称
        String path = "\\img\\index\\"+ file.getOriginalFilename();
        File file1 = new File(basePath + path);
        //将文件传给服务器且只执行一次并且不能再使用get方法
        //这个使用文件拷贝技术，效率比流写入效率高， 可以去看一下 零拷贝技术
        file.transferTo(file1);
      return customConfig.getFileUploadPrefix() + path;
    //你要返回给前端一个可以访问的链接，fileUploadPrefix 这个配置也可以放在前端，不然分开操作，没法做预览 校验链接是否有效

    }
    /**
     * 这个是将所有的数据一次行处理，
     * @param vo
     * @return
     * @throws IOException
     */
    @PostMapping("/regist")
    public UserInfo regist(RegistVo vo) throws IOException {
        UserInfo userInfo = new UserInfo();
        String imageUrl = upload(vo.getFile());

        BeanUtils.copyProperties(vo,userInfo);
        userInfo.setImageUrl(imageUrl);

        //入库操作

        return userInfo;
    }


}
