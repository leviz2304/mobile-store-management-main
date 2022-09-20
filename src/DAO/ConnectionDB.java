/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ConnectionDB {

    /*String host = "localhost";
    String user = "root";
    String password = "";
    int port = 3306;
    String database = "chdtdatabase";
    Connection conn = null;
    String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database; */

    private String host;
    private String port;
    private String user;
    private String passwd;
    private String databaseName;

    private Connection conn;
    private Statement stm;
    private ResultSet rs;

    public ConnectionDB() {
        checkDriver();
        this.host = "localhost";
        this.user = "root";
        this.passwd = "root";
        this.port = "8889";
        this.databaseName = "chdtdatabase";
        setConnect();
    }
    public void checkDriver(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Tìm thấy Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Không tìm thấy Driver");
        }
    }
    public void setConnect() {
        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName;
            System.out.println(url);
            conn = DriverManager.getConnection(url,user,passwd);

            stm = conn.createStatement();

            System.out.println("Kết nối database thành công");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Không thể kết nối tới database");
        }
    }

    public void closeConnect() {
        try {
            if (conn != null)
                conn.close();
            if (stm != null)
                stm.close();
            System.out.println("Đóng kết nối thành công");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Không thể đóng kết nối");
        }
    }

    public boolean isConnected(){
        return conn != null || stm != null;
    }

    public boolean sqlUpdate(String qry) {
        if(isConnected()) {
            try {
                stm.executeUpdate(qry);

                System.out.println("Truy vấn thành công.");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Truy vấn thất bại");
                return false;
            }
        }
        return false;
    }

    public ResultSet sqlQuery(String qry) {
        if(isConnected()) {
            try {
                rs = stm.executeQuery(qry);

                return rs;
            } catch (SQLException e) {
                System.out.println("Không thể truy xuất dữ liệu");
                return null;
            }
        }
        return null;
    }
}