// MySQL 연결 확인용 JDBC 테스트 클래스

package com.ticket.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ticket230320?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "root";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("MySQL 연결 성공!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}