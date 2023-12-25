/**
 * Copyright [2022] [remember5]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.remember.mybatisplus.interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.remember.mybatisplus.annotation.FiledEncrypt;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;

import java.lang.reflect.Field;
import java.sql.SQLException;

/**
 * 实现mybatis plus 自定义拦截器
 *
 * @author wangjiahao
 * @date 2023/12/25 17:26
 */
public class EncryptCustomInterceptor implements InnerInterceptor {
    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        // 模拟自定义注解加密
        final Class<?> parameterClazz = parameter.getClass();
        final Field[] declaredFields = parameterClazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field != null && field.isAnnotationPresent(FiledEncrypt.class)) {
                try {
                    field.setAccessible(true);
                    Object fieldValue = field.get(parameter);
                    String encryptedValue = "加密";
                    field.set(parameter, encryptedValue);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        InnerInterceptor.super.beforeUpdate(executor, ms, parameter);
    }
}
