package com.lanou.bean;

import java.util.Date;

/**
 * @author dllo
 */
public class Account {
    private Integer accountId;//账务账号id

    private Integer recommenderId;//推荐人id

    private String loginName;//登录名

    private String loginPasswd;//登录密码

    private String status;//状态

    private String createDate;//创建时间

    private String pauseDate;//暂停日期

    private String closeDate;//关闭日期

    private String realName;//真名

    private String idcardNo;//身份证号

    private String birthdate;//生日日期

    private String gender;//性别

    private String occupation;//职业

    private String telephone;//电话号码

    private String email;//邮件

    private String mailaddress;//通信地址

    private String zipcode;//邮政编码

    private String qq;//QQ号码

    private String lastLoginTime;//上次登录时间

    private String lastLoginIp;//上次登录ip

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getRecommenderId() {
        return recommenderId;
    }

    public void setRecommenderId(Integer recommenderId) {
        this.recommenderId = recommenderId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
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

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo == null ? null : idcardNo.trim();
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress == null ? null : mailaddress.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", recommenderId=" + recommenderId +
                ", loginName='" + loginName + '\'' +
                ", loginPasswd='" + loginPasswd + '\'' +
                ", status='" + status + '\'' +
                ", createDate='" + createDate + '\'' +
                ", pauseDate='" + pauseDate + '\'' +
                ", closeDate='" + closeDate + '\'' +
                ", realName='" + realName + '\'' +
                ", idcardNo='" + idcardNo + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", gender='" + gender + '\'' +
                ", occupation='" + occupation + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", mailaddress='" + mailaddress + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", qq='" + qq + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                '}';
    }
}