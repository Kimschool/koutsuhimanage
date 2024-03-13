package koutsuhi.data;

import java.sql.*;

public class DbConn {

    public Connection connection() {
        // MySQL 연결 정보
        String url = "jdbc:mysql://localhost:3306/weavus";
        String username = "root";
        String password = "";
        Connection connection = null;

        // JDBC 드라이버 로드
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("해당 DB접속 CLASS가 존재하지 않습니다.");
        } catch (SQLException e) {
            System.err.println("MYSQL 접속에 실패했습니다.");
        }

        return connection;
    }
}
