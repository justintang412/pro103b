package com.archive.app.sys;

import java.util.ArrayList;

import com.archive.app.entities.Code;
import com.archive.share.DataResponse;
import com.archive.share.DataUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {
    private static final Logger log = LoggerFactory.getLogger(CodeController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/api/sys/code")
    public DataResponse<Code> list(Code code) {
        log.info("serving " + code.getCodeId());
        if (code.getCodeType() != null) {
            return new DataResponse<Code>(jdbcTemplate.queryForList(
                    "select code_id, code_name, code_type from t_codes where code_type='" + code.getCodeType()
                            + "' order by code asc")
                    .stream()
                    .map(v -> DataUtils.composeEntity(v, Code.class)).toList());
        }
        if (code.getCodeId() != null) {
            return new DataResponse<Code>(jdbcTemplate.queryForList(
                    "select code_id, code_name, code_type from t_codes where code_id='" + code.getCodeId()
                            + "'")
                    .stream()
                    .map(v -> DataUtils.composeEntity(v, Code.class)).toList());
        }
        
        return new DataResponse<Code>(new ArrayList<Code>());
    }
}
