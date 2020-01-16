package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {

    /**
     * 功能描述: 根据用户名查询用户信息
     *
     * @Param: [username]
     * @Return: cn.itcast.travel.domain.User
     * @Author: ZhouCong
     * @Date: 2020/1/7 10:22
     */
    User findByUsername(String username);
    
    /**
     * 功能描述: 用户保存
     * 
     * @Param: [user]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/7 10:23
     */
    void saveUser(User user);

    /**
     * 功能描述: 根据激活码查询用户对象
     *
     * @Param: []
     * @Return: cn.itcast.travel.domain.User
     * @Author: ZhouCong
     * @Date: 2020/1/7 17:03
     */
    User findByCode(String code);

    /**
     * 功能描述: 修改指定用户激活状态
     *
     * @Param: [user]
     * @Return: void
     * @Author: ZhouCong
     * @Date: 2020/1/7 17:03
     */
    void updateStatus(User user);

    /**
     * 功能描述: 根据用户名方法查询用户
     *
     * @Param: [username, password]
     * @Return: cn.itcast.travel.domain.User
     * @Author: ZhouCong
     * @Date: 2020/1/9 13:34
     */
    User findByUsernameAndPassword(String username, String password);
}
