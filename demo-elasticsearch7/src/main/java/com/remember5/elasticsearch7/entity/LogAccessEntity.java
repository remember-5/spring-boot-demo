package com.remember5.elasticsearch7.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author wangjiahao
 * @date 2022/5/27 00:06
 */
@Data
@Entity
@Builder
@Table(name = "log_access", schema = "public", catalog = "spring_boot_demo")
@AllArgsConstructor
@NoArgsConstructor
public class LogAccessEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "v_method")
    private String vMethod;
    @Basic
    @Column(name = "v_uri")
    private String vUri;
    @Basic
    @Column(name = "v_ip")
    private String vIp;
    @Basic
    @Column(name = "i_status")
    private Integer iStatus;
    @Basic
    @Column(name = "v_type")
    private String vType;
    @Basic
    @Column(name = "v_browser")
    private String vBrowser;
    @Basic
    @Column(name = "b_success")
    private Boolean bSuccess;
    @Basic
    @Column(name = "v_application")
    private String vApplication;
    @Basic
    @Column(name = "v_data_id")
    private String vDataId;
    @Basic
    @Column(name = "v_alias_at_app_module")
    private String vAliasAtAppModule;
    @Basic
    @Column(name = "v_alias_at_app_module_function")
    private String vAliasAtAppModuleFunction;
    @Basic
    @Column(name = "b_skip")
    private Boolean bSkip;
    @Basic
    @Column(name = "id_at_auth_user")
    private String idAtAuthUser;
    @Basic
    @Column(name = "t_create")
    private Timestamp tCreate;
    @Basic
    @Column(name = "v_body")
    private String vBody;
    @Basic
    @Column(name = "id_at_app_module")
    private String idAtAppModule;
    @Basic
    @Column(name = "v_device")
    private String vDevice;


}
