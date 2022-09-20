/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietHoaDon;
import DTO.ThongKeSanPham;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class QuanLyChiTietHoaDonDAO {

    ConnectionDB qlcthdconnection;

    public QuanLyChiTietHoaDonDAO() {

    }

    public ArrayList readDB() {
        ArrayList<ChiTietHoaDon> dshd = new ArrayList<>();
        qlcthdconnection = new ConnectionDB();
        try {
            String qry = "SELECT * FROM chitiethoadon";
            ResultSet rs = qlcthdconnection.sqlQuery(qry);
            if (rs != null) {
                while (rs.next()) {
                    String mahd = rs.getString("MAHOADON");
                    String masp = rs.getString("MASANPHAM");
                    int soluong = rs.getInt("SOLUONG");
                    int thanhtien = rs.getInt("THANHTIEN");
                    int chietkhau = rs.getInt("CHIETKHAU");

                    dshd.add(new ChiTietHoaDon(mahd, masp, soluong, thanhtien, chietkhau));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Khong tim thay du lieu !!");
        } finally {
            qlcthdconnection.closeConnect();
        }
        return dshd;
    }
    public ArrayList<ThongKeSanPham> getThongKe() {
        ArrayList<ThongKeSanPham> dstk = new ArrayList<>();
        qlcthdconnection = new ConnectionDB();
        try {
            String query = "SELECT chitiethoadon.MASANPHAM, TENSANPHAM, SUM(chitiethoadon.SOLUONG) AS SOLUONG, SUM(THANHTIEN) AS TONGTIEN FROM chitiethoadon, sanpham WHERE chitiethoadon.MASANPHAM = sanpham.MASANPHAM GROUP BY chitiethoadon.MASANPHAM";
            ResultSet row = qlcthdconnection.sqlQuery(query);
            if(row != null) {
                while (row.next()) {
                    String maSP = row.getString("MASANPHAM");
                    String tenSP = row.getString("TENSANPHAM");
                    int soLuong = row.getInt("SOLUONG");
                    int tongTien = row.getInt("TONGTIEN");

                    dstk.add(new ThongKeSanPham(maSP,tenSP,soLuong,tongTien));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Không thể xuất thống kê");
        } finally {
            qlcthdconnection.closeConnect();
        }
        return dstk;
    }

    public Boolean add(ChiTietHoaDon hd) {
        qlcthdconnection = new ConnectionDB();
        String query = "INSERT INTO `chitiethoadon`(`MAHOADON`,`MASANPHAM`,`SOLUONG`,`THANHTIEN`,`CHIETKHAU`) VALUES ('"
                + hd.getMaHoaDon()+ "', '"
                + hd.getMaSanPham()+ "', '"
                + hd.getSoLuong() + "', '"
                + hd.getThanhTien() + "', '"
                + hd.getChietKhau()+ "')";
        System.out.println(query);
        Boolean ok = qlcthdconnection.sqlUpdate(query);
        qlcthdconnection.closeConnect();
        return ok;
    }

    public Boolean delete(String mahd, String masp) {
        qlcthdconnection = new ConnectionDB();
        Boolean success = qlcthdconnection.sqlUpdate("DELETE FROM `chitiethoadon` WHERE "
                + "MAHOADON='" + mahd
                + "' AND MASANPHAM='" + masp + "';");
        qlcthdconnection.closeConnect();
        return success;
    }
    
    public Boolean deleteAll(String mahd) {
        qlcthdconnection = new ConnectionDB();
        Boolean ok = qlcthdconnection.sqlUpdate("DELETE FROM `chitiethoadon` WHERE `MAHD`='" + mahd + "';");
        qlcthdconnection.closeConnect();
        return ok;
    }
    
    public Boolean update(ChiTietHoaDon ct) {
        qlcthdconnection = new ConnectionDB();
        Boolean success = qlcthdconnection.sqlUpdate("UPDATE `chitiethoadon` SET "
                + "SOLUONG='" + ct.getSoLuong()
                + "', THANHTIEN='" + ct.getThanhTien()
                + "', CHIETKHAU='" + ct.getChietKhau()
                + "' WHERE MAHOADON='" + ct.getMaHoaDon()+ "' AND MASANPHAM='" + ct.getMaSanPham()+ "';");
        qlcthdconnection.closeConnect();
        return success;
    }
    
    public Boolean update(String maHoaDon, String maSanPham, int soLuong, int thanhTien, int chietKhau) {
        ChiTietHoaDon hd = new ChiTietHoaDon(maHoaDon, maSanPham, soLuong, thanhTien, chietKhau);
        return update(hd);
    }
    public int updateQty(String mahd, String maSP, int soLuong) throws SQLException {
        qlcthdconnection = new ConnectionDB();
        ResultSet execute = qlcthdconnection.sqlQuery("SELECT GIATIEN FROM sanpham WHERE MASANPHAM='"+maSP+"'");
        int Price = execute.getInt("GIATIEN");

        int total = soLuong * Price;

        Boolean check = qlcthdconnection.sqlUpdate("UPDATE chitiethoadon SET SOLUONG="+soLuong+" THANHTIEN="+total+"");
        qlcthdconnection.closeConnect();
        if(check)
            return Price;
        else
            return 0;
    }


    public void closeConnection() {
        qlcthdconnection.closeConnect();
    }

}
