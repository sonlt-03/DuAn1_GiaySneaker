package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XJdbc {

    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String dburl = "jdbc:sqlserver://localhost:1433;database=GiaySneaker123;encrypt=true;trustServerCertificate=true;";
    private static final String username = "sa2";
    private static final String password = "123";

    static {
        try {
            Class.forName(driver);  // Bắt buộc load driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Mở kết nối mới mỗi lần gọi
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, username, password);
    }

    /**
     * Thao tác dữ liệu (INSERT, UPDATE, DELETE)
     */
    public static int executeUpdate(String sql, Object... values) {
        try (
            Connection conn = getConnection();
            PreparedStatement stmt = prepareStatement(conn, sql, values);
        ) {
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Truy vấn dữ liệu (SELECT)
     */
    public static ResultSet executeQuery(String sql, Object... values) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = prepareStatement(conn, sql, values);
            return stmt.executeQuery();
            // Lưu ý: ResultSet kèm theo Connection và Statement chưa đóng.
            // Bạn cần xử lý đóng sau khi dùng xong ResultSet.
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Truy vấn một giá trị đơn
     */
    public static <T> T getValue(String sql, Object... values) {
        try (
            Connection conn = getConnection();
            PreparedStatement stmt = prepareStatement(conn, sql, values);
            ResultSet rs = stmt.executeQuery();
        ) {
            if (rs.next()) {
                return (T) rs.getObject(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tạo PreparedStatement và gán tham số
     */
    private static PreparedStatement prepareStatement(Connection conn, String sql, Object... values) throws SQLException {
        PreparedStatement stmt = sql.trim().startsWith("{") ? conn.prepareCall(sql) : conn.prepareStatement(sql);
        for (int i = 0; i < values.length; i++) {
            stmt.setObject(i + 1, values[i]);
        }
        return stmt;
    }

    // Nếu cần có hàm đóng connection, statement, resultSet thì bạn có thể thêm.

}
