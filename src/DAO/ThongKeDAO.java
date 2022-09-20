package DAO;

import DTO.ThongKeDoanhThuNhanVIen;
import DTO.ThongKeKhachHang;
import DTO.ThongKeSanPham;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ThongKeDAO {
    ConnectionDB thongke;

    public ThongKeDAO() {
    }

    public ArrayList<ThongKeKhachHang> thongKeKH() {
        ArrayList<ThongKeKhachHang> result = new ArrayList<>();
        thongke = new ConnectionDB();
        try {
            String query = "SELECT khachhang.HOKH, khachhang.TENKH, COUNT(chitiethoadon.MAHOADON) AS SOLUONG, hoadon.NGAYLAP AS NGAYMUA, hoadon.TONGTIEN AS TONGTIEN FROM khachhang, hoadon, chitiethoadon WHERE khachhang.MAKH = hoadon.MAKHACHHANG AND chitiethoadon.MAHOADON = hoadon.MAHOADON GROUP BY chitiethoadon.MAHOADON ORDER BY `SOLUONG` DESC, NGAYMUA DESC";
            ResultSet row = thongke.sqlQuery(query);
            if(row != null) {
                while (row.next()) {
                    String tenkh = row.getString("HOKH") + " " + row.getString("TENKH");
                    int soLuong = row.getInt("SOLUONG");
                    Date ngayMua = row.getDate("NGAYMUA");
                    int tongTien = row.getInt("TONGTIEN");

                    result.add(new ThongKeKhachHang(tenkh,soLuong,ngayMua,tongTien));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Không thể xuất thống kê");
        } finally {
            thongke.closeConnect();
        }
        return result;
    }

    public ArrayList<ThongKeSanPham> getThongKe() {
        ArrayList<ThongKeSanPham> dstk = new ArrayList<>();
        thongke = new ConnectionDB();
        try {
            String query = "SELECT chitiethoadon.MASANPHAM, TENSANPHAM, SUM(chitiethoadon.SOLUONG) AS SOLUONG, SUM(THANHTIEN) AS TONGTIEN FROM chitiethoadon, sanpham WHERE chitiethoadon.MASANPHAM = sanpham.MASANPHAM GROUP BY chitiethoadon.MASANPHAM ORDER BY SOLUONG DESC";
            ResultSet row = thongke.sqlQuery(query);
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
            thongke.closeConnect();
        }
        return dstk;
    }

    public ArrayList<ThongKeDoanhThuNhanVIen> getDTNV() {
        ArrayList<ThongKeDoanhThuNhanVIen> dstk = new ArrayList<>();
        thongke = new ConnectionDB();
        try {
            String query = "SELECT nhanvien.MANHANVIEN, nhanvien.HONV, nhanvien.TENNV, sum(chitiethoadon.SOLUONG) AS SOLUONG, SUM(chitiethoadon.THANHTIEN) AS TONGTIEN FROM hoadon JOIN nhanvien on hoadon.MANHANVIEN = nhanvien.MANHANVIEN JOIN chitiethoadon on hoadon.MAHOADON = chitiethoadon.MAHOADON GROUP by nhanvien.MANHANVIEN, nhanvien.HONV, nhanvien.TENNV";
            ResultSet row = thongke.sqlQuery(query);
            if(row != null) {
                while (row.next()) {
                    String maNV = row.getString("MANHANVIEN");
                    String tenNV = row.getString("HONV") + " " + row.getString("TENNV");
                    int soLuong = row.getInt("SOLUONG");
                    int tongTien = row.getInt("TONGTIEN");

                    dstk.add(new ThongKeDoanhThuNhanVIen(maNV,tenNV,soLuong,tongTien));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Không thể xuất thống kê");
        } finally {
            thongke.closeConnect();
        }
        return dstk;
    }
}
