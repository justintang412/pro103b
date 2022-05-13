package com.archive.app.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.archive.app.entities.Department;
import com.archive.app.entities.Right;
import com.archive.share.DataResponse;
import com.archive.share.DataUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/api/department/list")
    public DataResponse<Department> list(Right item) {
        log.info("serving " + item.getRightId());
        List<Map<String, Object>> roots = jdbcTemplate
                .queryForList("select department_id, department_name from t_department where parent_id='1'");
        
        List<Department> data = roots.stream().map(root -> {
            Department rootDepartment = DataUtils.composeEntity(root, Department.class);
            List<Department> children = jdbcTemplate
                    .queryForList("select department_id, department_name from t_department where parent_id='"
                            + rootDepartment.getDepartmentId() + "' order by department_id asc")
                    .stream()
                    .map(v -> DataUtils.composeEntity(v, Department.class))
                    .toList();
            rootDepartment.setChildren(children);
            return rootDepartment;
        }).toList();

        return new DataResponse<Department>(data);
    }

}
