package com.archive.app.warehouse;

import java.util.UUID;

import com.archive.app.entities.Warehouse;
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
public class WarehouseController {
        private static final Logger log = LoggerFactory.getLogger(WarehouseController.class);
        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/warehouse/list")
        public DataResponse<Warehouse> list(Warehouse item) {
                log.info("serving " + item.getWarehouseId());
                String query = "select warehouse_id, warehouse_name, warehouse_manager, " +
                                "warehouse_contact, warehouse_address, warehouse_desc from t_warehouse ";

                if (item != null && item.getWarehouseName() != null) {
                        query += "where warehouse_name like '%" + item.getWarehouseName() + "%'";
                }
                return new DataResponse<Warehouse>(jdbcTemplate
                                .queryForList(query)
                                .stream()
                                .map(v -> DataUtils.composeEntity(v, Warehouse.class))
                                .toList());
        }

        @PostMapping("/api/warehouse/save")
        public void saveOrUpate(Warehouse item, HttpServletRequest request) {
                boolean isupdate = true;
                if (item.getWarehouseId() == null) {
                        isupdate = false;
                        item.setWarehouseId(UUID.randomUUID().toString());
                }
                String[] insertUpdate = DataUtils.parseInsertUpdate(item,
                                "warehouse_id,warehouse_name,warehouse_manager,warehouse_contact,warehouse_address,warehouse_desc"
                                                .split(","),
                                "archive.t_warehouse");
                if (isupdate) {
                        jdbcTemplate.update(insertUpdate[1]);
                } else {

                        jdbcTemplate.update(insertUpdate[0]);
                }
        }
}
