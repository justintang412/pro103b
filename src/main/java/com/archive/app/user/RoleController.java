package com.archive.app.user;

import com.archive.app.entities.Role;
import com.archive.share.DataResponse;
import com.archive.share.DataUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/api/role/list")
    public DataResponse<Role> list(Role item) {
        log.info("serving " + item.getRoleName());
        String query = "select role_id, role_name from t_role";
        if(item!=null && item.getRoleName()!=null && item.getRoleName().length()>0){
            query += " where role_name like '%"+item.getRoleName()+"%'";
        }
        return new DataResponse<Role>(jdbcTemplate
                .queryForList(query)
                .stream()
                .map(v -> DataUtils.composeEntity(v, Role.class))
                .toList());
    }

}
