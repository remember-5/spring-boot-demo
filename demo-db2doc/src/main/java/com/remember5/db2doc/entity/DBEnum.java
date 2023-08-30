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
package com.remember5.db2doc.entity;

/**
 * @author wangjiahao
 * @date 2023/8/30 12:22
 */
@SuppressWarnings("unused")
public enum DBEnum {
    MYSQL("Mysql", "com.mysql.cj.jdbc.Driver"),
    MARIADB("MariaDB", "org.mariadb.jdbc.Driver"),
    ORACLE("Oracle", "oracle.jdbc.OracleDriver"),
    POSTGRESQL("PostgreSQL", "org.postgresql.Driver"),
    TIDB("TIDB", "com.mysql.cj.jdbc.Driver"),
    SQLSERVER("SqlServer", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
    // Uncomment the below line if needed
    // CACHEDB("Cache DB（2016）", "com.intersys.jdbc.CacheDriver");

    private String databaseName;
    private String driverClassName;

    DBEnum(String databaseName, String driverClassName) {
        this.databaseName = databaseName;
        this.driverClassName = driverClassName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public static String getDriverClassNameByKey(String databaseName) {
        for (DBEnum driver : DBEnum.values()) {
            if (driver.getDatabaseName().equals(databaseName)) {
                return driver.getDriverClassName();
            }
        }
        // 如果未找到匹配的键，可以返回 null 或抛出异常等处理方式
        return null;
    }
}
