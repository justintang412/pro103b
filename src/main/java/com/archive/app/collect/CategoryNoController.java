package com.archive.app.collect;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryNoController {
    private static final Logger log = LoggerFactory.getLogger(CategoryNoController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    class CategoryNo {
        String categoryNo, categoryNoNote;

        public CategoryNo(String categoryNo, String categoryNoNote) {
            this.categoryNo = categoryNo;
            this.categoryNoNote = categoryNoNote;
        }

        public String getCategoryNo() {
            return categoryNo;
        }

        public void setCategoryNo(String categoryNo) {
            this.categoryNo = categoryNo;
        }

        public String getCategoryNoNote() {
            return categoryNoNote;
        }

        public void setCategoryNoNote(String categoryNoNote) {
            this.categoryNoNote = categoryNoNote;
        }

    }

    class CategoryNoResponse {
        List<CategoryNo> data;

        public CategoryNoResponse(List<CategoryNo> data) {
            this.data = data;
        }

        public List<CategoryNo> getData() {
            return data;
        }

        public void setData(List<CategoryNo> data) {
            this.data = data;
        }

    }

    @GetMapping("/ccas/collect/categoryno")
    public CategoryNoResponse getCategoryNo() {
        log.info("serving getCategoryNo");
        List<CategoryNo> codeList = jdbcTemplate.queryForList(
                "select category_no, category_no_note from t_category_no order by category_no asc").stream()
                .map(c -> new CategoryNo(c.get("category_no").toString(), c.get("category_no_note").toString()))
                .collect(Collectors.toList());

        return new CategoryNoResponse(codeList);
    }
}
