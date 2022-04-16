package com.archive.app.sys;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.archive.share.DataResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionaryController {
    private static final Logger log = LoggerFactory.getLogger(DictionaryController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    class Code {
        String code, name;

        public Code(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }


    @GetMapping("/api/sys/code")
    public DataResponse<Code> getCode(@RequestParam String type) {
        log.info("serving " + type);
        List<Map<String, Object>> codes = jdbcTemplate.queryForList(
                "select code, code_name from t_codes where code_type='" + type + "' order by code asc");
        List<Code> codeList = codes.stream().map(c -> new Code(c.get("code").toString(), c.get("code_name").toString()))
                .collect(Collectors.toList());
        return new DataResponse<Code>(codeList);
    }
}
