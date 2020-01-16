package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {

    /**
     * 功能描述: 分页查询
     *
     * @Param: [cid, currentPage, pageSize]
     * @Return: cn.itcast.travel.domain.PageBean<cn.itcast.travel.domain.Route>
     * @Author: ZhouCong
     * @Date: 2020/1/10 14:49
     */
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 功能描述: 根据id查询
     *
     * @Param: [rid]
     * @Return: cn.itcast.travel.domain.Route
     * @Author: ZhouCong
     * @Date: 2020/1/15 14:42
     */
    Route findOne(String rid);
}
