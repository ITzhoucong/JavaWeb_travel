package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 功能描述: 查询所有
     *
     * @Param: []
     * @Return: java.util.List<cn.itcast.travel.domain.Category>
     * @Author: ZhouCong
     * @Date: 2020/1/9 17:29
     */
    List<Category> findAll();
}
