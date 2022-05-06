package com.archive.share;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.archive.app.entities.File;

public class DataUtils {
    public static <T> String[] parseInsertUpdate(T instance, String[] fields, String tableName) {
        String primaryKey = fields[0];
        List<String> fieldList = Arrays.asList(fields);
        String insert = "insert into " + tableName + " (";
        String values = " values (";
        String update = "upate " + tableName + " set ";
        Method[] methods = instance.getClass().getMethods();
        List<Method> methodList = Arrays.asList(methods)
                .stream()
                .filter((t) -> t.getName().startsWith("get")).toList();
        boolean hasValidFields = false;
        String primaryKeyValue = null;
        for (Method m : methodList) {
            String name = m.getName().substring("get".length());
            StringBuffer fieldBuffer = new StringBuffer();
            for (char c : name.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    if (fieldBuffer.length() == 0) {
                        fieldBuffer.append(Character.toLowerCase(c));
                    } else {
                        fieldBuffer.append("_" + Character.toLowerCase(c));
                    }
                } else {
                    fieldBuffer.append(c);
                }
            }
            String fieldName = fieldBuffer.toString();

            if (fieldList.contains(fieldName)) {

                Object fieldValue = null;
                try {
                    fieldValue = m.invoke(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (fieldValue != null) {
                    if (!hasValidFields) {
                        hasValidFields = true;
                    }
                    insert += fieldName + ", ";
                    values += "'" + fieldValue.toString() + "', ";
                    if (fieldName.equals(primaryKey)) {
                        primaryKeyValue = fieldValue.toString();
                    } else {
                        update += fieldName + "='" + fieldValue.toString() + "', ";
                    }
                }
            }
        }
        if (hasValidFields) {
            insert = insert.substring(0, insert.length() - ", ".length()) + ")"
                    + values.substring(0, values.length() - ", ".length()) + ")";
            update = update.substring(0, update.length() - ", ".length()) +
                    " where " + primaryKey + "='" + primaryKeyValue + "'";
            return new String[] { insert, update };
        }
        return null;
    }

    public static <T> T composeEntity(Map<String, Object> item, Class<T> clz) {
        Method[] methods = clz.getMethods();
        List<Method> fields = Arrays.asList(methods).stream().filter((t) -> t.getName().startsWith("set")).toList();
        try {
            T t = clz.getDeclaredConstructor().newInstance();
            for (Method m : fields) {
                String name = m.getName().substring("set".length());
                StringBuffer fieldBuffer = new StringBuffer();
                for (char c : name.toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        if (fieldBuffer.length() == 0) {
                            fieldBuffer.append(Character.toLowerCase(c));
                        } else {
                            fieldBuffer.append("_" + Character.toLowerCase(c));
                        }
                    } else {
                        fieldBuffer.append(c);
                    }
                }
                String fieldName = fieldBuffer.toString();
                Object value = item.get(fieldName);
                m.invoke(t, value == null ? null : value.toString());
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main1(String args[]) {
        Map<String, Object> testData = new HashMap<String, Object>();
        testData.put("file_no", "121212421212");
        File file = DataUtils.composeEntity(testData, File.class);
        System.out.println(file.getFileNo());
    }

    public static void main(String args[]) {
        String[] fields = new String[] { "file_no", "department_file_no", "global_file_no", "file_title",
                "alternative_title", "series_no", "file_year", "refer_global_file_no", "valid_years", "pages",
                "security_classification", "department_id", "archive_value", "store_location", "archived_by",
                "date_archived", "remark" };
        File file = new File();
        file.setFileNo("fileno-988888");
        file.setArchiveValue("19201");
        file.setAlternativeTitle("alternative tile");
        String[] sql = DataUtils.parseInsertUpdate(file, fields, "archive.t_file");
        System.out.println(sql[0]);
        System.out.println(sql[1]);
    }
}
