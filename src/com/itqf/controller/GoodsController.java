package com.itqf.controller;

import com.itqf.anno.ContentType;
import com.itqf.entity.Product;
import com.itqf.entity.Types;
import com.itqf.service.ProductService;
import com.itqf.service.TypesService;
import com.itqf.service.impl.ProductServiceImpl;
import com.itqf.service.impl.TypesServiceImpl;
import com.itqf.utils.PageBean;
import com.itqf.utils.SysConstant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 下午3:34
 */
@WebServlet("/goodsController")//goodsController
public class GoodsController extends  BaseController {

    TypesService typesService = new TypesServiceImpl();
    ProductService productService = new ProductServiceImpl();

    @ContentType("application/json;charset=utf-8")
    public List<Types> getAllType(HttpServletRequest req, HttpServletResponse response) throws  Exception{

      return   typesService.findAll();
    }


    public String getGoodsByTypeId(HttpServletRequest req, HttpServletResponse response) throws  Exception{
            String typeId = req.getParameter("typeId");
            int tid = Integer.parseInt(typeId);
            String nowPageStr = req.getParameter("nowPage");
            int nowPage ;
            int pageSize = 5;
            if (nowPageStr==null){
                nowPage = 1;
            }else{
                nowPage = Integer.parseInt(nowPageStr);
            }

            int startIndex = (nowPage-1)*5;
            PageBean bean =  productService.findProductByTypeId(tid,startIndex,pageSize);
            bean.setNowPage(nowPage);
            req.setAttribute("pageBean",bean);
            req.setAttribute("typeId",typeId);
            bean.cal();
            System.out.println(bean.getTotalPage()+"end"+bean.getEnd()+"--start"+bean.getStart());
            return SysConstant.FORWARD+SysConstant.FLAG+"goodsList.jsp";
    }

    //getGoodsById
    public  String getGoodsById(HttpServletRequest req, HttpServletResponse resp) throws  Exception{
        int id =Integer.parseInt(req.getParameter("id")) ;
        Product p = productService.findProductDetailById(id);
        req.setAttribute("goods",p);
        return SysConstant.FORWARD+SysConstant.FLAG+"goodsDetail.jsp";
    }


}
