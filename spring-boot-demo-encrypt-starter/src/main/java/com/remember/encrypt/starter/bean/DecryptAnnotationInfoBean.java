package com.remember.encrypt.starter.bean;

import com.remember.encrypt.starter.enums.DecryptBodyMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>解密注解信息</p>
 * @author wangjiahao
 * @version 2018/9/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecryptAnnotationInfoBean {

    private DecryptBodyMethod decryptBodyMethod;

    private String key;

}
