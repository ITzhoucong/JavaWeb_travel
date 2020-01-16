package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {

    /**
     * 功能描述: 根据sid查询商品对应商家
     *
     * @Param: [sid]
     * @Return: cn.itcast.travel.domain.Seller
     * @Author: ZhouCong
     * @Date: 2020/1/15 15:05
     */
    Seller findBySid (int sid);
}
