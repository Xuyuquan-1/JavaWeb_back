package com.sys.util;


import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils{

    public static final String URL = "jdbc:mysql://localhost:3306/stusys";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    //1.加载驱动程序
    static {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

        //2. 获得数据库连接
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //3. 查询语句
    public static ResultSet query(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }
            rs = pst.executeQuery();
            return rs;

        } catch (Exception e) {
            throw new RuntimeException("Error from executing query",e);
        }
    }

    //4. 增，删，改语句
    public static int update(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = getConnection();
            pst = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }
            return pst.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error from execting update", e);
        }
    }

    //1. 获取resultset元数据（属性数量，属性名）
    //2. 进入外层（整个对象）的循环，创建对象（每个result行填充一个对象）
    //2. 获取每行每一个数据的属性名和值（这里属性名是表中定义的），属性名全部转换成小写，此时数据表中的属性名已经与bean中的相同
    //3. 通过相同的属性名和反射获取bean中对应属性的元数据（数据类型，限制符）
    //4. 将从数据表中获取的属性值转换成bean中属性的数据类型
    //5. 设置实现创建好的目标对象的对应属性值
    //6. 插入list，进入下次循环
    public static <T> List<T> convertResultSetToList(ResultSet resultSet, Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException {
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            T object = clazz.newInstance();

            for (int i = 1; i <= columnCount; i++) {
                //获取每行每一个数据的属性名和值（这里属性名是表中定义的）
                String columnName = metaData.getColumnName(i).toLowerCase();//全部转换成小写
                Object columnValue = resultSet.getObject(i);//这里得到得到Object类型与我们bean中定义的不一定相同，比如int->Integer, varchar char->String, Date->java.sql.Date

//                if (i == 1) {
//                    System.out.println("columnName:" + columnName);
//                }

                // 通过反射设置对象的属性
                try {
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true); // 允许访问私有字段

                    // 转换字段值到目标类型
                    Object convertedValue = convertValue(columnValue, field.getType());
//                    System.out.println("columnValue:" + columnValue);
//                    System.out.println("convertValue:" + convertedValue);

                    // 设置字段值
                    field.set(object, convertedValue);

                } catch (NoSuchFieldException e) {
                    // 如果对象中没有与数据库列名匹配的字段（转换为驼峰命名后），则记录日志并忽略该列
                    System.err.println("No such field: " + columnName + " in class " + clazz.getName());
                } catch (ClassCastException | IllegalArgumentException e) {
                    // 类型转换失败或参数非法时，记录日志并忽略该字段
                    System.err.println("Failed to set field value: " + columnName + " in class " + clazz.getName() + ". Error: " + e.getMessage());
                }
            }

            list.add(object);
        }

        return list;
    }

    /**
     * 将下划线分隔的字符串转换为驼峰命名法。
     *
     * @param s 下划线分隔的字符串
     * @return 驼峰命名法的字符串
     */
    private static String convertToCamelCase(String s) {
        StringBuilder result = new StringBuilder();
        boolean nextIsUpper = false;

        for (char c : s.toCharArray()) {
            if (c == '_') {
                nextIsUpper = true;
            } else {
                if (nextIsUpper) {
                    result.append(Character.toUpperCase(c));
                    nextIsUpper = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }

        return result.toString();
    }


    private static Object convertValue(Object value, Class<?> targetType) {
        if (value == null) {
            return null; // 空值直接返回
        }

        if (targetType.isAssignableFrom(value.getClass())) {
            return value; // 如果类型已经匹配，直接返回
        }

        if (targetType == String.class) {
            return value.toString(); // 转换为String
        } else if (targetType == Integer.class || targetType == int.class) {
            try {
                return Integer.parseInt(value.toString());
            } catch (NumberFormatException e) {
                return null; // 转换失败返回null
            }
        } else if (targetType == Long.class || targetType == long.class) {
            try {
                return Long.parseLong(value.toString());
            } catch (NumberFormatException e) {
                return null; // 转换失败返回null
            }
        }
        else if (targetType == Date.class) {
            // 这里需要额外的逻辑来将String或其他类型转换为Date
            // 例如，使用SimpleDateFormat来解析日期字符串
            // 为了简化，这里不实现Date的转换
            return null; // 转换失败返回null
        }

        // 对于其他类型，这里简单地返回null（实际项目中可能需要添加更多的类型转换逻辑）
        return null;
    }

    public static String SqlDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    // 关闭连接（注意：这里只是归还连接池，并非真正关闭连接）
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 关闭资源（连接、语句、结果集）
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn);
    }

    // 关闭资源（连接、语句、结果集）
    public static void close( ResultSet rs) throws SQLException {
        Statement stmt=rs.getStatement();
        Connection conn=stmt.getConnection();

        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn);
    }

}
