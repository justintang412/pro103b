package com.archive.app.workflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import com.archive.app.entities.Borrow;
import com.archive.app.entities.Todo;
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
public class BorrowController {
        private static final Logger log = LoggerFactory.getLogger(BorrowController.class);
        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/workflow/borrow/list")
        public DataResponse<Borrow> list(Borrow item, HttpServletRequest request) {
                log.info("serving " + item.getBorrowId());
                String authorization = request.getHeader("Authorization");
                if (authorization == null) {
                        return new DataResponse<Borrow>(new ArrayList<>());
                }
                String username = authorization.substring(0, authorization.indexOf(":"));
                String query = "select borrow_id, borrower, create_time, borrow_list, submit_date, approve_date, approve_result, apply_remark, approve_remark from t_borrow where borrower='"
                                + username + "'";
                return new DataResponse<Borrow>(jdbcTemplate
                                .queryForList(query)
                                .stream()
                                .map(v -> DataUtils.composeEntity(v, Borrow.class))
                                .toList());
        }

        @PostMapping("/api/workflow/borrow/save")
        public void saveOrUpate(Borrow item, HttpServletRequest request) {
                String authorization = request.getHeader("Authorization");
                if (authorization == null) {
                        return;
                }
                String username = authorization.substring(0, authorization.indexOf(":"));
                item.setBorrowId(UUID.randomUUID().toString());
                item.setBorrower(username);
                item.setCreateTime((new SimpleDateFormat("yyyy-mm-dd").format(Calendar.getInstance().getTime())));
                item.setSubmitDate((new SimpleDateFormat("yyyy-mm-dd").format(Calendar.getInstance().getTime())));
                String[] insertUpdate = DataUtils.parseInsertUpdate(item,
                                "borrow_id,borrower,create_time,borrow_list,submit_date,approve_date,approve_result,apply_remark,approve_remark"
                                                .split(","),
                                "archive.t_borrow");
                String insert = insertUpdate[0];
                log.info(insert);
                jdbcTemplate.update(insert);
                Todo todo = new Todo();
                todo.setTodoId(UUID.randomUUID().toString());
                todo.setApplier(item.getBorrower());
                todo.setApplyDate(item.getSubmitDate());
                todo.setTodoContent("借阅申请:" + item.getBorrowList());
                todo.setTodoTable("t_borrow:borrow_id");
                todo.setTodoField(item.getBorrowId());
                jdbcTemplate.update(DataUtils.parseInsertUpdate(todo,
                "todo_id,todo_content,applier,applier_department_id,apply_date,todo_table,todo_field,approve_date,approve_result,approve_memo,approver"
                                .split(","),
                "archive.t_todo")[0]);
                
        }
}
