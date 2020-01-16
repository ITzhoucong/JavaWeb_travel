package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    /**
     * 功能描述: 根据cid查询总记录数
     *
     * @Param:
     * @Return:
     * @Author: ZhouCong
     * @Date: 2020/1/10 14:50
     */
    int findTotalCount(int cid,String rname);

    /**
     * 功能描述: 根据cid，start，pageSize查询当前页的数据集合
     *
     * @Param:
     * @Return:
     * @Author: ZhouCong
     * @Date: 2020/1/10 14:50
     */
    List<Route> findByPage(int cid,int start,int pageSize, String rname);

    /**
     * 功能描述: 根据id查询
     *
     * @Param: [rid]
     * @Return: cn.itcast.travel.domain.Route
     * @Author: ZhouCong
     * @Date: 2020/1/15 14:44
     */
    Route findById(int rid);
}
