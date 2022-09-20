/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietGiamGia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Administrator
 */
public class QuanLyChiTietGiamGiaDAO {
    ConnectionDB qlctggConnection;
    public QuanLyChiTietGiamGiaDAO(){
        
    }
        public ArrayList<ChiTietGiamGia> readDB() {
        qlctggConnection = new ConnectionDB();
        ArrayList<ChiTietGiamGia> dssp = new ArrayList<>();
        try {
            String qry = "SELECT * FROM chitietgiamgia";
            ResultSet r = qlctggConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String magg = r.getString("MAGIAMGIA");
                    String masp = r.getString("MASANPHAM");
                    int chietkhau = r.getInt("CHIETKHAU");
                    dssp.add(new ChiTietGiamGia(magg, masp, chietkhau));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng khuyến mãi");
        } finally {
            qlctggConnection.closeConnect();
        }
        return dssp;
    }

    public ArrayList<ChiTietGiamGia> search(String columnName, String value) {
        qlctggConnection = new ConnectionDB();
        ArrayList<ChiTietGiamGia> dssp = new ArrayList<>();

        try {
            String qry = "SELECT * FROM chitietgiamgia WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qlctggConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String magg = r.getString("MAGIAMGIA");
                    String masp = r.getString("MASANPHAM");
                    int chietkhau = r.getInt("CHIETKHAU");
                    dssp.add(new ChiTietGiamGia(magg, masp, chietkhau));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng khuyến mãi");
        } finally {
            qlctggConnection.closeConnect();
        }

        return dssp;
    }

    public Boolean add(ChiTietGiamGia gg) {
        qlctggConnection = new ConnectionDB();
        Boolean ok = qlctggConnection.sqlUpdate("INSERT INTO `chitietgiamgia` (`MAGIAMGIA`, `MASANPHAM`, `CHIETKHAU`) VALUES ('"
                + gg.getMaGiamGia()+ "', '"
                + gg.getMaSanPham()+ "', '"                
                + gg.getChietKhau()+ "')");
        qlctggConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String magg, String masp) {
        qlctggConnection = new ConnectionDB();
        Boolean ok = qlctggConnection.sqlUpdate("DELETE FROM `chitietgiamgia` WHERE `MAGIAMGIA` = '" + magg + "' AND MASANPHAM='" + masp + "';");
        qlctggConnection.closeConnect();
        return ok;
    }

    public Boolean update(String magg, String masp, int chietkhau) {
        qlctggConnection = new ConnectionDB();
        String query ="UPDATE chitietgiamgia SET CHIETKHAU='"+chietkhau+"' WHERE MAGIAMGIA='"+magg+"' AND MASANPHAM='"+masp+"'";
        System.out.println(query);
        Boolean ok = qlctggConnection.sqlUpdate(query);
        qlctggConnection.closeConnect();
        return ok;
    }

    public void close() {
        qlctggConnection.closeConnect();
    }

    
}
