package com.archive.app.collect;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class VolumnNoController {
    private static final Logger log = LoggerFactory.getLogger(VolumnNoController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    class VolumnNo {
        String volumnNo, volumnName;

        public VolumnNo(String volumnNo, String volumnName) {
            this.volumnNo = volumnNo;
            this.volumnName = volumnName;
        }

        public String getVolumnNo() {
            return volumnNo;
        }

        public void setVolumnNo(String volumnNo) {
            this.volumnNo = volumnNo;
        }

        public String getVolumnName() {
            return volumnName;
        }

        public void setVolumnName(String volumnName) {
            this.volumnName = volumnName;
        }

    }

    class VolumnNoResponse {
        List<VolumnNo> data;

        public VolumnNoResponse(List<VolumnNo> data) {
            this.data = data;
        }

        public List<VolumnNo> getData() {
            return data;
        }

        public void setData(List<VolumnNo> data) {
            this.data = data;
        }

    }
    @GetMapping("/ccas/collect/volumnno")
    public VolumnNoResponse getVolumnNo() {
        log.info("serving getVolumnNo");
        return new VolumnNoResponse(
                jdbcTemplate.queryForList("select volumn_no, volumn_note from t_volumn order by volumn_no asc").stream()
                        .map(v -> new VolumnNo(v.get("volumn_no").toString(),
                                v.get("volumn_note") == null ? "" : v.get("volumn_note").toString()))
                        .collect(Collectors.toList()));
    }
}
