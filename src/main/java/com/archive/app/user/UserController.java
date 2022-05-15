package com.archive.app.user;

import com.archive.app.entities.User;
import com.archive.share.DataResponse;
import com.archive.share.DataUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    class RoleResponse {
        String[] data;

        public RoleResponse(String[] data) {
            this.data = data;
        }

        public String[] getData() {
            return data;
        }

        public void setData(String[] data) {
            this.data = data;
        }

    }

    class UserInfo {
        String username;

        public UserInfo(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    }

    class UserInfoResponse {
        Object data;

        public UserInfoResponse(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

    }

    @GetMapping("/api/dashboard/user/info")
    public UserInfoResponse userInfo(@RequestHeader("Authorization") String token) {
        log.info("/ccas/dashboard/user/info", token);
        UserInfoResponse resp = new UserInfoResponse(new UserInfo(token));
        return resp;
    }

    @GetMapping("/api/dashboard/user/roles")
    public RoleResponse roles(@RequestHeader("Authorization") String token) {
        log.info("/ccas/dashboard/user/info", token);
        return new RoleResponse(new String[] { "super_admin" });
    }

    @GetMapping("/api/user/list")
    public DataResponse<User> list(User item, HttpServletRequest request) {
        log.info("serving " + item.getDepartmentId());
        String query = "select username, is_admin, role_id, is_active, fullname, department_id " +
                "from t_user";
        if (item != null && item.getDepartmentId() != null) {
            query = "select username, is_admin, role_id, is_active, fullname, department_id " +
                    "from t_user where department_id='" + item.getDepartmentId() + "'";
        }
        log.info(query);
        return new DataResponse<User>(jdbcTemplate
                .queryForList(query)
                .stream()
                .map(v -> DataUtils.composeEntity(v, User.class))
                .toList());
    }
    @GetMapping("/api/user/departmentUsers")
    public DataResponse<User> departmentUsers(User item, HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        log.info(auth);
        String query = "select a.username, a.is_admin, a.role_id, a.is_active, a.fullname, a.department_id " +
                "from t_user a where a.department_id=(select department_id from t_user where username='"+auth.split(":")[0]+"')";
       
        log.info(query);
        return new DataResponse<User>(jdbcTemplate
                .queryForList(query)
                .stream()
                .map(v -> DataUtils.composeEntity(v, User.class))
                .toList());
    }
    
}
