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
package com.remember.dynamic.datasource.sqlite;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.remember.dynamic.datasource.SpringBootDemoDynamicDatasourceApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author wangjiahao
 * @date 2023/7/21 18:42
 */
@Slf4j
@DS("sqlite")
@SpringBootTest(classes = {SpringBootDemoDynamicDatasourceApplication.class})
public class SqliteTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @DS("sqlite")
    public void testSqlite() {
        jdbcTemplate.execute("CREATE TABLE user (\n" +
                "  id INTEGER PRIMARY KEY,\n" +
                "  username TEXT NOT NULL,\n" +
                "  password TEXT NOT NULL,\n" +
                "  email TEXT NOT NULL\n" +
                ");");
        final List<Map<String, Object>> maps = jdbcTemplate.queryForList("show databases;");
        System.err.println(maps);
    }

}
