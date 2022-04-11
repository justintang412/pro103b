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

    @GetMapping("/ccas/dashboard/user/info")
    public String userInfo(@RequestHeader("Authorization") String token) {
        log.info("/ccas/dashboard/user/info", token);
        return "";
    }
}
