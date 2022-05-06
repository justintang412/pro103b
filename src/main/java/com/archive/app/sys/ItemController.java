package com.archive.app.sys;

import com.archive.app.entities.Item;
import com.archive.share.DataResponse;
import com.archive.share.DataUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ItemController {
        private static final Logger log = LoggerFactory.getLogger(ArchiveGroupController.class);
        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/sys/item/list")
        public DataResponse<Item> list(Item item, HttpServletRequest request) {
                log.info("serving " + item.getItemId());
                String query = "select item_id, file_no, item_no, item_date, item_size, item_full_name, " +
                                "item_format, item_version, e_item_no, department_e_item_no, global_e_item_no, " +
                                "key_words, pages, pysical_file_id, receiver, receive_date, remark " +
                                "from t_item ";
                if (item.getItemId() != null) {
                        return new DataResponse<Item>(jdbcTemplate
                                        .queryForList(query + " where item_id='" + item.getItemId() + "'")
                                        .stream()
                                        .map(v -> DataUtils.composeEntity(v, Item.class))
                                        .toList());
                }

                if (item.getFileNo() != null) {
                        query += " where file_no='" + item.getFileNo() + "' order by item_no asc";
                }
                return new DataResponse<Item>(jdbcTemplate
                                .queryForList(query)
                                .stream()
                                .map(v -> DataUtils.composeEntity(v, Item.class))
                                .toList());
        }

        @PostMapping("/api/sys/item/save")
        public void saveOrUpate(Item item, HttpServletRequest request) {
                String[] insertUpdate = DataUtils.parseInsertUpdate(item,
                                new String[] { "item_id", "file_no", "item_no", "item_date", "item_size",
                                                "item_full_name", "item_format", "item_version", "e_item_no",
                                                "department_e_item_no", "global_e_item_no", "key_words", "pages",
                                                "pysical_file_id", "receiver", "receive_date", "remark" },
                                "archive.t_item");
                if (this.list(item, request).getData().size() > 0) {
                        jdbcTemplate.update(insertUpdate[1]);
                } else {
                        jdbcTemplate.update(insertUpdate[0]);
                }
        }
}
