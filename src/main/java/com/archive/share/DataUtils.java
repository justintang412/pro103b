package com.archive.share;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtils {
    public static <T> String[] parseInsertUpdate(T instance, String[] fields, String tableName) {
        String primaryKey = fields[0];
        List<String> fieldList = Arrays.asList(fields);
        String insert = "insert into " + tableName + " (";
        String values = " values (";
        String update = "update " + tableName + " set ";
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

}
