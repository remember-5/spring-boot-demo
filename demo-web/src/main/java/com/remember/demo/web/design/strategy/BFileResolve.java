package com.remember.demo.web.design.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/19
 */
@Slf4j
@Component
public class BFileResolve implements IFileStrategy{
    @Override
    public FileTypeResolveEnum getFileType() {
        return FileTypeResolveEnum.File_B_RESOLVE;
    }

    @Override
    public void resolve(Object object) {
        log.info("B  类型解析文件，参数 {}", object);
        // 具体解析逻辑
    }
}
