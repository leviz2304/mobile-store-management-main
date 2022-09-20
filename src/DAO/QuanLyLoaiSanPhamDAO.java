/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LoaiSanPham;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class QuanLyLoaiSanPhamDAO {
    
    ConnectionDB qllspConnection;
    
    public QuanLyLoaiSanPhamDAO() {

    }

    public ArrayList<LoaiSanPham> readDB() {
        qllspConnection = new ConnectionDB();
        ArrayList<LoaiSanPham> dslsp = new ArrayList<>();
        try {
            String qry = "SELECT * FROM loaisanpham";
            ResultSet r = qllspConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String maloai = r.getString("MALOAI");
                    String tenloai = r.getString("TENLOAI");

                    dslsp.add(new LoaiSanPham(maloai, tenloai));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng loại sản phẩm");
        } finally {
            qllspConnection.closeConnect();
        }
        return dslsp;
    }

    public ArrayList<LoaiSanPham> search(String columnName, String value) {
        qllspConnection = new ConnectionDB();
        ArrayList<LoaiSanPham> dslsp = new ArrayList<>();

        try {
            String qry = "SELECT * FROM loaisanpham WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qllspConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String maloai = r.getString("MALOAI");
                    String tenloai = r.getString("TENLOAI");

                    dslsp.add(new LoaiSanPham(maloai, tenloai));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng loại sản phẩm");
        } finally {
            qllspConnection.closeConnect();
        }

        return dslsp;
    }

    public Boolean add(LoaiSanPham lsp) {
        qllspConnection = new ConnectionDB();
        Boolean ok = qllspConnection.sqlUpdate("INSERT INTO `loaisanpham` (`MALOAI`, `TENLOAI`) VALUES ('"
                + lsp.getMaLoai()+ "', '" + lsp.getTenLoai()+ "');");
        qllspConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String maLoai) {
        qllspConnection = new ConnectionDB();
        Boolean ok = qllspConnection.sqlUpdate("DELETE FROM `loaisanpham` WHERE `loaisanpham`.`MALOAI` = '" + maLoai + "'");
        qllspConnection.closeConnect();
        return ok;
    }

    public Boolean update(String maLoai, String tenLoai) {
        qllspConnection = new ConnectionDB();
        Boolean ok = qllspConnection.sqlUpdate("UPDATE `loaisanpham` SET "
                + "TENLOAI='" + tenLoai 
                + "' where MALOAI='" + maLoai + "'");
        qllspConnection.closeConnect();
        return ok;
    }

    public void close() {
        qllspConnection.closeConnect();
    }

}
