package com.ass.dingshopping.bean;

public class ExcelBean {
    private String excelId;
    private String excelName;
    private String excelUser;
    private String excelRemark;
    private String excelTime;
    private String excelThumb;

    public ExcelBean(String excelId, String excelName, String excelUser, String excelRemark, String excelTime, String excelThumb) {
        this.excelId = excelId;
        this.excelName = excelName;
        this.excelUser = excelUser;
        this.excelRemark = excelRemark;
        this.excelTime = excelTime;
        this.excelThumb = excelThumb;
    }

    public String getExcelId() {
        return excelId;
    }

    public void setExcelId(String excelId) {
        this.excelId = excelId;
    }

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public String getExcelUser() {
        return excelUser;
    }

    public void setExcelUser(String excelUser) {
        this.excelUser = excelUser;
    }

    public String getExcelRemark() {
        return excelRemark;
    }

    public void setExcelRemark(String excelRemark) {
        this.excelRemark = excelRemark;
    }

    public String getExcelTime() {
        return excelTime;
    }

    public void setExcelTime(String excelTime) {
        this.excelTime = excelTime;
    }

    public String getExcelThumb() {
        return excelThumb;
    }

    public void setExcelThumb(String excelThumb) {
        this.excelThumb = excelThumb;
    }
}
