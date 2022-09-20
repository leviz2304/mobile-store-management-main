package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKeDoanhThuNhanVIen;
import DTO.ThongKeKhachHang;
import DTO.ThongKeSanPham;

import java.util.ArrayList;

public class ThongKeBUS {
    private ThongKeDAO tkDAO = new ThongKeDAO();

    public ArrayList<ThongKeSanPham> getThongKeSP() {
        return tkDAO.getThongKe();
    }

    public ArrayList<ThongKeKhachHang> getThongKeKH() {
        return tkDAO.thongKeKH();
    }

    public ArrayList<ThongKeDoanhThuNhanVIen> getDTNV() {
        return tkDAO.getDTNV();
    }
    public String[] headerThongKeSP() {
        return new String[] {"Mã sản phẩm", "Tên sản phẩm", "Số lượng bán được", "Tổng tiền"};
    }
    public String[] headerThongKeKH() {
        return new String[] {"STT","Tên khách hàng", "Số sản phẩm mua", "Ngày mua", "Tổng tiền"};
    }

    public String[] headerDTNV() {
        return new String[] {"Mã nhân viên", "Tên nhân viên", "Số lượng sản phẩm bán được", "Doanh thu"};
    }

}
