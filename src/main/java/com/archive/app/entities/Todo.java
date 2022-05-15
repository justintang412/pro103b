package com.archive.app.entities;

public class Todo {
    String todoId, todoContent, applier,
            applierDepartmentId,
            applyDate,
            todoTable,
            todoField,
            approveDate,
            approveResult,
            approveMemo,
            approver;

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public String getTodoContent() {
        return todoContent;
    }

    public void setTodoContent(String todoContent) {
        this.todoContent = todoContent;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier;
    }

    public String getApplierDepartmentId() {
        return applierDepartmentId;
    }

    public void setApplierDepartmentId(String applierDepartmentId) {
        this.applierDepartmentId = applierDepartmentId;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getTodoTable() {
        return todoTable;
    }

    public void setTodoTable(String todoTable) {
        this.todoTable = todoTable;
    }

    public String getTodoField() {
        return todoField;
    }

    public void setTodoField(String todoField) {
        this.todoField = todoField;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getApproveMemo() {
        return approveMemo;
    }

    public void setApproveMemo(String approveMemo) {
        this.approveMemo = approveMemo;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

}
