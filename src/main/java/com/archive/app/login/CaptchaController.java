package com.archive.app.login;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;

@RestController
public class CaptchaController {
    private static final Logger log = LoggerFactory.getLogger(CaptchaController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/ccas/dashboard/captcha/image")
    public String captcha(@RequestParam String t) {
        Captcha captcha = new Captcha.Builder(110, 110)
                .addBackground(new GradiatedBackgroundProducer())
                .addText(new DefaultTextProducer(), new DefaultWordRenderer())
                .addNoise(new CurvedLineNoiseProducer())
                .build();
        String image = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(), "png", bos);
            byte[] byteArray = Base64.getEncoder().encode(bos.toByteArray());
            image = new String(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jdbcTemplate
                .execute("insert into log_captcha (t, captcha) values ('" + t + "','" + captcha.getAnswer() + "')");
        log.info("captcha");
        return image;
    }
}
