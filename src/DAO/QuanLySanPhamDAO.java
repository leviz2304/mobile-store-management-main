/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SanPham;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class QuanLySanPhamDAO {

    ConnectionDB qlspConnection;

    public QuanLySanPhamDAO() {

    }

    public ArrayList<SanPham> readDB() {
        qlspConnection = new ConnectionDB();
        ArrayList<SanPham> dssp = new ArrayList<>();
        try {
            String qry = "SELECT * FROM sanpham";
            ResultSet r = qlspConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String masp = r.getString("MASANPHAM");
                    String tensp = r.getString("TENSANPHAM");
                    String maloai = r.getString("MALOAI");
                    String mansx = r.getString("MANSX");                    
                    int soluong = r.getInt("SOLUONG");
                    int giatien = r.getInt("GIATIEN");
                    dssp.add(new SanPham(masp, tensp, maloai, mansx, soluong, giatien));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng sản phẩm");
        } finally {
            qlspConnection.closeConnect();
        }
        return dssp;
    }

    public ArrayList<SanPham> search(String columnName, String value) {
        qlspConnection = new ConnectionDB();
        ArrayList<SanPham> dssp = new ArrayList<>();

        try {
            String qry = "SELECT * FROM sanpham WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qlspConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String masp = r.getString("MASANPHAM");
                    String tensp = r.getString("TENSANPHAM");
                    String maloai = r.getString("MALOAI");
                    String mansx = r.getString("MANSX");                    
                    int soluong = r.getInt("SOLUONG");
                    int giatien = r.getInt("GIATIEN");
                    dssp.add(new SanPham(mansx, tensp, maloai, mansx, soluong, giatien));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng sản phẩm");
        } finally {
            qlspConnection.closeConnect();
        }

        return dssp;
    }

    public Boolean add(SanPham sp) {
        qlspConnection = new ConnectionDB();
        String query = "INSERT INTO `sanpham` (`MASANPHAM`, `TENSANPHAM`, `MALOAI`, `MANSX`, `SOLUONG`, `GIATIEN`) VALUES ('"
                + sp.getMaSP()+ "', '"
                + sp.getTenSP()+ "', '"
                + sp.getMaLoaiSP()+ "', '"
                + sp.getMaNSX()+ "', '"
                + sp.getSoLuong()+ "', '"
                + sp.getGiaTien()+ "')";
        System.out.println(query);
        Boolean ok = qlspConnection.sqlUpdate(query);
        qlspConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String masp) {
        qlspConnection = new ConnectionDB();
        String query="DELETE FROM sanpham WHERE MASANPHAM='" + masp + "'";
        System.out.println(query);
        Boolean ok = qlspConnection.sqlUpdate(query);
        qlspConnection.closeConnect();
        return ok;
    }

    public Boolean update(String idSanPham, String tenSP, String idLoaiSP, String idHang, int soLuong, int giaTien) {
        qlspConnection = new ConnectionDB();
        Boolean ok = qlspConnection.sqlUpdate("UPDATE `sanpham` SET "
                + "TENSANPHAM='" + tenSP
                + "',MALOAI='" + idLoaiSP
                + "',MANSX='" + idHang
                + "',SOLUONG='" + soLuong
                + "',GIATIEN='" + giaTien
                + "' where MASANPHAM='" + idSanPham + "'");
        qlspConnection.closeConnect();
        return ok;
    }

    public Boolean updateSoLuong(String IdSP, int SoLuong) {
        qlspConnection = new ConnectionDB();
        Boolean ok = qlspConnection.sqlUpdate("UPDATE `sanpham` SET "
                + "SOLUONG='" + SoLuong
                + "' where MASANPHAM='" + IdSP + "'");
        qlspConnection.closeConnect();
        return ok;
    }

    public void close() {
        qlspConnection.closeConnect();
    }

}
