/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HoaDon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class QuanLyHoaDonDAO {

    ConnectionDB qlhdconnection;

    public QuanLyHoaDonDAO() {

    }

    public ArrayList readDB() {
        qlhdconnection = new ConnectionDB();
        ArrayList<HoaDon> dshd = new ArrayList<>();
        try {
            String qry = "SELECT * FROM hoadon";
            ResultSet rs = qlhdconnection.sqlQuery(qry);
            if (rs != null) {
                while (rs.next()) {
                    HoaDon hd = new HoaDon();
                    hd.setMaHoaDon(rs.getString("MAHOADON"));
                    hd.setMaKhachHang(rs.getString("MAKHACHHANG"));
                    hd.setMaNhanVien(rs.getString("MANHANVIEN"));
                    hd.setMaGiamGia(rs.getString("MAGIAMGIA"));
                    hd.setNgayLap(rs.getDate("NGAYLAP").toLocalDate());
                    hd.setTongTien(rs.getInt("TONGTIEN"));
                    hd.setTongTienGiamGia(rs.getInt("TONGCHIETKHAU"));
                    dshd.add(hd);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không đọc được dữ liệu bảng hóa đơn !!");
        } finally {
            qlhdconnection.closeConnect();
        }
        return dshd;
    }
    
    public Boolean add(HoaDon hd) {
        qlhdconnection = new ConnectionDB();
        String query = "INSERT INTO `hoadon`(`MAHOADON`,`MAKHACHHANG`,`MANHANVIEN`,`MAGIAMGIA`,`NGAYLAP`,`TONGTIEN`,`TONGCHIETKHAU`) VALUES ('"
                + hd.getMaHoaDon()+ "','"
                + hd.getMaKhachHang()+ "','"
                + hd.getMaNhanVien()+ "','"
                + hd.getMaGiamGia()+ "','"
                + hd.getNgayLap()+ "','"
                + hd.getTongTien()+ "','"
                + hd.getTongTienGiamGia()+ "');";
        System.out.println(query);
        Boolean ok = qlhdconnection.sqlUpdate(query);
        qlhdconnection.closeConnect();
        return ok;
    }
    
    public Boolean delete(String mahd) {
        qlhdconnection = new ConnectionDB();
        if (!qlhdconnection.sqlUpdate("DELETE FROM `hoadon` WHERE `MAHOADON`='" + mahd + "';")) {
            JOptionPane.showMessageDialog(null, "Vui lòng xóa hết chi tiết của hóa đơn trước !!!");
            qlhdconnection.closeConnect();
            return false;
        }
        qlhdconnection.closeConnect();
        return true;
    }
    
    public Boolean update(HoaDon hd) {
        qlhdconnection = new ConnectionDB();
        Boolean ok = qlhdconnection.sqlUpdate("UPDATE `hoadon` SET "
                + "MAKH='" + hd.getMaKhachHang()
                + "', MANHANVIEN='" + hd.getMaNhanVien()
                + "', MAGIAMGIA='" + hd.getMaGiamGia()
                + "', NGAYLAP='" + hd.getNgayLap() 
                + "', TONGTIEN='" + hd.getTongTien()
                + "', TONGCHIETKHAU='" + hd.getTongTienGiamGia()
                + "' WHERE MAHOADON='" + hd.getMaHoaDon()+ "';");
        qlhdconnection.closeConnect();
        return ok;
    }
    
    public Boolean updateTongTien(String mahd,int tongtien){
        qlhdconnection = new ConnectionDB();
        Boolean success = qlhdconnection.sqlUpdate("UPDATE `hoadon` SET TONGTIEN='" + tongtien + "' WHERE MAHOADON='" +mahd + "';");
        qlhdconnection.closeConnect();
        return success;
    }

    public Boolean update(String maHoaDon, String maKhachHang, String maNhanVien, String maGiamGia, LocalDate ngayLap, int tongTien, int tongTienGiamGia) {
        HoaDon hd = new HoaDon();
        hd.setMaHoaDon(maHoaDon);
        hd.setMaKhachHang(maKhachHang);
        hd.setMaNhanVien(maNhanVien);
        hd.setMaGiamGia(maGiamGia);
        hd.setNgayLap(ngayLap);
        hd.setTongTien(tongTien);
        hd.setTongTienGiamGia(tongTienGiamGia);
        return update(hd);
    }

   
}
