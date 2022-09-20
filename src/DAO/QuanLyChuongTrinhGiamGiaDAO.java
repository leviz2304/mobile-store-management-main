/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChuongTrinhGiamGia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class QuanLyChuongTrinhGiamGiaDAO {

    ConnectionDB qlggConnection;

    public QuanLyChuongTrinhGiamGiaDAO() {

    }

    public ArrayList<ChuongTrinhGiamGia> readDB() {
        qlggConnection = new ConnectionDB();
        ArrayList<ChuongTrinhGiamGia> dssp = new ArrayList<>();
        try {
            String qry = "SELECT * FROM chuongtrinhgiamgia";
            ResultSet r = qlggConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String magg = r.getString("MAGIAMGIA");
                    String tenct = r.getString("TENCHUONGTRINH");                    
                    LocalDate ngaybd = r.getDate("NGAYBATDAU").toLocalDate();
                    LocalDate ngaykt = r.getDate("NGAYKETTHUC").toLocalDate();
                    dssp.add(new ChuongTrinhGiamGia(magg, tenct, ngaybd, ngaykt));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng khuyến mãi");
        } finally {
            qlggConnection.closeConnect();
        }
        return dssp;
    }
    
    public ArrayList<ChuongTrinhGiamGia> search(String columnName, String value) {
        qlggConnection = new ConnectionDB();
        ArrayList<ChuongTrinhGiamGia> dssp = new ArrayList<>();

        try {
            String qry = "SELECT * FROM chuongtrinhgiamgia WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qlggConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String magg = r.getString("MAGIAMGIA");
                    String tenct = r.getString("TENCHUONGTRINH");                    
                    LocalDate ngaybd = r.getDate("NGAYBATDAU").toLocalDate();
                    LocalDate ngaykt = r.getDate("NGAYKETTHUC").toLocalDate();
                    dssp.add(new ChuongTrinhGiamGia(magg, tenct, ngaybd, ngaykt));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng khuyến mãi");
        } finally {
            qlggConnection.closeConnect();
        }

        return dssp;
    }
    
    public Boolean add(ChuongTrinhGiamGia gg) {
        qlggConnection = new ConnectionDB();
        Boolean ok = qlggConnection.sqlUpdate("INSERT INTO `chuongtrinhgiamgia` (`MAGIAMGIA`, `TENCHUONGTRINH`, `NGAYBATDAU`, `NGAYKETTHUC`) VALUES ('"
                + gg.getMaGiamGia()+ "', '"
                + gg.getTenChuongTrinh()+ "', '"
                + gg.getNgayBatDau()+ "', '"
                + gg.getNgayKetThuc()+ "');");
        qlggConnection.closeConnect();
        return ok;
    }
    
    public Boolean delete(String magg) {
        qlggConnection = new ConnectionDB();
        Boolean ok = qlggConnection.sqlUpdate("DELETE FROM `chuongtrinhgiamgia` WHERE `chuongtrinhgiamgia`.`MAGIAMGIA` = '" + magg + "'");
        qlggConnection.closeConnect();
        return ok;
    }
    
     public Boolean update(String maGiamGia, String tenChuongTrinh, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        qlggConnection = new ConnectionDB();
        Boolean ok = qlggConnection.sqlUpdate("UPDATE `chuongtrinhgiamgia` SET "
                + "TENCHUONGTRINH='" + tenChuongTrinh
                + "', NGAYBATDAU='" + ngayBatDau
                + "', NGAYKETTHUC='" + ngayKetThuc
                + "' where MAGIAMGIA='" + maGiamGia + "'");
        qlggConnection.closeConnect();
        return ok;
    }

    public void close() {
        qlggConnection.closeConnect();
    }

    
}

