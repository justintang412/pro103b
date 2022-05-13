package com.archive.app.user;

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
public class RightController {
    private static final Logger log = LoggerFactory.getLogger(RightController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/api/right/list")
    public DataResponse<Right> list(Right item) {
        log.info("serving " + item.getRightId());
        String query = "select right_id, right_name, right_desc, right_tag from t_rights";
        if(item!=null && item.getRightName()!=null && item.getRightName().length()>0){
            query += " where right_name like '%"+item.getRightName()+"%'";
        }
        return new DataResponse<Right>(jdbcTemplate
                .queryForList(query)
                .stream()
                .map(v -> DataUtils.composeEntity(v, Right.class))
                .toList());
    }

}
