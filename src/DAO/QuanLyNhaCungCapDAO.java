/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhaCungCap;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class QuanLyNhaCungCapDAO {

    ConnectionDB qlnccConnection;

    public ArrayList<NhaCungCap> readDB() {
        ArrayList<NhaCungCap> dsncc = new ArrayList<>();
        qlnccConnection = new ConnectionDB();
        try {
            String qry = "SELECT * FROM nhacungcap";
            ResultSet r = qlnccConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String mancc = r.getString("MANCC");
                    String tenncc = r.getString("TENNCC");
                    String email = r.getString("EMAIL");
                    String diachi = r.getString("DIACHI");
                    String sdt = r.getString("SDT");
                    dsncc.add(new NhaCungCap(mancc, tenncc, email, diachi, sdt));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thấy data cần tìm trong ResultSet");
        } finally {
            qlnccConnection.closeConnect();
        }
        return dsncc;
    }

    public ArrayList<NhaCungCap> search(String columnName, String value) {
        qlnccConnection = new ConnectionDB();
        ArrayList<NhaCungCap> dsncc = new ArrayList<>();
        try {
            String qry = "SELECT * FROM sanpham WHERE" + columnName + "LIKE '%" + value + "%'";
            ResultSet r = qlnccConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String mancc = r.getString("MANCC");
                    String tenncc = r.getString("TENNCC");
                    String sdt = r.getString("EMAIL");
                    String email = r.getString("DIACHI");
                    String diachi = r.getString("SDT");
                    dsncc.add(new NhaCungCap(mancc, tenncc, email, diachi, sdt));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng nhà cung cấp");
        } finally {
            qlnccConnection.closeConnect();
        }
        return dsncc;
    }

    public Boolean add(NhaCungCap ncc) {
        qlnccConnection = new ConnectionDB();
        Boolean ok = qlnccConnection.sqlUpdate("INSERT INTO `nhacungcap` (`MANCC`, `TENNCC`, `EMAIL`,`DIACHI`,`SDT`) VALUES ('"
                + ncc.getMaNCC() + "', '"
                + ncc.getTenNCC() + "', '"
                + ncc.getEmail() + "','"
                + ncc.getDiaChi() + "','"
                + ncc.getSDT() + "');");
        qlnccConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String mancc) {
        qlnccConnection = new ConnectionDB();
        Boolean ok = qlnccConnection.sqlUpdate("DELETE FROM `nhacungcap` WHERE `nhacungcap`.`MANCC` = '" + mancc + "'");
        qlnccConnection.closeConnect();
        return ok;
    }

    public Boolean update(String maNCC, String tenNCC, String Email, String diaChi, String SDT) {
        qlnccConnection = new ConnectionDB();
        Boolean ok = qlnccConnection.sqlUpdate("UPDATE `nhacungcap` SET "
                + "MANCC='" + maNCC
                + "',TENNCC='" + tenNCC 
                + "',EMAIL='" + Email 
                + "',DIACHI='" + diaChi 
                + "',SDT='" + SDT 
                + "' where MANCC='" + maNCC + "'");
        qlnccConnection.closeConnect();
        return ok;
    }

    public void close() {
        qlnccConnection.closeConnect();
    }
}
