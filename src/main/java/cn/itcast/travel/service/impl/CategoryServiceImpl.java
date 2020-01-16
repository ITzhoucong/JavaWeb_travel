package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: ZhouCong
 * @date: Create in 2020/1/9 17:23
 * @description:
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        List<Category> cs = null;

//      1、从redis中查询
//        1.1、获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
//        1.2、可使用sortedset排序查询
       // Set<String> categorys = jedis.zrange("category", 0, -1);
//        1.2、查询sortedset中的分数（cid）和值（cname）
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

//      2、判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0){
//      3、如果为空，需要从数据库中查询，再将数据存入redis
//            3.1、从数据库中查询
            cs = categoryDao.findAll();
//            3.2、将集合数据存储到redis中的category的key
            for (int i = 0;i<cs.size();i++){
            jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());

            }
        }else {
//      4、如果不为空，将set的数据存入list
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                cs.add(category);
            }

        }


        return cs;
    }
}
