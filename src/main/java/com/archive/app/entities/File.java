package com.archive.app.entities;

public class File {
    private String fileNo,
    departmentFileNo,
    globalFileNo,
    fileTitle,
    alternativeTitle,
    seriesNo,
    seriesName,
    fileYear,
    referGlobalFileNo,
    validYears,
    pages,
    securityClassification,
    departmentId,
    departmentName,
    archiveValue,
    storeLocation,
    archivedBy,
    dateArchived,
    remark;

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getDepartmentFileNo() {
        return departmentFileNo;
    }

    public void setDepartmentFileNo(String departmentFileNo) {
        this.departmentFileNo = departmentFileNo;
    }

    public String getGlobalFileNo() {
        return globalFileNo;
    }

    public void setGlobalFileNo(String globalFileNo) {
        this.globalFileNo = globalFileNo;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getAlternativeTitle() {
        return alternativeTitle;
    }

    public void setAlternativeTitle(String alternativeTitle) {
        this.alternativeTitle = alternativeTitle;
    }

    public String getSeriesNo() {
        return seriesNo;
    }

    public void setSeriesNo(String seriesNo) {
        this.seriesNo = seriesNo;
    }

    public String getFileYear() {
        return fileYear;
    }

    public void setFileYear(String fileYear) {
        this.fileYear = fileYear;
    }

    public String getReferGlobalFileNo() {
        return referGlobalFileNo;
    }

    public void setReferGlobalFileNo(String referGlobalFileNo) {
        this.referGlobalFileNo = referGlobalFileNo;
    }

    public String getValidYears() {
        return validYears;
    }

    public void setValidYears(String validYears) {
        this.validYears = validYears;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getSecurityClassification() {
        return securityClassification;
    }

    public void setSecurityClassification(String securityClassification) {
        this.securityClassification = securityClassification;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getArchiveValue() {
        return archiveValue;
    }

    public void setArchiveValue(String archiveValue) {
        this.archiveValue = archiveValue;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getArchivedBy() {
        return archivedBy;
    }

    public void setArchivedBy(String archivedBy) {
        this.archivedBy = archivedBy;
    }

    public String getDateArchived() {
        return dateArchived;
    }

    public void setDateArchived(String dateArchived) {
        this.dateArchived = dateArchived;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
