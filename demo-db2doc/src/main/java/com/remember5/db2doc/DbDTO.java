package com.remember5.db2doc;

/**
 * @author wangjiahao
 * @date 2023/8/29 20:09
 */
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

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getGenerateType() {
        return generateType;
    }

    public void setGenerateType(String generateType) {
        this.generateType = generateType;
    }

    public String getFileOutputDir() {
        return fileOutputDir;
    }

    public void setFileOutputDir(String fileOutputDir) {
        this.fileOutputDir = fileOutputDir;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
