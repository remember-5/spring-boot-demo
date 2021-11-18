package com.remember.demo.web.entity;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * @author wangjiahao
 * @date 2021/10/29
 */
@Data
public class TikTokDownloader {

    public static final String RE_STR = "http[s]?://(?:[a-zA-Z]|[0-9]|[$-_@.&+]|[!*\\(\\),]|(?:%[0-9a-fA-F][0-9a-fA-F]))+";
    public static final String IES_URL = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=%s&dytk=";

    /**
     * 分享文本
     * "7.66 qeB:/ 我好想她  https://v.douyin.com/RNGG5CN/ 复制Ci链接，打开Dou姻搜索，直接观看视頻！";
     */
    private String shareContent;
    /**
     * 分享文本中 提取出来到分享链接
     */
    private String shareUrl;
    /**
     * 播放id
     */
    private String playId;
    /**
     * ies返回结果
     */
    private JSONObject iesResponse;
    /**
     * video的name
     */
    private String videoName;
    /**
     * 网页播放url
     */
    private String playUrl;
    /**
     * 无水印下载地址
     */
    private String downloadUrl;

    public TikTokDownloader(String shareContent) {
        this.shareContent = shareContent;
        this.getDownloadUrl();
    }

    public String getDownloadUrl(String shareContent) {
        this.shareContent = shareContent;
        return this.getDownloadUrl();
    }

    public String getDownloadUrl() {
        this.findPlayId();
        return downloadUrl;
    }

    /**
     * 获取playId
     */
    private void findPlayId() {
        // 获取匹配到的文本
        List<String> all = ReUtil.findAllGroup0(RE_STR, shareContent);
        // 获取分享文本中的链接
        this.shareUrl = all.get(0);
        // 获取重定向前的代码
        String location = HttpRequest.get(this.shareUrl)
                .setFollowRedirects(false)
                .execute()
                .headers()
                .get("Location")
                .toArray()[0]
                .toString();

        // 获取到一个http链接 然后解析
        String[] split = location.split("\\?")[0].split("\\/");
        // 获取到playId
        this.playId = split[split.length - 1];
        this.findIesUrl();
    }


    /**
     * 查找ies地址
     */
    private void findIesUrl() {
        //获取ies地址
        String url = String.format(IES_URL, playId);
        HttpResponse iesResponse = HttpRequest.get(url).execute();
        this.iesResponse = JSONObject.parseObject(iesResponse.body());
        this.videoName();
    }

    /**
     * 获取文件名
     */
    private void videoName() {
        JSONObject itemList = iesResponse.getJSONArray("item_list")
                .getJSONObject(0);

        String videoName = itemList.getJSONObject("author").getString("nickname") + "-" + itemList.getString("desc");
        this.videoName = FileNameUtil.cleanInvalid(videoName) + ".mp4";
        this.play();
    }

    /**
     * 获取播放地址
     */
    private void play() {
        this.playUrl = this.iesResponse.getJSONArray("item_list")
                .getJSONObject(0).getJSONObject("video")
                .getJSONObject("play_addr")
                .getJSONArray("url_list")
                .get(0).toString()
                .replace("playwm", "play");
        this.getNotWaterMarkUrl();
    }

    /**
     * 获取无水印下载地址
     */
    private void getNotWaterMarkUrl() {
        this.downloadUrl = HttpRequest.get(playUrl)
                .setFollowRedirects(false)
                .execute()
                .headers()
                .get("Location").toArray()[0].toString();
    }

    public static void main(String[] args) {
        String shareContent = "2.56 Uyg:/ 我在乖乖吃饭 爸爸你快拍我呀～  https://v.douyin.com/RNKwqqm/ 复制佌lian接，答kaiDou音搜索，直接观kan视频！";
        TikTokDownloader tikTokDownloader = new TikTokDownloader(shareContent);
        String url = tikTokDownloader.getDownloadUrl();
        System.err.println(url);
    }
}
