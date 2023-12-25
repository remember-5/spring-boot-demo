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

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.remember.mybatisplus.annotation.FiledEncrypt;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Statement;

/**
 * 实现ibatis 的拦截器，这个只能和mybatis-plus二选一
 * 可以参考mybatis-plus的拦截器
 * @see DynamicTableNameInnerInterceptor
 *
 * @author wangjiahao
 * @date 2023/12/25 12:19
 */
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "update",
                args = {Statement.class}
        ),
        @Signature(
                type = StatementHandler.class,
                method = "query",
                args = {Statement.class, MetaObjectHandler.class}
        )
})
public class EncryptionInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object target = invocation.getTarget();

        if (target instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) target;
            MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
            MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

            BoundSql boundSql = statementHandler.getBoundSql();
            Object parameterObject = boundSql.getParameterObject();
            MetaObject parameterMetaObject = SystemMetaObject.forObject(parameterObject);

            switch (method.getName()) {
                case "update":
                    encryptInsertFields(parameterMetaObject);
                    return invocation.proceed();
                case "query":
                default:
                    return invocation.proceed();
            }
        }
        return invocation.proceed();
    }

    // 判断是否启用加解密
    private boolean isEncryptionEnabled(MetaObject parameterMetaObject) {
        // 根据你的逻辑判断是否启用加解密
        // 可以通过获取配置或其他方式来决定是否启用
        return true;
    }

    // 对INSERT操作进行加密处理
    private void encryptInsertFields(MetaObject parameterMetaObject) throws NoSuchFieldException {
        // 遍历参数对象的属性，判断是否有@Encryption注解
        for (String propertyName : parameterMetaObject.getGetterNames()) {
            final Class<?> clazz = parameterMetaObject.getOriginalObject().getClass();
            Field field = clazz.getDeclaredField(propertyName);
            if (field != null && field.isAnnotationPresent(FiledEncrypt.class)) {
                field.setAccessible(true);
                Object value = parameterMetaObject.getValue(propertyName);
                // 加密处理
                String encryptedValue = "加密";
                parameterMetaObject.setValue(propertyName, encryptedValue);
            }
        }
    }


//    public Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
//        Field field = clazz.getDeclaredField(fieldName);
//        field.setAccessible(true);
//        return field;
//    }
//    Class<?> clazz = entity.getClass();
//    Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//        if (field.isAnnotationPresent(DBEncrypted.class)) {
//            field.setAccessible(true);
//            String encryptedValue = (String) field.get(entity);
//            String encryptionType = field.getAnnotation(DBEncrypted.class).encryptionType();
//            field.set(entity, encrypt(encryptionType, encryptedValue));
//        }
//    }


//    public Object getField(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
//        Class<?> clazz = object.getClass();
//        Field field = clazz.getDeclaredField(fieldName);
//        field.setAccessible(true);
//        return field.get(object);
//    }

}
