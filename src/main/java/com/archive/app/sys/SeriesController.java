package com.archive.app.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.archive.app.entities.Series;
import com.archive.share.DataResponse;
import com.archive.share.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SeriesController {
        private static final Logger log = LoggerFactory.getLogger(SeriesController.class);
        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/sys/series/list")
        public DataResponse<Series> list(@RequestParam String departmentId) {
                log.info("serving " + departmentId);
                String sql = "select s.series_no, s.series_name, s.archive_no, s.file_type, s.department_id, s.sort_no, "
                                + "a.archive_name, d.department_name from t_archive_group a, t_department d, t_series s where s.archive_no = a.archive_no and "
                                +
                                "s.department_id =d.department_id";
                if (departmentId != null) {
                        sql += " and s.department_id='" + departmentId + "'";
                }
                sql += " order by s.archive_no, s.sort_no asc";
                List<Map<String, Object>> items = jdbcTemplate.queryForList(sql);
                // String no, String name, String archive, String archiveName, String fileType,
                // String departmentId,
                // String departmentName, int sortNo
                List<Series> list = items.stream()
                                .map(c -> new Series(c.get("series_no").toString(), c.get("series_name").toString(),
                                                c.get("archive_no").toString(), c.get("archive_name").toString(),
                                                c.get("file_type").toString(),
                                                c.get("department_id").toString(), c.get("department_name").toString(),
                                                Integer.parseInt(c.get("sort_no").toString())))
                                .collect(Collectors.toList());
                return new DataResponse<Series>(list);
        }

        @PostMapping("/api/sys/series/save")
        public void saveOrUpate(Series item, HttpServletRequest request) {
                String sql = "insert into archive.t_series (series_no, series_name, archive_no, department_id,file_type, sort_no) values ('"
                                + item.getNo()
                                + "','" + item.getName() + "','" + item.getArchive() + "','" + item.getDepartmentId()
                                + "','" + item.getFileType() + "','"
                                + item.getSortNo() + "')";
                if (DataUtils.getSingleItem(jdbcTemplate,
                                "select * from archive.t_series where series_no='" + item.getArchive() + "'") != null) {
                        sql = "update archive.t_series set series_name='" + item.getName() + "', archive_no='"
                                        + item.getArchive()
                                        + "', department_id='"
                                        + item.getDepartmentId() + "', file_type='" + item.getFileType()
                                        + "', sort_no='"
                                        + item.getSortNo()
                                        + "' where series_no='" + item.getNo() + "'";
                }
                jdbcTemplate.update(sql);
        }

        @PostMapping("/api/sys/series/view")
        public DataResponse<Series> view(@RequestParam String no) {
                log.info("serving " + no);
                if (no == null) {
                        return new DataResponse<Series>(new ArrayList<>());
                }
                String sql = "select s.series_no, s.series_name, s.archive_no, s.file_type, s.department_id, s.sort_no, "
                                + "a.archive_name, d.department_name from t_archive_group a, t_department d, t_series s where s.archive_no = a.archive_no and "
                                +
                                "s.department_id =d.department_id and s.series_no='" + no + "'";

                List<Map<String, Object>> items = jdbcTemplate.queryForList(sql);
                List<Series> list = items.stream()
                                .map(c -> new Series(c.get("series_no").toString(), c.get("series_name").toString(),
                                                c.get("archive_no").toString(), c.get("archive_name").toString(),
                                                c.get("file_type").toString(),
                                                c.get("department_id").toString(), c.get("department_name").toString(),
                                                Integer.parseInt(c.get("sort_no").toString())))
                                .collect(Collectors.toList());
                return new DataResponse<Series>(list);
        }
}
