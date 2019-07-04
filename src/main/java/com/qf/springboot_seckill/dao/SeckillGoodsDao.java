package com.qf.springboot_seckill.dao;

import com.qf.springboot_seckill.pojo.SeckillGoods;

import java.util.List;

/***
 *  佛曰:
 *          写字楼里写字间，写字间里程序员；
 *          程序人员写程序，又拿程序换酒钱。
 *          酒醒只在网上坐，酒醉还来网下眠；
 *          酒醉酒醒日复日，网上网下年复年。
 *          但愿老死电脑间，不愿鞠躬老板前；
 *          奔驰宝马贵者趣，公交自行程序员。
 *          别人笑我忒疯癫，我笑自己命太贱；
 *          不见满街漂亮妹，哪个归得程序员？
 */

public interface SeckillGoodsDao {

    //查询全部秒杀商品
    public List<SeckillGoods>  selectAll();

    //根据id查询商品
    public SeckillGoods  selectBySid(Integer sid);

    //获取秒杀库存
    public Integer selectSeckillStock(Integer sid);

    //减秒杀库存
    public void updateSeckillStock(Integer sid);
}
