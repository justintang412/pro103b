package com.archive.app.sys;
import com.archive.app.entities.Series;
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
public class SeriesController {
        private static final Logger log = LoggerFactory.getLogger(SeriesController.class);
        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/sys/series/list")
        public DataResponse<Series> list(Series item, HttpServletRequest request) {
                log.info("serving " + item.getSeriesNo());
                String query = "select series_no, series_name, archive_no, file_type, department_id, sort_no " +
                                "from t_series ";
                if (item.getSeriesNo() != null) {
                        return new DataResponse<Series>(jdbcTemplate
                                        .queryForList(query + " where series_no='" + item.getSeriesNo() + "'")
                                        .stream()
                                        .map(v -> DataUtils.composeEntity(v, Series.class))
                                        .toList());
                }

                if (item.getArchiveNo() != null) {
                        query += " where archive_no='" + item.getArchiveNo() + "' order by sort_no asc";
                }
                return new DataResponse<Series>(jdbcTemplate
                                .queryForList(query)
                                .stream()
                                .map(v -> DataUtils.composeEntity(v, Series.class))
                                .toList());
        }

        @PostMapping("/api/sys/series/save")
        public void saveOrUpate(Series item, HttpServletRequest request) {
                String[] insertUpdate = DataUtils.parseInsertUpdate(item,
                                new String[] { "series_no", "series_name", "archive_no", "file_type", "department_id", "sort_no" },
                                "archive.t_series");
                if (this.list(item, request).getData().size() > 0) {
                        jdbcTemplate.update(insertUpdate[1]);
                } else {
                        jdbcTemplate.update(insertUpdate[0]);
                }
        }
}
