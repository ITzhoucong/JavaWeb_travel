package cn.itcast.travel.service;

public interface FavoriteService {

    /**
     * 功能描述: 判断是否收藏
     *
     * @Param: [rid, uid]
     * @Return: boolean
     * @Author: ZhouCong
     * @Date: 2020/1/16 15:16
     */
    boolean isFavorite (int rid ,int uid);

    /**
     * 功能描述: 添加收藏
     *
     * @Param: [rid, uid]
     * @Return: boolean
     * @Author: ZhouCong
     * @Date: 2020/1/16 15:16
     */
    void add(String rid, int uid);
}
