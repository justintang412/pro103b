package com.archive.app.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
}
