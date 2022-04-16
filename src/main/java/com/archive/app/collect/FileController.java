package com.archive.app.collect;

import java.util.List;
import java.util.stream.Collectors;

import com.archive.share.DataResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    class DocFile {
        Object docId, registerNo, urgentLevel, expireDate, ownerDept, secretLevel, isArchive,
                processNode, createYear, docNo, volumnNo, title, keywords,
                receiver, dispatchNote, remark, createDate, category, categoryNo, fromDept, copyCount, pageCount,
                browserNote, status;

        public DocFile(Object docId, Object registerNo, Object urgentLevel, Object expireDate, Object ownerDept,
                Object secretLevel, Object isArchive, Object processNode, Object createYear, Object docNo,
                Object volumnNo, Object title, Object keywords, Object receiver, Object dispatchNote, Object remark,
                Object createDate, Object category, Object categoryNo, Object fromDept, Object copyCount,
                Object pageCount, Object browserNote, Object status) {
            this.docId = docId;
            this.registerNo = registerNo;
            this.urgentLevel = urgentLevel;
            this.expireDate = expireDate;
            this.ownerDept = ownerDept;
            this.secretLevel = secretLevel;
            this.isArchive = isArchive;
            this.processNode = processNode;
            this.createYear = createYear;
            this.docNo = docNo;
            this.volumnNo = volumnNo;
            this.title = title;
            this.keywords = keywords;
            this.receiver = receiver;
            this.dispatchNote = dispatchNote;
            this.remark = remark;
            this.createDate = createDate;
            this.category = category;
            this.categoryNo = categoryNo;
            this.fromDept = fromDept;
            this.copyCount = copyCount;
            this.pageCount = pageCount;
            this.browserNote = browserNote;
            this.status = status;
        }

        public Object getDocId() {
            return docId;
        }

        public void setDocId(Object docId) {
            this.docId = docId;
        }

        public Object getRegisterNo() {
            return registerNo;
        }

        public void setRegisterNo(Object registerNo) {
            this.registerNo = registerNo;
        }

        public Object getUrgentLevel() {
            return urgentLevel;
        }

        public void setUrgent_level(Object urgentLevel) {
            this.urgentLevel = urgentLevel;
        }

        public Object getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(Object expireDate) {
            this.expireDate = expireDate;
        }

        public Object getOwnerDept() {
            return ownerDept;
        }

        public void setOwnerDept(Object ownerDept) {
            this.ownerDept = ownerDept;
        }

        public Object getSecretLevel() {
            return secretLevel;
        }

        public void setSecretLevel(Object secretLevel) {
            this.secretLevel = secretLevel;
        }

        public Object getIsArchive() {
            return isArchive;
        }

        public void setIsArchive(Object isArchive) {
            this.isArchive = isArchive;
        }

        public Object getProcessNode() {
            return processNode;
        }

        public void setProcessNode(Object processNode) {
            this.processNode = processNode;
        }

        public Object getCreateYear() {
            return createYear;
        }

        public void setCreateYear(Object createYear) {
            this.createYear = createYear;
        }

        public Object getDocNo() {
            return docNo;
        }

        public void setDocNo(Object docNo) {
            this.docNo = docNo;
        }

        public Object getVolumnNo() {
            return volumnNo;
        }

        public void setVolumnNo(Object volumnNo) {
            this.volumnNo = volumnNo;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getKeywords() {
            return keywords;
        }

        public void setKeywords(Object keywords) {
            this.keywords = keywords;
        }

        public Object getReceiver() {
            return receiver;
        }

        public void setReceiver(Object receiver) {
            this.receiver = receiver;
        }

        public Object getDispatchNote() {
            return dispatchNote;
        }

        public void setDispatchNote(Object dispatchNote) {
            this.dispatchNote = dispatchNote;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public Object getCategory() {
            return category;
        }

        public void setCategory(Object category) {
            this.category = category;
        }

        public Object getCategoryNo() {
            return categoryNo;
        }

        public void setCategoryNo(Object categoryNo) {
            this.categoryNo = categoryNo;
        }

        public Object getFromDept() {
            return fromDept;
        }

        public void setFromDept(Object fromDept) {
            this.fromDept = fromDept;
        }

        public Object getCopyCount() {
            return copyCount;
        }

        public void setCopyCount(Object copyCount) {
            this.copyCount = copyCount;
        }

        public Object getPageCount() {
            return pageCount;
        }

        public void setPageCount(Object pageCount) {
            this.pageCount = pageCount;
        }

        public Object getBrowserNote() {
            return browserNote;
        }

        public void setBrowserNote(Object browserNote) {
            this.browserNote = browserNote;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

    }

    class SearchForm {
        String beginTime, endTime, secretLevel, volumnNo, docNo, isAchived, category, categoryNo, key;

        public SearchForm(String beginTime, String endTime, String secretLevel, String volumnNo, String docNo,
                String isAchived, String category, String categoryNo, String key) {
            this.beginTime = beginTime;
            this.endTime = endTime;
            this.secretLevel = secretLevel;
            this.volumnNo = volumnNo;
            this.docNo = docNo;
            this.isAchived = isAchived;
            this.category = category;
            this.categoryNo = categoryNo;
            this.key = key;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getSecretLevel() {
            return secretLevel;
        }

        public void setSecretLevel(String secretLevel) {
            this.secretLevel = secretLevel;
        }

        public String getVolumnNo() {
            return volumnNo;
        }

        public void setVolumnNo(String volumnNo) {
            this.volumnNo = volumnNo;
        }

        public String getDocNo() {
            return docNo;
        }

        public void setDocNo(String docNo) {
            this.docNo = docNo;
        }

        public String getIsAchived() {
            return isAchived;
        }

        public void setIsAchived(String isAchived) {
            this.isAchived = isAchived;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCategoryNo() {
            return categoryNo;
        }

        public void setCategoryNo(String categoryNo) {
            this.categoryNo = categoryNo;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

    }

    @GetMapping("/api/collect/files")
    public DataResponse<DocFile> getDocFiles(SearchForm searchForm) {
        log.info("--------------getDocFiles------------------");
        String sql = "SELECT doc_id, register_no, urgent_level, expire_date, owner_dept, secret_level, " +
                "is_archived, process_note, create_year, doc_no, volumn_no, title, keywords, receiver, " +
                "dispatch_note, remark, create_date, category, category_no, from_dept, copy_count, page_count," +
                " browser_note, status FROM archive.t_doc where 1=1 ";
        if (searchForm.beginTime != null && searchForm.endTime != null) {
            sql += "and create_year<'" + searchForm.endTime + "' and create_year>='" + searchForm.endTime + "' ";
        }
        if (searchForm.secretLevel != null) {
            sql += "and secret_level='" + searchForm.secretLevel + "' ";
        }
        if (searchForm.volumnNo != null) {
            sql += "and volumn_no='" + searchForm.volumnNo + "' ";
        }
        if (searchForm.docNo != null) {
            sql += "and doc_no='" + searchForm.docNo + "' ";
        }
        if (searchForm.isAchived != null) {
            sql += "and is_archived='" + searchForm.isAchived + "' ";
        }
        if (searchForm.category != null) {
            sql += "and category='" + searchForm.category + "' ";
        }
        if (searchForm.categoryNo != null) {
            sql += "and category_no='" + searchForm.categoryNo + "' ";
        }
        if (searchForm.key != null) {
            sql += "and (";
            String keys[] = searchForm.key.split(" ");
            for (String k : keys) {
                sql += "title like '%" + k + "%' or ";
            }
            sql = sql.substring(0, sql.length() - 3);
            sql += ")";
        }
        sql += "order by doc_id desc";
        List<DocFile> list = jdbcTemplate.queryForList(sql)
                .stream()
                .map(v -> new DocFile(v.get("doc_id"), v.get("register_no"), v.get("urgent_level"),
                        v.get("expire_date"), v.get("owner_dept"),
                        v.get("secret_level"), v.get("is_archived"), v.get("process_note"), v.get("create_year"),
                        v.get("doc_no"),
                        v.get("volumn_no"), v.get("title"), v.get("keywords"), v.get("receiver"),
                        v.get("dispatch_note"), v.get("remark"),
                        v.get("create_date"), v.get("category"), v.get("category_no"), v.get("from_dept"),
                        v.get("copy_count"),
                        v.get("page_count"), v.get("browser_note"), v.get("status")))
                .collect(Collectors.toList());

        return new DataResponse<DocFile>(list);
    }

}
