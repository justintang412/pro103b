package com.archive.app.sys;

import java.util.ArrayList;

import com.archive.app.entities.ArchiveGroup;
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
public class ArchiveGroupController {
        private static final Logger log = LoggerFactory.getLogger(ArchiveGroupController.class);
        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/sys/archive-group/list")
        public DataResponse<ArchiveGroup> list(ArchiveGroup item) {
                log.info("serving " + item.getArchiveNo());
                if (item.getDepartmentId() != null) {
                        return new DataResponse<ArchiveGroup>(jdbcTemplate
                                        .queryForList("select a.archive_no, a.archive_name, d.department_id, " +
                                                        "d.department_name from t_archive_group a, t_department d " +
                                                        "where a.department_id =d.department_id"
                                                        +
                                                        " and a.department_id='" + item.getDepartmentId() + "'")
                                        .stream()
                                        .map(v -> DataUtils.composeEntity(v, ArchiveGroup.class))
                                        .toList());
                }
                if (item.getArchiveNo() != null) {
                        return new DataResponse<ArchiveGroup>(jdbcTemplate
                                        .queryForList("select a.archive_no, a.archive_name, d.department_id, " +
                                                        "d.department_name from t_archive_group a, t_department d " +
                                                        "where a.department_id =d.department_id"
                                                        +
                                                        " and a.archive_no='" + item.getArchiveNo() + "'")
                                        .stream()
                                        .map(v -> DataUtils.composeEntity(v, ArchiveGroup.class))
                                        .toList());
                }
                return new DataResponse<ArchiveGroup>(new ArrayList<ArchiveGroup>());
        }

        @PostMapping("/api/sys/archive-group/save")
        public void saveOrUpate(ArchiveGroup item, HttpServletRequest request) {
                String authorization = request.getHeader("Authorization");
                if (authorization == null) {
                        return;
                }
                String username = authorization.substring(0, authorization.indexOf(":"));
                log.info("serving " + username);
                String sql = "insert into archive.t_archive_group (archive_no, archive_name, department_id) values ('"
                                + item.getArchiveNo()
                                + "','" + item.getArchiveName() + "','" + item.getDepartmentId() + "')";
                if (item.getArchiveNo() != null) {
                        ArchiveGroup archiveGroup = new ArchiveGroup();
                        archiveGroup.setArchiveNo(item.getArchiveNo());
                        if (this.list(archiveGroup).getData().size() > 0) {
                                sql = "update archive.t_archive_group set archive_name='" + item.getArchiveName()
                                                + "', department_id='"
                                                + item.getDepartmentId() + "' where archive_no='" + item.getArchiveNo()
                                                + "'";
                        }
                }
                jdbcTemplate.update(sql);
        }
}
