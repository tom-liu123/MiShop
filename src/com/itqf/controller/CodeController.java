package com.itqf.controller;

import cn.dsna.util.images.ValidateCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiuCongYang
 * @Version 1.0.0
 * create at @date  2021/1/4 19:21
 * copyright Beijing Murong Information Technology Co.,Ltd.
 */
@WebServlet("/code")
public class CodeController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ValidateCode code =new ValidateCode(100,30,4,5);
        String textCode = code.getCode();
        req.getSession().setAttribute("code",textCode);

        ImageIO.write(code.getBuffImg(),"jpg",resp.getOutputStream());

    }



}
