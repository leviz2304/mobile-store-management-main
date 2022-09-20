/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhachHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Administrator
 */
public class QuanLyKhachHangDAO {
    ConnectionDB qlkhConnection;

    public QuanLyKhachHangDAO() {

    }
    
    public ArrayList<KhachHang> readDB() {
        qlkhConnection = new ConnectionDB();
        ArrayList<KhachHang> dskh = new ArrayList<>();
        try {
            String qry = "SELECT * FROM khachhang";
            ResultSet r = qlkhConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String makh = r.getString("MAKH");
                    String hokh = r.getString("HOKH");
                    String tenkh = r.getString("TENKH");
                    String gioitinh = r.getString("GIOITINH");
                    String email = r.getString("EMAIL");
                    String diachi = r.getString("DIACHI");
                    String sdt = r.getString("SDT");
                    int tongchitieu = r.getInt("TONGCHITIEU");
                    dskh.add(new KhachHang(makh, hokh,tenkh, gioitinh, email, diachi, sdt, tongchitieu));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng khách hàng");
        } finally {
            qlkhConnection.closeConnect();
        }
        return dskh;
    }
    
    public ArrayList<KhachHang> search(String columnName, String value) {
        qlkhConnection = new ConnectionDB();
        ArrayList<KhachHang> dskh = new ArrayList<>();

        try {
            String qry = "SELECT * FROM khachhang WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qlkhConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String makh = r.getString("MAKH");
                    String hokh = r.getString("HOKH");
                    String tenkh = r.getString("TENKH");
                    String gioitinh = r.getString("GIOITINH");
                    String email = r.getString("EMAIL");
                    String diachi = r.getString("DIACHI");
                    String sdt = r.getString("SDT");
                    int tongchitieu = r.getInt("TONGCHITIEU");
                    dskh.add(new KhachHang(makh, hokh,tenkh, gioitinh, email,diachi, sdt, tongchitieu));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng khách hàng");
        } finally {
            qlkhConnection.closeConnect();
        }

        return dskh;
    }
    
    public Boolean add(KhachHang kh) {
        qlkhConnection = new ConnectionDB();
        Boolean ok = qlkhConnection.sqlUpdate("INSERT INTO `khachhang` (`MAKH`, `TENKH`, `HOKH`, `GIOITINH`, `DIACHI`, `SDT`, `EMAIL`, `TONGCHITIEU`) VALUES ('"
                + kh.getMaKhachHang()+ "', '"
                + kh.getTen()+ "', '"
                + kh.getHo()+ "', '"
                + kh.getGioiTinh()+ "','"
                + kh.getDiachi()+ "','"
                + kh.getSDT()+ "','"
                + kh.getEmail()+ "','"
                + kh.getTongChiTieu()+ "');");
        qlkhConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String makh) {
        qlkhConnection = new ConnectionDB();
        Boolean ok = qlkhConnection.sqlUpdate("DELETE FROM `khachhang` WHERE `khachhang`.`MAKH` = '" + makh + "'");
        qlkhConnection.closeConnect();
        return ok;
    }
    
    public Boolean update(String maKhachHang, String ho, String ten, String gioiTinh, String Email,String DiaChi, String SDT, int tongChiTieu) {
        qlkhConnection = new ConnectionDB();
        Boolean ok = qlkhConnection.sqlUpdate("UPDATE khachhang SET HOKH='"+ho+"', TENKH='"+ten+"', GIOITINH='"+gioiTinh+"', EMAIL='"+Email+"', DIACHI='"+DiaChi+"', SDT='"+SDT+"',TONGCHITIEU="+tongChiTieu+" WHERE MAKH='"+maKhachHang+"'");
       
        qlkhConnection.closeConnect();
        return ok;
    }
    
    public Boolean updateTongChiTieu(String maKhachHang, int tongChiTieu) {
        qlkhConnection = new ConnectionDB();
        Boolean ok = qlkhConnection.sqlUpdate("Update khachhang Set "
                + "TONGCHITIEU='" +  tongChiTieu
                + "' where MAKH='" + maKhachHang + "'");
        qlkhConnection.closeConnect();
        return ok;
    }
    
    public void close(){
        qlkhConnection.closeConnect();
    }

   
}
