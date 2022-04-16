package com.archive.app.collect;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.archive.share.DataResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    class CategoryNode {
        String categoryId, categoryName, categoryNote;
        List<CategoryNode> children;

        public CategoryNode(String categoryId, String categoryName, String categoryNote, List<CategoryNode> children) {
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.categoryNote = categoryNote;
            this.children = children;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryNote() {
            return categoryNote;
        }

        public void setCategoryNote(String categoryNote) {
            this.categoryNote = categoryNote;
        }

        public List<CategoryNode> getChildren() {
            return children;
        }

        public void setChildren(List<CategoryNode> children) {
            this.children = children;
        }

    }

    

    @GetMapping("/api/collect/category")
    public DataResponse<CategoryNode> getCategory() {
        log.info("serving getCategory");
        List<Map<String, Object>> codes = jdbcTemplate.queryForList(
                "select category_id, category_name, category_level from t_category where category_level='1' order by category_id asc");
        List<CategoryNode> data = codes.stream()
                .map(c -> {
                    List<CategoryNode> children = jdbcTemplate.queryForList(
                            "select category_id, category_name, category_level, category_note from t_category where category_level='2' and parent_category='"
                                    + c.get("category_id").toString() + "' order by category_id asc")
                            .stream()
                            .map(v -> new CategoryNode(v.get("category_id").toString(),
                                    v.get("category_name").toString(), v.get("category_note")==null?"":v.get("category_note").toString(), null))
                            .collect(Collectors.toList());
                    CategoryNode cnode = new CategoryNode(c.get("category_id").toString(),
                            c.get("category_name").toString(),
                            c.get("category_note") == null ? "" : c.get("category_note").toString(), children);
                    return cnode;
                })
                .collect(Collectors.toList());
        return new DataResponse<CategoryNode>(data);
    }
}
