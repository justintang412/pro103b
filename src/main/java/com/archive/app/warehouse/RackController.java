package com.archive.app.warehouse;

import java.util.UUID;

import com.archive.app.entities.Rack;
import com.archive.share.DataResponse;
import com.archive.share.DataUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RackController {
        private static final Logger log = LoggerFactory.getLogger(RackController.class);
        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/rack/list")
        public DataResponse<Rack> list(Rack item) {
                log.info("serving " + item.getRackName());
                String query = "select a.rack_id, a.rack_name, a.warehouse_id, a.rack_position, b.warehouse_name from t_rack a, t_warehouse b where a.warehouse_id=b.warehouse_id ";

                if (item != null && item.getRackName() != null) {
                        query += "and a.rack_name like '%" + item.getRackName() + "%'";
                }
                if (item != null && item.getWarehouseId() != null) {
                        query += "and a.warehouse_id = '" + item.getWarehouseId() + "'";
                }
                return new DataResponse<Rack>(jdbcTemplate
                                .queryForList(query)
                                .stream()
                                .map(v -> DataUtils.composeEntity(v, Rack.class))
                                .toList());
        }

        @PostMapping("/api/rack/save")
        public void saveOrUpate(Rack item, HttpServletRequest request) {
                boolean isupdate = true;
                if (item.getRackId() == null) {
                        isupdate = false;
                        item.setRackId(UUID.randomUUID().toString());
                }
                String[] insertUpdate = DataUtils.parseInsertUpdate(item,
                                "rack_id,rack_name,warehouse_id,rack_position"
                                                .split(","),
                                "archive.t_rack");
                if (isupdate) {
                        jdbcTemplate.update(insertUpdate[1]);
                } else {

                        jdbcTemplate.update(insertUpdate[0]);
                }
        }
}
