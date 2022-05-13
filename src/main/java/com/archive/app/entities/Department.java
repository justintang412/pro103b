package com.archive.app.entities;

import java.util.List;

public class Department {
    String departmentId, departmentName, parentId;
    List<Department> children;
    public String getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public List<Department> getChildren() {
        return children;
    }
    public void setChildren(List<Department> children) {
        this.children = children;
    }

    
}
