package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    /**
     * 功能描述: 根据rid查询商品对应图片
     *
     * @Param: [rid]
     * @Return: java.util.List<cn.itcast.travel.domain.RouteImg>
     * @Author: ZhouCong
     * @Date: 2020/1/15 14:56
     */
    List<RouteImg> findByRid(int rid);
}
