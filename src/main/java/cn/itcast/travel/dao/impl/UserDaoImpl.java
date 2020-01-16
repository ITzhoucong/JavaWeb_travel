package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author: ZhouCong
 * @date: Create in 2020/1/7 10:02
 * @description:
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public User findByUsername(String username) {
        User user = null;
//        注意异常处理,没有查到用户名数据无法封装BeanPropertyRowMapper对象，直接报错，并不是查询结果为空，这里无需打印异常
        try {
//        1、定义sql语句
            String sql = "select * from tab_user where username = ?";
//        2、执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);

        } catch (Exception e) {
          //  e.printStackTrace();
        }

        return user;
    }

    @Override
    public void saveUser(User user) {
//        1、定义sql
        StringBuilder sql = new StringBuilder();
        sql.append("insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) ");
        sql.append(" values (?,?,?,?,?,?,?,?,?)");
//        2、执行sql
        template.update(sql.toString(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    @Override
    public User findByCode(String code) {

        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (Exception e) {
        //    e.printStackTrace();
        }

        return user;
    }

    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status = 'Y' where uid = ?";
        template.update(sql,user.getUid());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
//        注意异常处理,没有查到用户名数据无法封装BeanPropertyRowMapper对象，直接报错，并不是查询结果为空，这里无需打印异常
        try {
//        1、定义sql语句
            String sql = "select * from tab_user where username = ? and password = ?";
//        2、执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);

        } catch (Exception e) {
            //  e.printStackTrace();
        }
        return user;
    }
}
