package com.remember5.interview.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangjihao
 * @date
 */
public class AtDemo {

    public static void main(String[] args) {

        String content = "你好啊@KevinBlandy 这里是SpringBoot中文社区，@搞个大新闻 @Hello_Java开发者\r\n。";

        /**
         @(?<name>[a-zA-Z\\d_\\u4e00-\\u9fa5]{1,14})\\s+
         @ 表示匹配以该符号开头的字符串
         (?<name>[a-zA-Z\\d_\\u4e00-\\u9fa5]{1,14}) 表示用户昵称的规则为:大小写字母，汉字，数字以及下划线，1 - 14个字符。并且给该规则起了个名字叫做`name`
         \\s+ 表示在符合昵称规则后，跟随了一个或者多个空格/换行
         **/
        Pattern pattern = Pattern.compile("@(?<name>[a-zA-Z\\d_\\u4e00-\\u9fa5]{1,14})\\s+");

        Matcher matcher = pattern.matcher(content);

        StringBuilder stringBuilder = new StringBuilder();

        int lastIndex = 0;

        while (matcher.find()) {
            //匹配到的昵称（正则中定义的名称）
            String name = matcher.group("name");

            //昵称开始的角标
            int start = matcher.start("name");

            //截取昵称前面的字符串,从上一个昵称结束的角标开始截取
            stringBuilder.append(content.substring(lastIndex, start));

            //手动对昵称添加a标签，可以从DB根据name检索数据，然后添加href属性。
            stringBuilder.append("<a>" + name + "</a>");

            //记录昵称结束的角标
            lastIndex = matcher.end("name");
        }

        //最后对所有@信息都添加了<a>
        stringBuilder.append(content.substring(lastIndex)).toString();

        System.out.println(stringBuilder.toString());
        //你好啊<a>KevinBlandy</a>这里是SpringBoot中文社区，<a>搞个大新闻</a><a>Hello_Java开发者</a>。

    }
}
