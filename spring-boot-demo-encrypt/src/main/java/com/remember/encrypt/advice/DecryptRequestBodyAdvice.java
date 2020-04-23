package com.remember.encrypt.advice;


import com.remember.encrypt.annotation.decrypt.AESDecryptBody;
import com.remember.encrypt.annotation.decrypt.DESDecryptBody;
import com.remember.encrypt.annotation.decrypt.DecryptBody;
import com.remember.encrypt.annotation.decrypt.RSADecryptBody;
import com.remember.encrypt.bean.DecryptAnnotationInfoBean;
import com.remember.encrypt.bean.DecryptHttpInputMessage;
import com.remember.encrypt.config.EncryptBodyConfig;
import com.remember.encrypt.enums.DecryptBodyMethod;
import com.remember.encrypt.exception.DecryptBodyFailException;
import com.remember.encrypt.exception.DecryptMethodNotFoundException;
import com.remember.encrypt.util.AESEncryptUtil;
import com.remember.encrypt.util.CheckUtils;
import com.remember.encrypt.util.DESEncryptUtil;
import com.remember.encrypt.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * 请求数据的加密信息解密处理<br>
 * 本类只对控制器参数中含有
 * <strong>{@link org.springframework.web.bind.annotation.RequestBody}</strong>
 * 以及package为
 * <strong>
 * <code>com.remember.encrypt.annotation.decrypt</code>
 * </strong>下的注解有效
 *
 * @author wangjiahao
 * @version 2020/4/23
 * @see RequestBodyAdvice
 */
