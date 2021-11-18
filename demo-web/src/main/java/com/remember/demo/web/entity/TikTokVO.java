package com.remember.demo.web.entity;

import lombok.Data;

/**
 * @author wangjiahao
 * @date 2021/10/28
 */
@Data
public class TikTokVO {
    /**
     * 分享内容
     */
    private String shareContent;


    /**
     * 是否保存到服务器
     */
    private Boolean saveEnable = false;

    /**
     * 文件名称
     */
    private String fileName;

}
