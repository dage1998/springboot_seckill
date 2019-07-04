package com.qf.springboot_seckill.service.impl;

import com.qf.springboot_seckill.dao.SeckillGoodsDao;
import com.qf.springboot_seckill.pojo.SeckillGoods;
import com.qf.springboot_seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {


    @Autowired(required = false)
    private SeckillGoodsDao seckillGoodsDao;
    @Override
    public List<SeckillGoods> selectAllGoods() {
        return seckillGoodsDao.selectAll();
    }

    @Override
    public SeckillGoods selectBySid(Integer sid) {
        return seckillGoodsDao.selectBySid(sid);
    }

    @Override
    public void seckillGoods(Integer sid) {
        Integer stock = seckillGoodsDao.selectSeckillStock(sid);

        if (stock <= 0){
            throw new RuntimeException("已经秒杀完毕");
        }
        //生成订单 暂时省略

        //减库存
        seckillGoodsDao.updateSeckillStock(sid);


    }
}
