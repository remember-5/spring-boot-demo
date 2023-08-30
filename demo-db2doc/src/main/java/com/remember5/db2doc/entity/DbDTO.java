package com.remember5.db2doc.entity;

import lombok.Data;

/**
 * @author wangjiahao
 * @date 2023/8/29 20:09
 */
@Data
public class DbDTO {
    private String dbType;
    private String driverClassName;

    private String ip;

    private Integer port;

    private String username;

    private String password;

    private String database;

    private String generateType;

    private String fileOutputDir;

    private String filename;
}
