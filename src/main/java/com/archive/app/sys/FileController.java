package com.archive.app.sys;

import com.archive.app.entities.File;
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
public class FileController {
        private static final Logger log = LoggerFactory.getLogger(ArchiveGroupController.class);
        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/sys/file/list")
        public DataResponse<File> list(File item, HttpServletRequest request) {
                log.info("serving " + item.getFileNo());
                String query = "select a.file_no,a.department_file_no, a.global_file_no, " +
                                "a.file_title, a.alternative_title, a.series_no, s.series_name, a.file_year, a.refer_global_file_no, "
                                +
                                "a.valid_years, a.pages, a.security_classification, a.department_id, d.department_name, a.archive_value, "
                                +
                                "a.store_location, a.archived_by, a.date_archived, a.remark " +
                                "from t_file a, t_department d, t_series s " +
                                "where a.department_id =d.department_id and a.series_no=s.series_no";
                if (item.getFileNo() != null) {
                        return new DataResponse<File>(jdbcTemplate
                                        .queryForList(query + " and a.file_no='" + item.getFileNo() + "'")
                                        .stream()
                                        .map(v -> DataUtils.composeEntity(v, File.class))
                                        .toList());
                }

                if (item.getDepartmentId() != null) {
                        query += " and a.department_id='" + item.getDepartmentId() + "'";
                }
                if (item.getFileTitle() != null) {
                        query += " and a.file_title like'%" + item.getFileTitle() + "%'";
                }
                if (item.getArchivedBy() != null) {
                        query += " and a.archived_by ='" + item.getArchivedBy() + "'";
                }

                return new DataResponse<File>(jdbcTemplate
                                .queryForList(query)
                                .stream()
                                .map(v -> DataUtils.composeEntity(v, File.class))
                                .toList());

        }

        @PostMapping("/api/sys/file/save")
        public void saveOrUpate(File item, HttpServletRequest request) {
                String[] insertUpdate = DataUtils.parseInsertUpdate(item,
                                new String[] { "file_no", "department_file_no", "global_file_no", "file_title",
                                                "alternative_title",
                                                "series_no", "file_year", "refer_global_file_no", "valid_years",
                                                "pages", "security_classification",
                                                "department_id", "archive_value", "store_location", "archived_by",
                                                "date_archived", "remark" },
                                "archive.t_file");
                if (this.list(item, request).getData().size() > 0) {
                        jdbcTemplate.update(insertUpdate[1]);
                } else {
                        jdbcTemplate.update(insertUpdate[0]);
                }
        }
}
