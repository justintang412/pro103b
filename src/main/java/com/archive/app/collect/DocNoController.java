package com.archive.app.collect;

import com.archive.share.DataResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocNoController {
    private static final Logger log = LoggerFactory.getLogger(DocNoController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    class DocNo {
        String docNo, docNoNote;

        public DocNo(String docNo, String docNoNote) {
            this.docNo = docNo;
            this.docNoNote = docNoNote;
        }

        public String getDocNo() {
            return docNo;
        }

        public void setDocNo(String docNo) {
            this.docNo = docNo;
        }

        public String getDocNoNote() {
            return docNoNote;
        }

        public void setDocNoNote(String docNoNote) {
            this.docNoNote = docNoNote;
        }

    }
    @GetMapping("/api/collect/docno")
    public DataResponse<DocNo> getDocNo() {
        log.info("serving getDocNo");
        return new DataResponse<DocNo>(
                jdbcTemplate.queryForList("select doc_no, doc_no_note from t_doc_no order by doc_no asc").stream()
                        .map(v -> new DocNo(v.get("doc_no").toString(),
                                v.get("doc_no_note") == null ? "" : v.get("doc_no_note").toString()))
                        .toList());
    }
}
