package com.remember5.elasticsearch7.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.sql.Timestamp;

/**
 * @author wangjiahao
 * @date 2022/5/27 00:06
 */
@Data
@Document(indexName="log_access")
public class EsLogAccessEntity {

    private String id;
    private String vMethod;
    private String vUri;
    private String vIp;
    private Integer iStatus;
    private String vType;
    private String vBrowser;
    private Boolean bSuccess;
    private String vApplication;
    private String vDataId;
    private String vAliasAtAppModule;
    private String vAliasAtAppModuleFunction;
    private Boolean bSkip;
    private String idAtAuthUser;
    private Timestamp tCreate;
    private String vBody;
    private String idAtAppModule;
    private String vDevice;


}
