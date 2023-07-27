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
package com.remember.dynamic.datasource.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.remember.dynamic.datasource.mybatisplus.service.ISqliteService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangjiahao
 * @date 2023/7/27 15:27
 */
@DS("sqlite")
@Service
@RequiredArgsConstructor
public class SqliteServiceImpl implements ISqliteService {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public void save() {
        jdbcTemplate.execute("insert into t_article (id, content) values (1, 'a');");
    }

    // ====================================================================================

    @Override
    public void twiceSave() {
        jdbcTemplate.execute("insert into t_article (id, content) values (2, 'b');");
        jdbcTemplate.execute("insert into t_article (id, content) values (2, 'bb');");
    }

    // ====================================================================================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tTwiceSave() {
        jdbcTemplate.execute("insert into t_article (id, content) values (3, 'c');");
        jdbcTemplate.execute("insert into t_article (id, content) values (3, 'cc');");
    }
    // ====================================================================================

    @Override
    public void callInner() throws Exception {
        callInner2();
        jdbcTemplate.execute("insert into t_article (id, content) values (4, 'd');");
    }

    public void callInner2() throws Exception {
        jdbcTemplate.execute("insert into t_article (id, content) values (4, 'dd');");
        throw new Exception();

    }

    // ====================================================================================
    @Override
    public void tCallInnerError() throws Exception {
        this.tCallInnerError2();
        jdbcTemplate.execute("insert into t_article (id, content) values (5, 'e');");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tCallInnerError2() throws Exception {
        jdbcTemplate.execute("insert into t_article (id, content) values (5, 'ee');");
        throw new Exception();
    }

    // ====================================================================================
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tCallInnerError3() throws Exception {
        this.CallInnerError2();
        jdbcTemplate.execute("insert into t_article (id, content) values (6, 'f');");
    }

    @Override
    public void CallInnerError2() throws Exception {
        jdbcTemplate.execute("insert into t_article (id, content) values (6, 'ff');");
        throw new Exception();
    }
    // ====================================================================================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tCallInner() throws Exception {
        this.tCallInner2();
        jdbcTemplate.execute("insert into t_article (id, content) values (7, 'g');");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tCallInner2() throws Exception {
        jdbcTemplate.execute("insert into t_article (id, content) values (7, 'gg');");
        throw new Exception();
    }
    // ====================================================================================
}
