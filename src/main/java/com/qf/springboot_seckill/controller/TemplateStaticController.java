package com.qf.springboot_seckill.controller;

import com.qf.springboot_seckill.pojo.SeckillGoods;
import com.qf.springboot_seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
public class TemplateStaticController {

        @Autowired
        @Qualifier("myTemplateEngine")
        TemplateEngine templateEngine;

        @Autowired
        private SeckillGoodsService seckillGoodsService;

        @RequestMapping("create")
         public String createStaticHtml(Integer sid){

             SeckillGoods seckillGoods = seckillGoodsService.selectBySid(sid);

             //模板的上下文对象，用于存放模板中需要的数据
             Context context = new Context();

             context.setVariable("goods",seckillGoods);

             //生成静态文件的位置
             //获取classes 文件夹的绝对路径
             String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
             String newFile = path + "static/result.html";

             File file = new File(newFile);
             FileWriter writer =null;
             if (!file.exists()){
                 try {
                     file.createNewFile();
                     writer  = new FileWriter(newFile);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }

             templateEngine.process("seckill_detail",context,writer);

             return "success";
         }


}
