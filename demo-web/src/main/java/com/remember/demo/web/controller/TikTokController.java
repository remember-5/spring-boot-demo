package com.remember.demo.web.controller;

import cn.hutool.core.util.StrUtil;
import com.remember.common.entity.R;
import com.remember.demo.web.entity.TikTokDownloader;
import com.remember.demo.web.entity.TikTokVO;
import com.remember.demo.web.utils.Aria2Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjiahao
 * @date 2021/10/28
 */
@Slf4j
@RestController
@RequestMapping("tiktok")
public class TikTokController {


    @PostMapping
    public R get(@RequestBody TikTokVO tikTok) {// 设置正则表达式
        TikTokDownloader downloader = new TikTokDownloader(tikTok.getShareContent());

        if (tikTok.getSaveEnable()) {
            if (StrUtil.isEmpty(tikTok.getFileName())) {
                String videoName = downloader.getVideoName();
                tikTok.setFileName(videoName);
            }
            if (!Aria2Utils.downloadFiles(downloader.getDownloadUrl(), tikTok.getFileName())) {
                return R.fail("500",null,"下载失败！");
            }
        }


        return R.success(downloader.getDownloadUrl());
    }

}
