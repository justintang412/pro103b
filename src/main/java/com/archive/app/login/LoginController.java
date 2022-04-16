package com.archive.app.login;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/api/dashboard/login")
    public void login(LoginForm loginForm, final HttpServletResponse response) {
        log.info("----------------------");
        if (loginForm.getUsername() == null || loginForm.getT() == null || loginForm.getPassword() == null
                || loginForm.getCaptcha() == null) {
            return;
        }

        List<Map<String, Object>> captchaList = jdbcTemplate.queryForList(
                "select count(*) as cnt from log_captcha where t='" + loginForm.getT() + "' and captcha='"
                        + loginForm.getCaptcha() + "'");
        if (captchaList.size() == 0 || captchaList.get(0).get("cnt").toString().equals("0")) {
            return;
        }

        List<Map<String, Object>> userList = jdbcTemplate.queryForList(
                "select password from t_user where username='" + loginForm.getUsername() + "' and is_active='true'");
        if (userList.size() == 0 || !userList.get(0).get("password").toString().equals(loginForm.getPassword())) {
            return;
        }
        String token = UUID.randomUUID().toString();
        log.info(token);
        long expire = new Date().getTime() + 12 * 3600000;
        jdbcTemplate.execute(
                "update t_user set token='" + token + "', expire= " + expire + " where username='"
                        + loginForm.getUsername() + "'");
        response.setHeader("Authorization", token);
    }
}
