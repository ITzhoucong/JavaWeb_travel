package cn.itcast.travel.service;

import cn.itcast.travel.domain.User; /**
 * @author: ZhouCong
 * @date: Create in 2020/1/7 10:00
 * @description:
 */
public interface UserService {
    /**
     * 功能描述: 注册用户
     *
     * @Param: [user]
     * @Return: boolean
     * @Author: ZhouCong
     * @Date: 2020/1/7 10:09
     */
    boolean register(User user);

    /**
     * 功能描述: 激活用户
     *
     * @Param: [code]
     * @Return: boolean
     * @Author: ZhouCong
     * @Date: 2020/1/7 16:59
     */
    boolean activeUser(String code);

    /**
     * 功能描述: 用户登录
     *
     * @Param: [user]
     * @Return: cn.itcast.travel.domain.User
     * @Author: ZhouCong
     * @Date: 2020/1/9 10:31
     */
    User login(User user);
}