@Order(1)
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    private final EncryptBodyConfig config;

    /**
     * 此处如果返回false , 则不执行当前Advice的业务
     *
     * @param methodParameter 用来获取注解
     * @param targetType      省略
     * @param converterType   省略
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter methodParameter, @NonNull Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        Annotation[] annotations = methodParameter.getDeclaringClass().getAnnotations();
        if (annotations != null && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof DecryptBody ||
                        annotation instanceof AESDecryptBody ||
                        annotation instanceof DESDecryptBody ||
                        annotation instanceof RSADecryptBody) {
                    return true;
                }
            }
        }
        return methodParameter.getMethod().isAnnotationPresent(DecryptBody.class) ||
                methodParameter.getMethod().isAnnotationPresent(AESDecryptBody.class) ||
                methodParameter.getMethod().isAnnotationPresent(DESDecryptBody.class) ||
                methodParameter.getMethod().isAnnotationPresent(RSADecryptBody.class);
    }

    /**
     * 无请求时的处理
     *
     * @param body          body
     * @param inputMessage  message
     * @param parameter     parameter
     * @param targetType    targetType
     * @param converterType converterType
     * @return body
     */
    @Override

    public Object handleEmptyBody(Object body, @NonNull HttpInputMessage inputMessage, @NonNull MethodParameter parameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    /**
     * 读取参数前执行
     * 在此做些编码 / 解密 / 封装参数为对象的操作
     *
     * @param inputMessage  inputMessage
     * @param parameter     parameter
     * @param targetType    targetType
     * @param converterType converterType
     * @return 解密后的内容
     * @throws IOException IOException
     */
    @Override
    @NonNull
    public HttpInputMessage beforeBodyRead(@NonNull HttpInputMessage inputMessage, @NonNull MethodParameter parameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

//        if (inputMessage.getBody() == null) {
//            return inputMessage;
//        }

        String body;

        try {
            body = IOUtils.toString(inputMessage.getBody(), config.getEncoding());
        } catch (Exception e) {
            throw new DecryptBodyFailException("Unable to get request body data," +
                    " please check if the sending data body or request method is in compliance with the specification." +
                    " (无法获取请求正文数据，请检查发送数据体或请求方法是否符合规范。)");
        }
        if (body == null || StringUtils.isNullOrEmpty(body)) {
            throw new DecryptBodyFailException("The request body is NULL or an empty string, so the decryption failed." +
                    " (请求正文为NULL或为空字符串，因此解密失败。)");
        }
        String decryptBody = null;
        DecryptAnnotationInfoBean methodAnnotation = this.getMethodAnnotation(parameter);
        if (methodAnnotation != null) {
            decryptBody = switchDecrypt(body, methodAnnotation);
        } else {
            DecryptAnnotationInfoBean classAnnotation = this.getClassAnnotation(parameter.getDeclaringClass());
            if (classAnnotation != null) {
                decryptBody = switchDecrypt(body, classAnnotation);
            }
        }
        if (decryptBody == null) {
            throw new DecryptBodyFailException("Decryption error, " +
                    "please check if the selected source data is encrypted correctly." +
                    " (解密错误，请检查选择的源数据的加密方式是否正确。)");
        }
        try {
            InputStream inputStream = IOUtils.toInputStream(decryptBody, config.getEncoding());
            return new DecryptHttpInputMessage(inputStream, inputMessage.getHeaders());
        } catch (Exception e) {
            throw new DecryptBodyFailException("The string is converted to a stream format exception." +
                    " Please check if the format such as encoding is correct." +
                    " (字符串转换成流格式异常，请检查编码等格式是否正确。)");
        }
    }

    /**
     * 读取参数后执行
     *
     * @param body          body
     * @param inputMessage  inputMessage
     * @param parameter     parameter
     * @param targetType    targetType
     * @param converterType converterType
     * @return 读取参数后执行
     */
    @Override
    @NonNull
    public Object afterBodyRead(@NonNull Object body, @NonNull HttpInputMessage inputMessage, @NonNull MethodParameter parameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    /**
     * 获取方法控制器上的加密注解信息
     *
     * @param methodParameter 控制器方法
     * @return 加密注解信息
     */
    private DecryptAnnotationInfoBean getMethodAnnotation(MethodParameter methodParameter) {

        if (methodParameter.getMethod().isAnnotationPresent(DecryptBody.class)) {
            DecryptBody decryptBody = methodParameter.getMethodAnnotation(DecryptBody.class);
            return DecryptAnnotationInfoBean.builder()
                    .decryptBodyMethod(decryptBody.value())
                    .key(decryptBody.otherKey())
                    .build();
        }
        if (methodParameter.getMethod().isAnnotationPresent(DESDecryptBody.class)) {
            return DecryptAnnotationInfoBean.builder()
                    .decryptBodyMethod(DecryptBodyMethod.DES)
                    .key(methodParameter.getMethodAnnotation(DESDecryptBody.class).otherKey())
                    .build();
        }
        if (methodParameter.getMethod().isAnnotationPresent(AESDecryptBody.class)) {
            return DecryptAnnotationInfoBean.builder()
                    .decryptBodyMethod(DecryptBodyMethod.AES)
                    .key(methodParameter.getMethodAnnotation(AESDecryptBody.class).otherKey())
                    .build();
        }
        return null;
    }

    /**
     * 获取类控制器上的加密注解信息
     *
     * @param clazz 控制器类
     * @return 加密注解信息
     */
    private DecryptAnnotationInfoBean getClassAnnotation(Class<?> clazz) {
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        if (annotations != null && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof DecryptBody) {
                    DecryptBody decryptBody = (DecryptBody) annotation;
                    return DecryptAnnotationInfoBean.builder()
                            .decryptBodyMethod(decryptBody.value())
                            .key(decryptBody.otherKey())
                            .build();
                }
                if (annotation instanceof DESDecryptBody) {
                    return DecryptAnnotationInfoBean.builder()
                            .decryptBodyMethod(DecryptBodyMethod.DES)
                            .key(((DESDecryptBody) annotation).otherKey())
                            .build();
                }
                if (annotation instanceof AESDecryptBody) {
                    return DecryptAnnotationInfoBean.builder()
                            .decryptBodyMethod(DecryptBodyMethod.AES)
                            .key(((AESDecryptBody) annotation).otherKey())
                            .build();
                }
            }
        }
        return null;
    }


    /**
     * 选择加密方式并进行解密
     *
     * @param formatStringBody 目标解密字符串
     * @param infoBean         加密信息
     * @return 解密结果
     */
    private String switchDecrypt(String formatStringBody, DecryptAnnotationInfoBean infoBean) {
        DecryptBodyMethod method = infoBean.getDecryptBodyMethod();
        if (method == null) {
            throw new DecryptMethodNotFoundException();
        }
        String key = infoBean.getKey();
        if (method == DecryptBodyMethod.DES) {
            key = CheckUtils.checkAndGetKey(config.getDesKey(), key, "DES-KEY");
            return DESEncryptUtil.decrypt(formatStringBody, key);
        }
        if (method == DecryptBodyMethod.AES) {
            key = CheckUtils.checkAndGetKey(config.getAesKey(), key, "AES-KEY");
            return AESEncryptUtil.decrypt(formatStringBody, key);
        }
        throw new DecryptBodyFailException();
    }
}
