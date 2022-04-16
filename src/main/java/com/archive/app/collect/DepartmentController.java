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
public class DepartmentController {
    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    class Department {
        String departmentId, departmentName;

        public Department(String departmentId, String departmentName) {
            this.departmentId = departmentId;
            this.departmentName = departmentName;
        }

        public String getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

    }

    class DepartmentResponse {
        List<Department> data;

        public DepartmentResponse(List<Department> data) {
            this.data = data;
        }

        public List<Department> getData() {
            return data;
        }

        public void setData(List<Department> data) {
            this.data = data;
        }

    }
    
    @GetMapping("/api/collect/department")
    public DepartmentResponse getDepartments() {
        return new DepartmentResponse(jdbcTemplate
                .queryForList("select department_id, department_name from t_department order by department_id asc")
                .stream()
                .map(v -> new Department(v.get("department_id").toString(), v.get("department_name").toString()))
                .collect(Collectors.toList()));
    }
}
