package com.me.common.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Arvin1 on 2021/9/27.
 */
@Component
public class RandomColor {

    public static List<String> colorList = new ArrayList<>();

    @PostConstruct
    public void initColor(){
        for(int i = 0; i<500; i++){
            colorList.add(ramdomColor());
        }
    }
    public static String getColor(Integer i){
        return colorList.get(i);
    }

    public static String ramdomColor(){
        //红色
        String red;
        //绿色
        String green;
        //蓝色
        String blue;
        //生成随机对象
        Random random = new Random();
        //生成红色颜色代码
        red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成绿色颜色代码
        green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成蓝色颜色代码
        blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

        //判断红色代码的位数
        red = red.length()==1 ? "0" + red : red ;
        //判断绿色代码的位数
        green = green.length()==1 ? "0" + green : green ;
        //判断蓝色代码的位数
        blue = blue.length()==1 ? "0" + blue : blue ;
        //生成十六进制颜色值
        return "#"+red+green+blue;
    }
}
