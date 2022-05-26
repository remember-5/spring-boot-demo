package com.remember.dynamic.datasource.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;


@Builder
@TableName(value = "log_access")
@AllArgsConstructor
@NoArgsConstructor
public class LogAccess {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "v_method")
    private String vMethod;

    @TableField(value = "v_uri")
    private String vUri;

    @TableField(value = "v_ip")
    private String vIp;

    @TableField(value = "i_status")
    private Integer iStatus;

    @TableField(value = "v_type")
    private String vType;

    @TableField(value = "v_browser")
    private String vBrowser;

    @TableField(value = "b_success")
    private Boolean bSuccess;

    @TableField(value = "v_application")
    private String vApplication;

    @TableField(value = "v_data_id")
    private String vDataId;

    @TableField(value = "v_alias_at_app_module")
    private String vAliasAtAppModule;

    @TableField(value = "v_alias_at_app_module_function")
    private String vAliasAtAppModuleFunction;

    @TableField(value = "b_skip")
    private Boolean bSkip;

    @TableField(value = "id_at_auth_user")
    private String idAtAuthUser;

    @TableField(value = "t_create")
    private Date tCreate;

    @TableField(value = "v_body")
    private String vBody;

    @TableField(value = "id_at_app_module")
    private String idAtAppModule;

    @TableField(value = "v_device")
    private String vDevice;


}