package com.nxyf.springboot_jsp_shiro.controller;

import com.nxyf.springboot_jsp_shiro.entity.User;
import com.nxyf.springboot_jsp_shiro.service.UserService;
import com.nxyf.springboot_jsp_shiro.utils.VerifyCodeUtils;
import com.sun.deploy.net.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //验证码放入session
        session.setAttribute("code",code);
        //验证码存入图片
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtils.outputImage(220,60,outputStream,code);
    }
    @RequestMapping("/login")
    public String login(String username, String password,String code,HttpSession session) {
        //比较验证码
        String sCode = (String) session.getAttribute("code");
        if (sCode.equalsIgnoreCase(code)) {
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(new UsernamePasswordToken(username, password));
                return "redirect:/index.jsp";
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                System.out.println("用户名错误");
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                System.out.println("密码错误");
            }
        }
        return "redirect:/login.jsp";
    }

    /**
     * 退出
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }

    /**
     * 注册
     */
    @RequestMapping("/register")
    public String register(User user) {
        try {
            userService.register(user);
            return "redirect:/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register.jsp";
        }

    }
}