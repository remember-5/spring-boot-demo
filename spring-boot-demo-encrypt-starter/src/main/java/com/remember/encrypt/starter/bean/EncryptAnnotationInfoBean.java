package com.remember.encrypt.starter.bean;


import com.remember.encrypt.starter.enums.EncryptBodyMethod;
import com.remember.encrypt.starter.enums.SHAEncryptType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>加密注解信息</p>
 * @author wangjiahao
 * @version 2018/9/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EncryptAnnotationInfoBean {

    private EncryptBodyMethod encryptBodyMethod;

    private String key;

    private SHAEncryptType shaEncryptType;

}
