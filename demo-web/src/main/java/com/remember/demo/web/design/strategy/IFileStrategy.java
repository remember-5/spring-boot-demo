package com.remember.demo.web.design.strategy;

/**
 * https://mp.weixin.qq.com/s/VY929e2_vJVMQ00e848p0w
 * @author wangjiahao
 * @date 2021/11/19
 */
public interface IFileStrategy {

    /**
     * 判断文件类型，属于哪种文件解析类型
     *
     * @return /
     */
    FileTypeResolveEnum getFileType();

    /**
     * 封装的公共算法 (具体的解析方法)
     *
     * @param object /
     */
    void resolve(Object object);


}
