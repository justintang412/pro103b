package com.archive.app.workflow;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TodoController {
        private static final Logger log = LoggerFactory.getLogger(TodoController.class);
        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/workflow/todo/list")
        public DataResponse<Todo> list(@RequestParam("type") String type) {
                log.info("serving " + type);
                String todoTable = "t_borrow:borrow_id";
                switch (type) {
                        case "0":

                                break;
                        default:
                                todoTable = "t_borrow:borrow_id";
                }
                String query = "select todo_id, todo_content, applier, applier_department_id, apply_date, todo_table, todo_field, approve_date, approve_result, approve_memo, approver from t_todo where approve_date is null and todo_table='"
                                + todoTable + "'";
                log.info(query);
                return new DataResponse<Todo>(jdbcTemplate
                                .queryForList(query)
                                .stream()
                                .map(v -> DataUtils.composeEntity(v, Todo.class))
                                .toList());
        }

        @PostMapping("/api/workflow/todo/approve")
        public void approve(Todo item, HttpServletRequest request) {
                String authorization = request.getHeader("Authorization");
                if (authorization == null) {
                        return;
                }
                String username = authorization.substring(0, authorization.indexOf(":"));
                item.setApproveDate(new SimpleDateFormat("yyyy-mm-dd").format(Calendar.getInstance().getTime()));
                item.setApprover(username);
                jdbcTemplate.update(DataUtils.parseInsertUpdate(item,
                                "todo_id,todo_content,applier,applier_department_id,apply_date,todo_table,todo_field,approve_date,approve_result,approve_memo,approver"
                                                .split(","),
                                "archive.t_todo")[1]);
                Todo todo = jdbcTemplate.queryForList("select * from t_todo where todo_id='" + item.getTodoId() + "'")
                                .stream()
                                .map(v -> DataUtils.composeEntity(v, Todo.class))
                                .toList().get(0);
                Borrow borrow = new Borrow();
                borrow.setBorrowId(todo.getTodoField());
                borrow.setApproveDate(todo.getApproveDate());
                borrow.setApproveRemark(todo.getApproveMemo());
                borrow.setApproveResult(todo.getApproveResult());
                String[] insertUpdate = DataUtils.parseInsertUpdate(borrow,
                                "borrow_id,borrower,create_time,borrow_list,submit_date,approve_date,approve_result,apply_remark,approve_remark"
                                                .split(","),
                                "archive.t_borrow");
                String insert = insertUpdate[1];
                log.info(insert);
                jdbcTemplate.update(insert);
        }
}
