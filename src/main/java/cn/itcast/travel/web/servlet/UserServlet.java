package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: ZhouCong
 * @date: Create in 2020/1/9 16:00
 * @description:
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    /**
     * @description: 声明userService业务对象
     */
    private UserService userService = new UserServiceImpl();
        /**
         * 功能描述: 注册用户
         *
         * @Param: [request, response]
         * @Return: void
         * @Author: ZhouCong
         * @Date: 2020/1/9 16:39
         */
    protected void register(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

//        创建返回对象
        ResultInfo info = new ResultInfo();

//        验证码校验
        String check = request.getParameter("check");
//        从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
//        保证验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
//        比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
//            验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
//            将info对象序列化位json对象
            ObjectMapper mapper = new ObjectMapper();
            String string = mapper.writeValueAsString(info);
//        将json数据写回客户端
//        设置content-type
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(string);
            return;
        }

//        1、获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();
//        2、封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        3、调用service完成注册
        boolean flag = userService.register(user);

//        4、响应结果
        if (flag) {
//            注册成功
            info.setFlag(true);
        } else {
//            注册失败
            info.setFlag(false);
            info.setErrorMsg("用户名已存在！");
        }
//        将info对象序列化位json对象
        ObjectMapper mapper = new ObjectMapper();
        String string = mapper.writeValueAsString(info);

//        将json数据写回客户端
//        设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(string);
    }

    /**
     * 功能描述: 登录用户
     *
     * @Param: [request, response]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/9 16:40
     */
    protected void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

//        创建返回值对象
        ResultInfo info = new ResultInfo();
//        验证码校验
        String check = request.getParameter("check");
//        获取验证码
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("CHECKCODE_SERVER");
        if (!checkCode.equalsIgnoreCase(check)) {
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
//        响应数据
            /*ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json;charset=utf-8");
            mapper.writeValue(response.getOutputStream(),info);*/
            writeValue(info,response);
            return;
        }


//        1、获取用户名和密码数据
        Map<String, String[]> parameterMap = request.getParameterMap();
//        2、封装user对象
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        3、调用service查询
        User u = userService.login(user);

//        4、判断用户对象是否为null
        if( u == null){
//            用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }
//        判断用户是否激活
        if ( u != null && !"Y".equals(u.getStatus())){
//            用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活！");
        }
//        6、判断登陆成功
        if ( u != null && "Y".equals(u.getStatus())){
//            登录成功
            info.setFlag(true);
//            将用户存入session
            request.getSession().setAttribute("user",u);
        }
//        响应数据
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);

    }

    /**
     * 功能描述: 查询单个对象
     *
     * @Param: [request, response]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/9 16:40
     */
    protected void findOne(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

//        从session中获取登录用户
        User user = (User) request.getSession().getAttribute("user");
//        将user写回客户端
       /* ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);*/
       writeValue(user,response);

    }

    /**
     * 功能描述: 退出用户
     *
     * @Param: [request, response]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/9 16:40
     */
    protected void exit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//        1、销毁session
        request.getSession().invalidate();
//        2、跳转页面
        response.sendRedirect(request.getContextPath() + "/login.html");

    }

    /**
     * 功能描述: 用户激活
     *
     * @Param: [request, response]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/9 16:41
     */
    protected void active(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

//        1、获取激活码
        String code = request.getParameter("code");
        if (code != null){
//            2、调用service完成激活
            boolean flag = userService.activeUser(code);
//          3、判断标记
            String msg = null;
            if(flag){
//                激活成功
                msg = "激活成功，请<a href='showLoginPage'>登录</a>";
            }else {
//                激活失败
                msg = "激活失败，请联系管理员！";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }

    protected void showLoginPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/login.html");
    }


    }


