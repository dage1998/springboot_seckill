package com.qf.springboot_seckill.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

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

//@Data 包含 @Setter 和@Getter
//可以不用写get和set方法，由lombok 自动生成
@Data                   //由lombok 自动生成get、set方法
@ToString               //由lombok 自动生成tostring方法
@NoArgsConstructor      //由lombok 自动生成无参构造方法
@AllArgsConstructor     //由lombok 自动生成有参构造方法
public class SeckillGoods {

    private  Integer sid;
    private  Goods goods;
    private  Double seckillPrice;
    private  Integer seckillStock;
    private  Date beginTime;
    private  Date endTime;



}
