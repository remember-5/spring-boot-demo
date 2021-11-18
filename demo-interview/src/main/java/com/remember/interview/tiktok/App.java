package com.remember.interview.tiktok;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author wangjiahao
 * @date 2021/10/28
 */
public class App {

    public static void main(String[] args) {
        // 设置正则表达式
        String reg = "http[s]?://(?:[a-zA-Z]|[0-9]|[$-_@.&+]|[!*\\(\\),]|(?:%[0-9a-fA-F][0-9a-fA-F]))+";
        // 获取分享文本
        String shareUrl = "7.66 qeB:/ 我好想她  https://v.douyin.com/RNGG5CN/ 复制Ci链接，打开Dou姻搜索，直接观看视頻！";
        // 获取匹配到的文本
        List<String> all = ReUtil.findAllGroup0(reg, shareUrl);
        // 获取分享文本中的链接
        String baseUrl = all.get(0);
        // 获取重定向前的代码
        String location = HttpRequest.get(baseUrl)
                .setFollowRedirects(false)
                .execute()
                .headers()
                .get("Location")
                .toArray()[0]
                .toString();

        // 获取到一个http链接 然后解析
        String[] split = location.split("\\?")[0].split("\\/");
        // 获取到playId
        String playId = split[split.length - 1];
        //获取ies地址
        String url = String.format("https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=%s&dytk=", playId);


        HttpResponse iesResponse = HttpRequest.get(url).execute();
        JSONObject json = JSONObject.parseObject(iesResponse.body());
        String realUrl = json.getJSONArray("item_list")
                .getJSONObject(0)
                .getJSONObject("video")
                .getJSONObject("play_addr")
                .getJSONArray("url_list")
                .get(0).toString()
                .replace("playwm", "play");

        String finalUrl = HttpRequest.get(realUrl).setFollowRedirects(false).execute().headers().get("Location").stream().toArray()[0].toString();

        System.err.println("下载地址为：" + finalUrl);


    }
}
