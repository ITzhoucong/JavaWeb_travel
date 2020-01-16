package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述: 分页查询
 *
 * @Param:
 * @Return:
 * @Author: ZhouCong
 * @Date: 2020/1/10 13:59
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

private RouteService routeServce = new RouteServiceImpl();
private FavoriteService favoriteService = new FavoriteServiceImpl();

    protected void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1、接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
//        解决get乱码
        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");

        int cid = 0;
        int currentPage = 0;
        int pageSize = 0;
//        2、处理参数
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }
        if ("null".equals(rname)){
            rname = null;
        }

        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
//            如果不传递，则默认为第一页
            currentPage = 1;
        }

        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
//          如果不传递，则默认为每页显示5条
            pageSize = 5;
        }
//        3、调用service查询PageBean对象
        PageBean<Route> pb = routeServce.pageQuery(cid,currentPage,pageSize,rname);
//        4、将PageBean对象转化为json，返回
            writeValue(pb,response);
    }

    /**
     * 功能描述: 根据id查询一个旅游线路的详细信息
     *
     * @Param: [request, response]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/15 14:39
     */
    protected void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1、接收id
        String rid = request.getParameter("rid");
//        2、调用service查询route对象
        Route route = routeServce.findOne(rid);
//        3、转为json写回客户端
        writeValue(route,response);

    }

    /**
     * 功能描述: 判断当前登录用户是否收藏该线路
     *
     * @Param: [request, response]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/16 13:57
     */
   protected void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        1、获取线路id
        String rid = request.getParameter("rid");
//        2、获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null){
//            用户尚未登录
            uid = 0;
        }else {
//            用户已经登录
            uid = user.getUid();
        }
//        3、调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(Integer.parseInt(rid), uid);
//        4、写回客户端
        writeValue(flag,response);

    }

    /**
     * 功能描述: 添加收藏
     *
     * @Param: [request, response]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/16 15:12
     */
    protected void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1、获取线路rid
        String rid = request.getParameter("rid");
//        2、获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
//        用户id
        int uid;
        if (user == null){
//            用户尚未登录
            return;
        }else {
//            用户已经登录
            uid = user.getUid();
        }
//        3、调用service添加
        favoriteService.add(rid,uid);

    }

}
