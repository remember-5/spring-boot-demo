package com.remember5.leetcode.day02;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author wangjiahao
 * @date 2021/10/27
 */
public class Simple2 {


    public static void main(String[] args) {
        Date beforeWorkDate = getBeforeWorkDate(6);
        System.err.println(beforeWorkDate);


    }


    public static Date getBeforeWorkDate(int numDays) {
        // 获取当前是周几 1表示周日，2表示周一

        int count = 1;
        Date date = DateUtil.yesterday();

        while (count != numDays) {
            if (DateUtil.dayOfWeek(date) != 1 && DateUtil.dayOfWeek(date) != 7) {
                ++count;
            }
            date = DateUtil.offsetDay(date, -1);
        }

        return date;

    }

}
