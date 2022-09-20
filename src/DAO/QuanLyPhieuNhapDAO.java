/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PhieuNhap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class QuanLyPhieuNhapDAO {
    
    ConnectionDB connection;
    
    public QuanLyPhieuNhapDAO() {
    }

    public ArrayList readDB() {
        connection = new ConnectionDB();
        ArrayList<PhieuNhap> dspn = new ArrayList<>();
        try {
            String qry = "SELECT * FROM phieunhap";
            ResultSet rs = connection.sqlQuery(qry);
            if (rs != null) {

                while (rs.next()) {
                    PhieuNhap pn = new PhieuNhap();

                    pn.setMaPhieuNhap(rs.getString("MAPHIEUNHAP"));
                    pn.setMaNhanVien(rs.getString("MANHANVIEN"));
                    pn.setMaNCC(rs.getString("MANCC"));                   
                    pn.setNgayNhap(rs.getDate("NGAYNHAP").toLocalDate());
                    pn.setTongTien(rs.getInt("TONGTIEN"));
                    dspn.add(pn);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu !!");
        } finally {
            connection.closeConnect();
        }
        return dspn;
    }

    public Boolean add(PhieuNhap pn) {
        connection = new ConnectionDB();
        Boolean ok = connection.sqlUpdate("INSERT INTO `phieunhap`(`MAPHIEUNHAP`,`MANHANVIEN`,`MANCC`,`NGAYNHAP`,TONGTIEN) VALUES ('"
                + pn.getMaPhieuNhap()+ "','"
                + pn.getMaNhanVien()+ "','"
                + pn.getMaNCC()+ "','"
                + pn.getNgayNhap() + "', '"+ pn.getTongTien()+"')");
        connection.closeConnect();
        return ok;
    }

    public Boolean delete(String maPhieuNhap) {
        connection = new ConnectionDB();
        if (!connection.sqlUpdate("DELETE FROM `phieunhap` WHERE `MAPHIEUNHAP`='" + maPhieuNhap + "';")) {
            JOptionPane.showMessageDialog(null, "Vui lòng xóa hết chi tiết của phiếu nhập trước !!!");
            connection.closeConnect();
            return false;
        }
        connection.closeConnect();
        return true;
    }

    public Boolean update(PhieuNhap pn) {
        connection = new ConnectionDB();
        Boolean ok = connection.sqlUpdate("UPDATE `phieunhap` SET "
                + "MANHANVIEN='" + pn.getMaNhanVien()
                + "', MANCC='" + pn.getMaNCC()
                 + "', NGAYNHAP='" + pn.getNgayNhap()
                + "' WHERE MAPHIEUNHAP='" + pn.getMaPhieuNhap()+ "';");
        connection.closeConnect();
        return ok;
    }
    public Boolean updateTongTien(String mapn, int tongTien) {
        connection = new ConnectionDB();
        String query = "UPDATE phieunhap SET TONGTIEN = " + tongTien + " WHERE MAPHIEUNHAP='" + mapn +"'";
        Boolean check = connection.sqlUpdate(query);
        connection.closeConnect();
        return check;
    }

    public Boolean update(String maPhieuNhap, String maNhanVien, String maNCC, LocalDate ngayNhap) {
        PhieuNhap pn = new PhieuNhap();
        pn.setMaPhieuNhap(maPhieuNhap);
        pn.setMaNhanVien(maNhanVien);
        pn.setMaNCC(maNCC);
        pn.setNgayNhap(ngayNhap);       
        return update(pn);
    }

    
}
