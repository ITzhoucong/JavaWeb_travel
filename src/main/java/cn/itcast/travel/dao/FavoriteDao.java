package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {

    /**
     * 功能描述: 根据rid和uid查询收藏信息
     *
     * @Param:
     * @Return:
     * @Author: ZhouCong
     * @Date: 2020/1/16 14:03
     */
    Favorite findByRidAndUid (int rid, int uid);

    /**
     * 功能描述: 根据rid查询收藏次数
     *
     * @Param: [rid]
     * @Return: int
     * @Author: ZhouCong
     * @Date: 2020/1/16 14:36
     */
    int findCountByRid(int rid);

    /**
     * 功能描述: 添加收藏
     *
     * @Param: [rid, uid]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/16 15:17
     */
    void add(int rid, int uid);
}
