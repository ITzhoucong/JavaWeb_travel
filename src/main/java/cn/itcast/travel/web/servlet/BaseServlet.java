package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       完成方法分发
//        1、获取请求路径
//        travel/user/add
        String uri = req.getRequestURI();
        System.out.println("请求uri:"+uri);
//        2、获取方法名称
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println("方法名称："+methodName);
//        3、获取方法对象Method
//        this谁调用我，我代表谁
        System.out.println(this);
        try {
//            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
//            方法为protected修饰，需要忽略访问修饰符获取getDeclaredMethod,最好将方法设为public
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
//        4、执行方法
//            暴力反射
            method.setAccessible(true);
            method.invoke(this,req,resp);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * 功能描述: 直接将传入的对象序列化为json，并且写回客户端
     * 
     * @Param: [object]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/9 17:34
     */
    public void writeValue(Object object,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),object);
    }

    /**
     * 功能描述: 将传入的对象序列化为json，返回
     *
     * @Param: [object]
     * @Return: java.lang.String
     * @Author: ZhouCong
     * @Date: 2020/1/9 17:38
     */
    public String  writeValueAsString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
