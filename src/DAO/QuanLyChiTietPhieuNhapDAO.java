/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietPhieuNhap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Administrator
 */
public class QuanLyChiTietPhieuNhapDAO {
    ConnectionDB qlctpnConnection;

    public ArrayList<ChiTietPhieuNhap> readDB() {
        ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();
        qlctpnConnection = new ConnectionDB();
        try {

            String query = "SELECT * FROM chitietphieunhap";
            ResultSet r = qlctpnConnection.sqlQuery(query);
            if (r != null) {
                while (r.next()) {
                    String mapn = r.getString("MAPHIEUNHAP");
                    String masp = r.getString("MASANPHAM");
                    Integer soluong = r.getInt("SOLUONG");
                    Integer dongia = r.getInt("DONGIA");

                    ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(mapn, masp, soluong, dongia);
                    dsctpn.add(ctpn);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không thấy data cần tìm trong ResultSet");

        } finally {
            qlctpnConnection.closeConnect();
        }
        return dsctpn;

    }
    public ArrayList<ChiTietPhieuNhap> search(String columName, String value) {
        ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();
        qlctpnConnection = new ConnectionDB();
        try {

            String query = "SELECT * FROM chitietphieunhap WHERE" + columName + "LIKE '%" + value + "%'";
            ResultSet r = qlctpnConnection.sqlQuery(query);
            if (r != null) {
                while (r.next()) {
                    String mapn = r.getString("MAPHIEUNHAP");
                    String masp = r.getString("MASANPHAM");
                    Integer soluong = r.getInt("SOLUONG");
                    Integer dongia = r.getInt("DONGIA");

                    ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(mapn, masp, soluong, dongia);
                    dsctpn.add(ctpn);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không thấy data cần tìm trong ResultSet");

        } finally {
            qlctpnConnection.closeConnect();
        }
        return dsctpn;

    }
    public boolean add(ChiTietPhieuNhap ctpn) {
        qlctpnConnection = new ConnectionDB();
        Boolean ok = qlctpnConnection.sqlUpdate("INSERT INTO `chitietphieunhap`(`MAPHIEUNHAP`,`MASANPHAM`,`SOLUONG`,`DONGIA`) VALUE('"
                + ctpn.getMaPhieuNhap()+ "', '" 
                + ctpn.getMaSanPham()+ "','" 
                + ctpn.getSoLuong() + "','" 
                + ctpn.getDonGia() + "')");
        qlctpnConnection.closeConnect();
        return ok;

    }

    public Boolean deleteAll(String mapn) {
        qlctpnConnection = new ConnectionDB();
        Boolean ok = qlctpnConnection.sqlUpdate("DELETE FROM `chitietphieunhap` WHERE `MAPHIEUNHAP`='" + mapn + "';");
        qlctpnConnection.closeConnect();
        return ok;
    }
    public Boolean delete(String mapn, String masp) {
        qlctpnConnection = new ConnectionDB();
        Boolean ok = qlctpnConnection.sqlUpdate("DELETE FROM `chitietphieunhap` WHERE `MAPHIEUNHAP`='" + mapn + "' AND MASANPHAM='" + masp + "';");
        qlctpnConnection.closeConnect();
        return ok;
    }

    public boolean update(String maPhieuNhap, String maSanPham, int soLuong, int donGia) {
        qlctpnConnection = new ConnectionDB();
        Boolean ok = qlctpnConnection.sqlUpdate("UPDATE `chitietphieunhap` SET "
                + "SOLUONG='" + soLuong
                + "',DONGIA='" + donGia
                + "' WHERE MAPHIEUNHAP='" + maPhieuNhap + "' AND MASANPHAM='" + maSanPham + "';");
        qlctpnConnection.closeConnect();
        return ok;
    }

}
