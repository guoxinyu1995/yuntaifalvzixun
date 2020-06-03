package com.ytfu.yuntaifawu.ui.mine.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证律师提交数据的javabean封装,封装必要数据进行提交
 */
public class CertifiedLawyerBean {

    private String id;
    private File avatar;
    private String name;
    private String age;
    private String edu;
    private String organization;
    private String address;
    private String goodAt;
    private String resume;

    private File license;//执业证
    private File record;//备案
    private File idCardFront;
    private File idCardBack;


    public CertifiedLawyerBean() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoodAt() {
        return goodAt;
    }

    public void setGoodAt(String goodAt) {
        this.goodAt = goodAt;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public File getLicense() {
        return license;
    }

    public void setLicense(File license) {
        this.license = license;
    }

    public File getRecord() {
        return record;
    }

    public void setRecord(File record) {
        this.record = record;
    }

    public File getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(File idCardFront) {
        this.idCardFront = idCardFront;
    }

    public File getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(File idCardBack) {
        this.idCardBack = idCardBack;
    }
}
