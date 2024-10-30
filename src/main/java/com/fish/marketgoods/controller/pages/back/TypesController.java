package com.fish.marketgoods.controller.pages.back;

import com.fish.marketgoods.pojo.entity.GoodsType;
import com.fish.marketgoods.pojo.vo.GoodsTypeVo;
import com.fish.marketgoods.service.TypeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


//作用是等于@ResponseBody 和@conteoller 一起使用
@Controller

@RequestMapping("pages/back/type")
public class TypesController {


    @Resource
    /* 通过alt+enter方法快速创建接口*/
            TypeService typesService;

    @Value("${custom.fileUploadPrefix}")
    private String fileUploadPrefix;

    //本次查询中出现过的问题
    //出现过与关键字冲突问题，原因应该是在于mapping.xml文件中的自己写的查询语句的格式不规范，没有分行，没有加入查询类型限定等一系列毛病（有待发现）
    @RequestMapping("toAddGoodsType")
    String toAddGoodsType(Model model) {
        List<GoodsTypeVo> Type=typesService.selectTypeByParentsId(0);
        System.err.println(Type);
        model.addAttribute("ParentsType",Type);
        return "pages/back/type/type-add";
    }



   //本次运行中出现的问题
   //1.首先是MultipartFile中的文件名字未与前端同一导致了空指针的问题
    //2.没有在Service实现类中加入@Resource注释IOC未识别到该类出现空指针问题
    //3.model的创建错误，修改为在进入url时候创建解决
    //4.出现过空指针问题，原因在于数据库中的数据名称错误（驼峰使用了中文表达式导致了dataSource自动创建驼峰没有删除）
    //5.数据库中忘记添加入中文utf-8的原因暂时没能输入中文（有待改进）
    @RequestMapping("/addGoodsType")
    public String addGoodsType(GoodsType goodstype , Model model) {
        if ( goodstype.getParentType()==0){
            System.err.println(  goodstype.getParentType());
            goodstype.setParentType(0);
        }else {
            System.err.println(  goodstype.getParentType());
            String a= String.valueOf(goodstype.getParentType()) +"1";
            goodstype.setParentType(Integer.parseInt(a));
            System.err.println(  goodstype.getParentType());
        }
      if (typesService.addGoodsType(goodstype)){
          System.out.println("insert success");
      }else {
          System.err.println(goodstype);
      }
        //model要在映射之前不然无法返回消息会爆红
        model.addAttribute("infoAdd","倍香!insert Success");
     return "pages/back/type/type-add";

    }

    @RequestMapping("/addParentsType")
    String addParentsType(Model model) {

        List<GoodsTypeVo> Type=typesService.selectTypeByParentsId(0);
        System.err.println(Type);
        model.addAttribute("ParentsType",Type);
        return "pages/back/type/type-add";
    }

    @RequestMapping("/toEditType")
    String toEditType(Model model){
    /*    List<GoodsType> Type=typesService.selectAllTypes();
        System.err.println(Type);
        model.addAttribute("Types",Type);*/
        return "pages/back/type/type-edit";
    }

    @ResponseBody
    @RequestMapping("getAllGoodsType")
     List<GoodsType> getAllGoodsType(){
      /*  List<GoodsType> Type=typesService.selectAllTypes();*/
        System.err.println("access");
       /* System.err.println(Type);*/
        return typesService.selectAllTypes();
    }

    @ResponseBody
    @RequestMapping("updateGoodsType")
    String updateGoodsType(GoodsType goodsType){
        System.err.println(goodsType);
        if (typesService.updateGoodsType(goodsType)){
            return "update success"  ;
        }else {
            return "update default"  ;
        }
    }


    @ResponseBody
    @RequestMapping("deleteGoodsType/{goodsTypeId}")
    String deleteGoodsType(@PathVariable Integer goodsTypeId){
        System.err.println(goodsTypeId);
        if (typesService.deleteGoodsType(goodsTypeId)){
            return "update success"  ;
        }else {
            return "update default"  ;
        }
    }

    @ResponseBody
    @RequestMapping("searchGoodsType{typeName}")
    List<GoodsType> searchGoodsType(@PathVariable String typeName){
        return   typesService.searchGoodsType(typeName);
    }

}