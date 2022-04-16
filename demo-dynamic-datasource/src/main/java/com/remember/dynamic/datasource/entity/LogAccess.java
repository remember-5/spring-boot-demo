package com.remember.dynamic.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "log_access")
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

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return v_method
     */
    public String getvMethod() {
        return vMethod;
    }

    /**
     * @param vMethod
     */
    public void setvMethod(String vMethod) {
        this.vMethod = vMethod;
    }

    /**
     * @return v_uri
     */
    public String getvUri() {
        return vUri;
    }

    /**
     * @param vUri
     */
    public void setvUri(String vUri) {
        this.vUri = vUri;
    }

    /**
     * @return v_ip
     */
    public String getvIp() {
        return vIp;
    }

    /**
     * @param vIp
     */
    public void setvIp(String vIp) {
        this.vIp = vIp;
    }

    /**
     * @return i_status
     */
    public Integer getiStatus() {
        return iStatus;
    }

    /**
     * @param iStatus
     */
    public void setiStatus(Integer iStatus) {
        this.iStatus = iStatus;
    }

    /**
     * @return v_type
     */
    public String getvType() {
        return vType;
    }

    /**
     * @param vType
     */
    public void setvType(String vType) {
        this.vType = vType;
    }

    /**
     * @return v_browser
     */
    public String getvBrowser() {
        return vBrowser;
    }

    /**
     * @param vBrowser
     */
    public void setvBrowser(String vBrowser) {
        this.vBrowser = vBrowser;
    }

    /**
     * @return b_success
     */
    public Boolean getbSuccess() {
        return bSuccess;
    }

    /**
     * @param bSuccess
     */
    public void setbSuccess(Boolean bSuccess) {
        this.bSuccess = bSuccess;
    }

    /**
     * @return v_application
     */
    public String getvApplication() {
        return vApplication;
    }

    /**
     * @param vApplication
     */
    public void setvApplication(String vApplication) {
        this.vApplication = vApplication;
    }

    /**
     * @return v_data_id
     */
    public String getvDataId() {
        return vDataId;
    }

    /**
     * @param vDataId
     */
    public void setvDataId(String vDataId) {
        this.vDataId = vDataId;
    }

    /**
     * @return v_alias_at_app_module
     */
    public String getvAliasAtAppModule() {
        return vAliasAtAppModule;
    }

    /**
     * @param vAliasAtAppModule
     */
    public void setvAliasAtAppModule(String vAliasAtAppModule) {
        this.vAliasAtAppModule = vAliasAtAppModule;
    }

    /**
     * @return v_alias_at_app_module_function
     */
    public String getvAliasAtAppModuleFunction() {
        return vAliasAtAppModuleFunction;
    }

    /**
     * @param vAliasAtAppModuleFunction
     */
    public void setvAliasAtAppModuleFunction(String vAliasAtAppModuleFunction) {
        this.vAliasAtAppModuleFunction = vAliasAtAppModuleFunction;
    }

    /**
     * @return b_skip
     */
    public Boolean getbSkip() {
        return bSkip;
    }

    /**
     * @param bSkip
     */
    public void setbSkip(Boolean bSkip) {
        this.bSkip = bSkip;
    }

    /**
     * @return id_at_auth_user
     */
    public String getIdAtAuthUser() {
        return idAtAuthUser;
    }

    /**
     * @param idAtAuthUser
     */
    public void setIdAtAuthUser(String idAtAuthUser) {
        this.idAtAuthUser = idAtAuthUser;
    }

    /**
     * @return t_create
     */
    public Date gettCreate() {
        return tCreate;
    }

    /**
     * @param tCreate
     */
    public void settCreate(Date tCreate) {
        this.tCreate = tCreate;
    }

    /**
     * @return v_body
     */
    public String getvBody() {
        return vBody;
    }

    /**
     * @param vBody
     */
    public void setvBody(String vBody) {
        this.vBody = vBody;
    }

    /**
     * @return id_at_app_module
     */
    public String getIdAtAppModule() {
        return idAtAppModule;
    }

    /**
     * @param idAtAppModule
     */
    public void setIdAtAppModule(String idAtAppModule) {
        this.idAtAppModule = idAtAppModule;
    }

    /**
     * @return v_device
     */
    public String getvDevice() {
        return vDevice;
    }

    /**
     * @param vDevice
     */
    public void setvDevice(String vDevice) {
        this.vDevice = vDevice;
    }
}