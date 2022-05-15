package com.archive.app.warehouse;

import java.util.UUID;

import com.archive.app.entities.Box;
import com.archive.app.entities.Rack;
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
public class BoxController {
        private static final Logger log = LoggerFactory.getLogger(BoxController.class);
        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/box/list")
        public DataResponse<Box> list(Box item) {
                log.info("serving " + item.getBoxId());
                String query = "select box_id, box_no, rack_id, archive_no, series_no from t_box where 1=1";

                if (item != null && item.getArchiveNo() != null) {
                        query += "and archive_no = '" + item.getArchiveNo() + "'";
                }
                if (item != null && item.getSeriesNo() != null) {
                        query += "and a.series_no = '" + item.getSeriesNo() + "'";
                }
                return new DataResponse<Box>(jdbcTemplate
                                .queryForList(query)
                                .stream()
                                .map(v -> DataUtils.composeEntity(v, Box.class))
                                .toList());
        }

        @PostMapping("/api/box/save")
        public void saveOrUpate(Box item, HttpServletRequest request) {
                boolean isupdate = true;
                if (item.getBoxId() == null) {
                        isupdate = false;
                        item.setBoxId(UUID.randomUUID().toString());
                }
                String[] insertUpdate = DataUtils.parseInsertUpdate(item,
                                "box_id,box_no,rack_id,archive_no,series_no"
                                                .split(","),
                                "archive.t_box");
                if (isupdate) {
                        jdbcTemplate.update(insertUpdate[1]);
                } else {

                        jdbcTemplate.update(insertUpdate[0]);
                }
        }
}
