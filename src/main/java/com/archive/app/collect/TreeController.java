package com.archive.app.collect;

import java.util.ArrayList;
import java.util.List;

import com.archive.app.entities.TreeNode;
import com.archive.share.DataResponse;
import com.archive.share.DataUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TreeController {
        private static final Logger log = LoggerFactory.getLogger(TreeController.class);
        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/collect/tree/list")
        public DataResponse<TreeNode> list() {
                log.info("serving /api/collect/tree/list");
                List<TreeNode> dataList = new ArrayList<>();
                List<TreeNode> archiveList = jdbcTemplate
                                .queryForList("select a.archive_no as id, null as pId, a.archive_name as name " +
                                                "from t_archive_group a order by a.archive_no asc")

                                .stream()
                                .map(v -> DataUtils.composeEntity(v, TreeNode.class))
                                .toList();
                dataList.addAll(archiveList);
                archiveList.forEach(item -> {
                        List<TreeNode> seriesList = jdbcTemplate
                                        .queryForList("select series_no as id, archive_no as pId, series_name as name "
                                                        +
                                                        "from t_series where archive_no= '" + item.getId()
                                                        + "' order by sort_no asc")
                                        .stream()
                                        .map(v -> DataUtils.composeEntity(v, TreeNode.class))
                                        .toList();
                        item.setChildren(seriesList);
                });
                return new DataResponse<TreeNode>(dataList);
        }
}
