package com.lei.lemonvideo.model;

import com.google.gson.annotations.Expose;
import com.lei.lemonvideo.application.AppManager;

/**
 * Created by lei on 2017/12/4.
 */
public class ErrorInfo {

    public static final int ERROR_TYPE_HTTP = 1;
    public static final int ERROR_TYPE_URL = 2;
    public static final int ERROR_TYPE_FATAL = 3;

    //区分实体中不想被序列化的属性，自身包含序列化、反序列化
    @Expose
    int type;//错误类型

    @Expose
    int tag;//标签

    @Expose
    String functionName;

    @Expose
    String className;

    @Expose
    Site site;

    @Expose
    String reason;

    @Expose
    String exceptionString;

    public ErrorInfo(int siteId,int type){
        site = new Site(siteId, AppManager.getContext());
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getExceptionString() {
        return exceptionString;
    }

    public void setExceptionString(String exceptionString) {
        this.exceptionString = exceptionString;
    }
}
