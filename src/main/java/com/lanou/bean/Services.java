package com.lanou.bean;

import java.util.Date;

/**
 * @author dllo
 */
public class Services {
    private Integer serviceId;//业务id

    private Integer accountId;//账务账号id

    private String unixHost;//服务器ip

    private String osUsername;//os账号

    private String loginPasswd;//密码

    private String status;//状态

    private String createDate;//创建日期

    private String pauseDate;//暂停日期

    private String closeDate;//删除日期

    private Integer costId;//资费id

    //添加资费的属性
    private Cost cost;
    //添加账户账号的属性
    private Account account;

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUnixHost() {
        return unixHost;
    }

    public void setUnixHost(String unixHost) {
        this.unixHost = unixHost == null ? null : unixHost.trim();
    }

    public String getOsUsername() {
        return osUsername;
    }

    public void setOsUsername(String osUsername) {
        this.osUsername = osUsername == null ? null : osUsername.trim();
    }

    public String getLoginPasswd() {
        return loginPasswd;
    }

    public void setLoginPasswd(String loginPasswd) {
        this.loginPasswd = loginPasswd == null ? null : loginPasswd.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPauseDate() {
        return pauseDate;
    }

    public void setPauseDate(String pauseDate) {
        this.pauseDate = pauseDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    @Override
    public String toString() {
        return "Services{" +
                "serviceId=" + serviceId +
                ", accountId=" + accountId +
                ", unixHost='" + unixHost + '\'' +
                ", osUsername='" + osUsername + '\'' +
                ", loginPasswd='" + loginPasswd + '\'' +
                ", status='" + status + '\'' +
                ", createDate='" + createDate + '\'' +
                ", pauseDate='" + pauseDate + '\'' +
                ", closeDate='" + closeDate + '\'' +
                ", costId=" + costId +
                ", cost=" + cost +
                ", account=" + account +
                '}';
    }
}