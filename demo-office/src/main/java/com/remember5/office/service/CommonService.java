package com.remember5.office.service;

import org.springframework.http.ResponseEntity;

/**
 * @author wangjiahao
 * @date 2021/12/3
 */
public interface CommonService {
    /**
     * PDF 文件导出
     * @return
     */
    ResponseEntity<?> export();
}
