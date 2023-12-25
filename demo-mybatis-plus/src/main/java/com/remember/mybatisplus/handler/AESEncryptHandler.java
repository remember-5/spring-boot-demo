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
package com.remember.mybatisplus.handler;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现自定义的handler 进行字段的加解密
 *
 * @author wangjiahao
 * @date 2023/12/25 16:31
 */
@Slf4j
@MappedJdbcTypes(JdbcType.VARCHAR)
public class AESEncryptHandler extends BaseTypeHandler<String> {
    private static final byte[] KEYS = "shc987654321camp".getBytes(StandardCharsets.UTF_8);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        log.info("set parameter : {}", parameter);
        if (StringUtils.isEmpty(parameter)) {
            ps.setString(i, null);
            return;
        }
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, KEYS);
        String encrypt = aes.encryptHex(parameter);
        ps.setString(i, encrypt);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return decrypt(rs.getString(columnName));
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return decrypt(rs.getString(columnIndex));
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return decrypt(cs.getString(columnIndex));
    }

    public String decrypt(String value) {
        if (null == value) {
            return null;
        }
        return new SymmetricCrypto(SymmetricAlgorithm.AES, KEYS).decryptStr(value);
    }
}
