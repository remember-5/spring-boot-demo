package com.remember.dynamic.datasource.jpa.entity;

import lombok.Builder;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author wangjiahao
 * @date 2022/5/26 12:29
 */
@Builder
@Entity
@Table(name = "log_access", schema = "public", catalog = "spring_boot_demo")
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getvMethod() {
        return vMethod;
    }

    public void setvMethod(String vMethod) {
        this.vMethod = vMethod;
    }

    public String getvUri() {
        return vUri;
    }

    public void setvUri(String vUri) {
        this.vUri = vUri;
    }

    public String getvIp() {
        return vIp;
    }

    public void setvIp(String vIp) {
        this.vIp = vIp;
    }

    public Integer getiStatus() {
        return iStatus;
    }

    public void setiStatus(Integer iStatus) {
        this.iStatus = iStatus;
    }

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public String getvBrowser() {
        return vBrowser;
    }

    public void setvBrowser(String vBrowser) {
        this.vBrowser = vBrowser;
    }

    public Boolean getbSuccess() {
        return bSuccess;
    }

    public void setbSuccess(Boolean bSuccess) {
        this.bSuccess = bSuccess;
    }

    public String getvApplication() {
        return vApplication;
    }

    public void setvApplication(String vApplication) {
        this.vApplication = vApplication;
    }

    public String getvDataId() {
        return vDataId;
    }

    public void setvDataId(String vDataId) {
        this.vDataId = vDataId;
    }

    public String getvAliasAtAppModule() {
        return vAliasAtAppModule;
    }

    public void setvAliasAtAppModule(String vAliasAtAppModule) {
        this.vAliasAtAppModule = vAliasAtAppModule;
    }

    public String getvAliasAtAppModuleFunction() {
        return vAliasAtAppModuleFunction;
    }

    public void setvAliasAtAppModuleFunction(String vAliasAtAppModuleFunction) {
        this.vAliasAtAppModuleFunction = vAliasAtAppModuleFunction;
    }

    public Boolean getbSkip() {
        return bSkip;
    }

    public void setbSkip(Boolean bSkip) {
        this.bSkip = bSkip;
    }

    public String getIdAtAuthUser() {
        return idAtAuthUser;
    }

    public void setIdAtAuthUser(String idAtAuthUser) {
        this.idAtAuthUser = idAtAuthUser;
    }

    public Timestamp gettCreate() {
        return tCreate;
    }

    public void settCreate(Timestamp tCreate) {
        this.tCreate = tCreate;
    }

    public String getvBody() {
        return vBody;
    }

    public void setvBody(String vBody) {
        this.vBody = vBody;
    }

    public String getIdAtAppModule() {
        return idAtAppModule;
    }

    public void setIdAtAppModule(String idAtAppModule) {
        this.idAtAppModule = idAtAppModule;
    }

    public String getvDevice() {
        return vDevice;
    }

    public void setvDevice(String vDevice) {
        this.vDevice = vDevice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogAccessEntity that = (LogAccessEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (vMethod != null ? !vMethod.equals(that.vMethod) : that.vMethod != null) return false;
        if (vUri != null ? !vUri.equals(that.vUri) : that.vUri != null) return false;
        if (vIp != null ? !vIp.equals(that.vIp) : that.vIp != null) return false;
        if (iStatus != null ? !iStatus.equals(that.iStatus) : that.iStatus != null) return false;
        if (vType != null ? !vType.equals(that.vType) : that.vType != null) return false;
        if (vBrowser != null ? !vBrowser.equals(that.vBrowser) : that.vBrowser != null) return false;
        if (bSuccess != null ? !bSuccess.equals(that.bSuccess) : that.bSuccess != null) return false;
        if (vApplication != null ? !vApplication.equals(that.vApplication) : that.vApplication != null) return false;
        if (vDataId != null ? !vDataId.equals(that.vDataId) : that.vDataId != null) return false;
        if (vAliasAtAppModule != null ? !vAliasAtAppModule.equals(that.vAliasAtAppModule) : that.vAliasAtAppModule != null)
            return false;
        if (vAliasAtAppModuleFunction != null ? !vAliasAtAppModuleFunction.equals(that.vAliasAtAppModuleFunction) : that.vAliasAtAppModuleFunction != null)
            return false;
        if (bSkip != null ? !bSkip.equals(that.bSkip) : that.bSkip != null) return false;
        if (idAtAuthUser != null ? !idAtAuthUser.equals(that.idAtAuthUser) : that.idAtAuthUser != null) return false;
        if (tCreate != null ? !tCreate.equals(that.tCreate) : that.tCreate != null) return false;
        if (vBody != null ? !vBody.equals(that.vBody) : that.vBody != null) return false;
        if (idAtAppModule != null ? !idAtAppModule.equals(that.idAtAppModule) : that.idAtAppModule != null)
            return false;
        if (vDevice != null ? !vDevice.equals(that.vDevice) : that.vDevice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vMethod != null ? vMethod.hashCode() : 0);
        result = 31 * result + (vUri != null ? vUri.hashCode() : 0);
        result = 31 * result + (vIp != null ? vIp.hashCode() : 0);
        result = 31 * result + (iStatus != null ? iStatus.hashCode() : 0);
        result = 31 * result + (vType != null ? vType.hashCode() : 0);
        result = 31 * result + (vBrowser != null ? vBrowser.hashCode() : 0);
        result = 31 * result + (bSuccess != null ? bSuccess.hashCode() : 0);
        result = 31 * result + (vApplication != null ? vApplication.hashCode() : 0);
        result = 31 * result + (vDataId != null ? vDataId.hashCode() : 0);
        result = 31 * result + (vAliasAtAppModule != null ? vAliasAtAppModule.hashCode() : 0);
        result = 31 * result + (vAliasAtAppModuleFunction != null ? vAliasAtAppModuleFunction.hashCode() : 0);
        result = 31 * result + (bSkip != null ? bSkip.hashCode() : 0);
        result = 31 * result + (idAtAuthUser != null ? idAtAuthUser.hashCode() : 0);
        result = 31 * result + (tCreate != null ? tCreate.hashCode() : 0);
        result = 31 * result + (vBody != null ? vBody.hashCode() : 0);
        result = 31 * result + (idAtAppModule != null ? idAtAppModule.hashCode() : 0);
        result = 31 * result + (vDevice != null ? vDevice.hashCode() : 0);
        return result;
    }
}
