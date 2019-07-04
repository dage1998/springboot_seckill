package com.qf.springboot_seckill.controller;

import com.qf.springboot_seckill.pojo.SeckillGoods;
import com.qf.springboot_seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Controller
public class SeckillGoodsController {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @RequestMapping("/list")
    public String selectAll(Model model){
        List<SeckillGoods> goods = seckillGoodsService.selectAllGoods();

        model.addAttribute("seckillGoods",goods);
        System.out.println("hhh");
        System.out.println("213");

        return "goods_list";
    }


    @RequestMapping("/detail")
    public String selectGoodsBySid(Integer sid, Model model){
        SeckillGoods seckillGoods = seckillGoodsService.selectBySid(sid);
        model.addAttribute("goods", seckillGoods);

        // 还可以得到剩余时间
        // 获取当前时间
        Date currentDate = new Date();
        //获取秒杀开始时间、结束时间
        Date beginTime = seckillGoods.getBeginTime();
        Date endTime = seckillGoods.getEndTime();

        long time1 = beginTime.getTime() - currentDate.getTime();

        //秒杀状态
        int seckillStatus = 0;
        //剩余时间(秒)
        long remainSeconds = 0;
        //说明 还没有开始秒杀
        if (time1 > 0){
            //未开始状态
            seckillStatus = 0;
            remainSeconds = time1 / 1000 ;

        }else {
            long time2 = endTime.getTime() - currentDate.getTime();
            //秒杀已开始
            if (time2 > 0){
                //查询秒杀商品库存
                if (seckillGoods.getSeckillStock() == 0){
                    //库存是0，秒杀结束
                    seckillStatus = 2;
                }else {
                    //秒杀中
                    seckillStatus = 1;
                }
            }else{
                //秒杀结束
                seckillStatus = 2;
            }


        }
        model.addAttribute("seckillStatus",seckillStatus);
        model.addAttribute("remainSeconds",remainSeconds);

        // 得到秒杀状态   未开始，已经开始，结束

        return "goods_detail";
    }

    @RequestMapping("/seckill")
    public String seckillGoods(Integer sid,Model model){


        try {
            seckillGoodsService.seckillGoods(sid);
        } catch (Exception e) {
            model.addAttribute("errmsg",e.getMessage());
            return "miaosha_fail";
        }

        return "redirect:/list";
    }

    @RequestMapping("seckillinfo")
    @ResponseBody
    public Map<String ,Object> seckillInfo(Integer sid){

        SeckillGoods seckillGoods = seckillGoodsService.selectBySid(sid);

        // 还可以得到剩余时间
        // 获取当前时间
        Date currentDate = new Date();
        //获取秒杀开始时间、结束时间
        Date beginTime = seckillGoods.getBeginTime();
        Date endTime = seckillGoods.getEndTime();

        long time1 = beginTime.getTime() - currentDate.getTime();

        //秒杀状态
        int seckillStatus = 0;
        //剩余时间(秒)
        long remainSeconds = 0;

        // 表示秒杀的时候对应的路径
        String seckillUrl = "#";

        //说明 还没有开始秒杀
        if (time1 > 0){
            //未开始状态
            seckillStatus = 0;
            remainSeconds = time1 / 1000 ;

        }else {
            long time2 = endTime.getTime() - currentDate.getTime();
            //秒杀已开始
            if (time2 > 0){
                //查询秒杀商品库存
                if (seckillGoods.getSeckillStock() == 0){
                    //库存是0，秒杀结束
                    seckillStatus = 2;

                    remainSeconds = -1;
                }else {
                    //秒杀中
                    seckillStatus = 1;

                    //秒杀状态时,给出路径
                    seckillUrl = "seckill";

                    remainSeconds = 0;
                }
            }else{
                //秒杀结束
                seckillStatus = 2;

                remainSeconds = time2 ;

            }


        }
        Map<String ,Object> map =new HashMap<>();

        map.put("seckillStatus",seckillStatus);
        map.put("remainSeconds",remainSeconds);

        // 得到秒杀状态   未开始，已经开始，结束

        map.put("seckillUrl",seckillUrl);

        return map;

    }
}
