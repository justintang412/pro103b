package com.archive.app.entities;

public class Series {
    String seriesNo, seriesName, archiveNo, fileType, departmentId,
            sortNo;

    public String getSeriesNo() {
        return seriesNo;
    }

    public void setSeriesNo(String seriesNo) {
        this.seriesNo = seriesNo;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getArchiveNo() {
        return archiveNo;
    }

    public void setArchiveNo(String archiveNo) {
        this.archiveNo = archiveNo;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

}