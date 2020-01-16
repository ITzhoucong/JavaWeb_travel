package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @author: ZhouCong
 * @date: Create in 2020/1/7 10:01
 * @description:
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(User user) {
//        1、根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
//        判断user是否位null
        if( u != null){
//            用户名存在,注册失败
            return false;
        }
//        2、保存用户信息
//        2.1、设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
//        2.2、设置激活状态
        user.setStatus("N");
        userDao.saveUser(user);

//        3、激活邮件发送，邮件正文？
            String content = "<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";
            MailUtils.sendMail(user.getEmail(),content,"激活邮件");

            return true;
    }

    @Override
    public boolean activeUser(String code) {
//        1、根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if (user != null){
//            2、调用dao的修改激活状态方法
            userDao.updateStatus(user);
            return true;
        }
        return false;
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());

    }
}
