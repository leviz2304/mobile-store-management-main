/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhanVien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class QuanLyNhanVienDAO {

    ConnectionDB qlnvConnection;

    public QuanLyNhanVienDAO() {

    }

    public ArrayList<NhanVien> readDB() {
        qlnvConnection = new ConnectionDB();
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        try {
            String qry = "SELECT * FROM nhanvien";
            ResultSet r = qlnvConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String manv = r.getString("MANHANVIEN");
                    String honv = r.getString("HONV");
                    String tennv = r.getString("TENNV");
                    String gioitinh = r.getString("GIOITINH");
                    String email = r.getString("EMAIL");
                    String diachi = r.getString("DIACHI");
                    String chucvu = r.getString("CHUCVU");
                    Integer luong = r.getInt("LUONG");
                    String sdt = r.getString("SDT");
                    String hinhanh = r.getString("HINHANH");
                    String chuthich = r.getString("CHUTHICH");
                    dsnv.add(new NhanVien(manv, honv, tennv, gioitinh, email, diachi, chucvu, luong, sdt, hinhanh, chuthich));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng nhân viên");
        } finally {
            qlnvConnection.closeConnect();
        }
        return dsnv;
    }

    public ArrayList<NhanVien> search(String columnName, String value) {
        qlnvConnection = new ConnectionDB();
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        try {
            String qry = "SELECT * FROM nhanvien WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qlnvConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String manv = r.getString("MANHANVIEN");
                    String honv = r.getString("HONV");
                    String tennv = r.getString("TENNV");
                    String gioitinh = r.getString("GIOITINH");
                    String email = r.getString("EMAIL");
                    String diachi = r.getString("DIACHI");
                    String chucvu = r.getString("CHUCVU");
                    Integer luong = r.getInt("LUONG");
                    String sdt = r.getString("SDT");
                    String hinhanh = r.getString("HINHANH");
                    String chuthich = r.getString("CHUTHICH");
                    dsnv.add(new NhanVien(manv, honv, tennv, gioitinh, email, diachi, chucvu, luong, sdt, hinhanh, chuthich));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng nhân viên");
        } finally {
            qlnvConnection.closeConnect();
        }
        return dsnv;
    }
    
    public Boolean add(NhanVien nv) {
        qlnvConnection = new ConnectionDB();
        Boolean ok = qlnvConnection.sqlUpdate("INSERT INTO `nhanvien` (`MANHANVIEN`,`HONV`, `TENNV`, `GIOITINH`, `EMAIL`,`DIACHI`,`CHUCVU`,`LUONG`, `SDT`, `HINHANH`, `CHUTHICH`) VALUES ('"
                + nv.getMaNhanVien()+ "', '"
                + nv.getTen()+ "', '" 
                + nv.getHo()+ "', '" 
                + nv.getGioiTinh()+ "', '" 
                + nv.getEmail()+ "', '" 
                + nv.getDiaChi() + "', '" 
                + nv.getChucVu()+ "', '" 
                + nv.getLuong()+ "', '" 
                + nv.getSDT() + "', '" 
                + nv.getHinhAnh()+ "', '"
                + nv.getChuThich()+ "');");
        qlnvConnection.closeConnect();
        return ok;
    }
    
    public Boolean delete(String maNhanVien) {
        qlnvConnection = new ConnectionDB();
        Boolean ok = qlnvConnection.sqlUpdate("DELETE FROM `nhanvien` WHERE `nhanvien`.`MANHANVIEN` = '" + maNhanVien + "'");
        qlnvConnection.closeConnect();
        return ok;
    }

    public Boolean update(String maNhanVien, String ho, String ten, String gioiTinh, String Email, String diaChi, String chucVu, int luong, String SDT, String hinhAnh, String chuThich) {
        qlnvConnection = new ConnectionDB();
        Boolean ok = qlnvConnection.sqlUpdate("UPDATE `nhanvien` SET "
                + "HONV='" + ho
                + "',TENNV='" + ten
                + "',GIOITINH='" + gioiTinh
                + "',EMAIL='" + Email
                + "',DIACHI='" + diaChi
                + "',CHUCVU='" + chucVu
                + "',LUONG='" + luong
                + "',SDT='" + SDT
                + "',HINHANH='" + hinhAnh 
                + "',CHUTHICH='" + chuThich 
                + "' where MANHANVIEN='" + maNhanVien + "'");
        qlnvConnection.closeConnect();
        return ok;
    }
    public Boolean checkLogin(String email, String sdt) {
        qlnvConnection = new ConnectionDB();
        String query = "SELECT COUNT(MANHANVIEN) AS isNULL FROM nhanvien WHERE EMAIL='"+ email +"' AND SDT='"+sdt+"'";
        try {
            ResultSet r = qlnvConnection.sqlQuery(query);
            if (r != null) {
                int count = r.getInt("isNULL");
                if(count == 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng nhân viên");
        } finally {
            qlnvConnection.closeConnect();
        }
        return false;
    }

    public void close() {
        qlnvConnection.closeConnect();
    }

}
