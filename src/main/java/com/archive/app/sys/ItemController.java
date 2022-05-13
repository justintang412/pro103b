package com.archive.app.sys;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.archive.app.entities.Item;
import com.archive.share.DataResponse;
import com.archive.share.DataUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ItemController {
        private static final Logger log = LoggerFactory.getLogger(ItemController.class);

        @Value("${upload.folder}")
        private String uploadPath;

        @Autowired
        JdbcTemplate jdbcTemplate;

        @GetMapping("/api/sys/item/list")
        public DataResponse<Item> list(Item item, HttpServletRequest request) {
                
                String query = "select item_id, file_no, item_no, item_date, item_size, item_full_name, " +
                                "item_format, item_version, e_item_no, department_e_item_no, global_e_item_no, " +
                                "key_words, pages, pysical_file_id, receiver, receive_date, remark " +
                                "from t_item where item_no is null";
                if (item.getItemId() != null) {
                        return new DataResponse<Item>(jdbcTemplate
                                        .queryForList(query + " and item_id='" + item.getItemId() + "'")
                                        .stream()
                                        .map(v -> DataUtils.composeEntity(v, Item.class))
                                        .toList());
                }

                if (item.getReceiveDate()!=null) {
                        query += " and receive_date between " + item.getReceiveDate();
                }
                if (item.getReceiver()!=null) {
                        query += " and receiver = '" + item.getReceiver() + "'";
                }
                if (item.getItemFullName()!=null) {
                        query += " and item_full_name like '%" + item.getItemFullName() + "%'";
                }
                query += " order by receive_date desc";
                log.info(query);
                return new DataResponse<Item>(jdbcTemplate
                                .queryForList(query)
                                .stream()
                                .map(v -> DataUtils.composeEntity(v, Item.class))
                                .toList());
        }

        @PostMapping("/api/sys/item/save")
        public void saveOrUpate(@RequestParam("itemFullName") String itemFullName,
                        @RequestParam("receiveDate") String receiveDate,
                        @RequestParam("receiver") String receiver,
                        @RequestParam("file") MultipartFile file, HttpServletRequest request) {
                Item item = new Item();
                item.setItemId(UUID.randomUUID().toString());
                item.setItemFullName(itemFullName);
                item.setReceiveDate(receiveDate);
                item.setReceiver(receiver);
                String itemFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
                String pysicalFileId = UUID.randomUUID().toString()
                                + "." +itemFormat;
                item.setItemFormat(itemFormat);
                String filename = uploadPath + pysicalFileId;
                try (
                                FileOutputStream fos = new FileOutputStream(filename);
                                InputStream fis = file.getInputStream();) {
                        byte[] buf = new byte[1024000];
                        long totalSize = 0;
                        int c = fis.read(buf);
                        while (c != -1) {
                                totalSize += c;
                                fos.write(buf);
                                c = fis.read(buf);
                        }
                        item.setItemSize("" + totalSize);
                } catch (Exception e) {
                        log.error(e.getMessage());
                }
                item.setPysicalFileId(pysicalFileId);

                String[] insertUpdate = DataUtils.parseInsertUpdate(item,
                                new String[] { "item_id", "file_no", "item_no", "item_date", "item_size",
                                                "item_full_name", "item_format", "item_version", "e_item_no",
                                                "department_e_item_no", "global_e_item_no", "key_words", "pages",
                                                "pysical_file_id", "receiver", "receive_date", "remark" },
                                "archive.t_item");
                jdbcTemplate.update(insertUpdate[0]);

        }

        @PostMapping("/api/sys/item/delete")
        public void delete(Item item, HttpServletRequest request) {
                log.info(item.getItemId());
                if (item.getItemId() == null) {
                        return;
                }
                String ids = Arrays.asList(item.getItemId().split(",")).stream().reduce("", (a, b) -> {
                        if (a.length() > 0) {
                                return a + ",'" + b + "'";
                        } else {
                                return "'" + b + "'";
                        }
                });
                log.info(ids);
                jdbcTemplate.update("delete from archive.t_item where item_id in (" + ids + ")");
        }

        @PostMapping("/api/sys/item/download")
        public ResponseEntity<Resource> download(@RequestParam("itemId") String itemId, HttpServletRequest request) {
                log.info("serving download "+ itemId);
                if (itemId == null) {
                        return null;
                }
                Item item = new Item();
                item.setItemId(itemId);
                List<Item> fileItems = this.list(item, request).getData();
                if (fileItems == null || fileItems.size() == 0) {
                        return null;
                }
                Item downloadItem = fileItems.get(0);
                String filename = uploadPath + downloadItem.getPysicalFileId();
                try {
                        InputStreamResource resource = new InputStreamResource(new FileInputStream(filename));
                        return ResponseEntity.ok()
                                        .contentLength(Long.parseLong(downloadItem.getItemSize()))
                                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                                        .body(resource);

                } catch (Exception e) {
                        log.error(e.getMessage(), e);
                }
                return null;
        }

}
