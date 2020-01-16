package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    /**
     * 功能描述: 查询所有
     *
     * @Param: [request, response]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/9 17:30
     */
    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1、调用service查询所有
        List<Category> categories = categoryService.findAll();
//        2、序列化json返回
        writeValue(categories,response);
    }

    protected void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
