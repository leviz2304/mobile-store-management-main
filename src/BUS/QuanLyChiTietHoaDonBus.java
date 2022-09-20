package BUS;

import DAO.QuanLyChiTietHoaDonDAO;
import DAO.QuanLySanPhamDAO;
import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import DTO.SanPham;
import DTO.ThongKeSanPham;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuanLyChiTietHoaDonBus {
    private static ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();
    private QuanLyChiTietHoaDonDAO qlcthdDAO = new QuanLyChiTietHoaDonDAO();
    private QuanLyHoaDonBUS qlhdBUS = new QuanLyHoaDonBUS();
    private QuanLySanPhamBUS qlspBUS = new QuanLySanPhamBUS();


    public QuanLyChiTietHoaDonBus() {
        if(dscthd.isEmpty()) {
            dscthd = qlcthdDAO.readDB();
        }
    }

    public void setDscthd() {
        if(dscthd.isEmpty()) {
            dscthd = qlcthdDAO.readDB();
        }
    }
    public String[] getHeader() {
        return new String[]{"Mã hóa đơn", "Mã sản phẩm", "Số lượng","Thành tiền","Chiết khấu"};
    }

    public Boolean themChiTiet(ChiTietHoaDon cthd) {
        Boolean check = qlcthdDAO.add(cthd);
        if(check) {
            dscthd.add(cthd);
            Boolean checkUpdateTT = qlhdBUS.updateTongTien(cthd.getMaHoaDon(),cthd.getThanhTien()-cthd.getChietKhau());
            return checkUpdateTT;
        }
        return false;
    }
    public String[] getHeadersThongKe() {
        return new String[] {"Mã sản phẩm", "Tên sản phẩm", "Số lượng bán được", "Tổng tiền"};
    }
    public Boolean xoaCT(String maHd, String maSP) {
        Boolean check = qlcthdDAO.delete(maHd,maSP);
        if(check) {
            for (int i = (dscthd.size() - 1); i >= 0; i--) {
                if (dscthd.get(i).getMaHoaDon().equals(maHd) && dscthd.get(i).getMaSanPham().equals(maSP)) {
                    int newTotal = dscthd.get(i).getThanhTien() - dscthd.get(i).getChietKhau();
                    Boolean updateTT = qlhdBUS.updateTongTien(dscthd.get(i).getMaHoaDon(),newTotal*-1);
                    if(updateTT) {
                        dscthd.remove(i);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public ArrayList<ChiTietHoaDon> getCTbyID(String mahd) {
        ArrayList<ChiTietHoaDon> result = new ArrayList<>();
        dscthd.forEach((cthd) -> {
            if(cthd.getMaHoaDon().equals(mahd)) {
                result.add(cthd);
            }
        });
        return result;
    }
    public Boolean updateQty(String mahd, String maSP, int Soluong) throws SQLException {
        int check = qlcthdDAO.updateQty(mahd,maSP,Soluong);
        if(check != 0) {
            dscthd.forEach((cthd) -> {
                if(cthd.getMaHoaDon().equals(mahd) && cthd.getMaSanPham().equals(maSP)) {
                    cthd.setSoLuong(Soluong);
                    cthd.setThanhTien(Soluong*check);
                }
            });
            return true;
        }
        return false;
    }
    public ArrayList<ThongKeSanPham> getThongKe() {
        return qlcthdDAO.getThongKe();
    }

    public Boolean updateSoLuongKho(String maSP, int soLuong) {
        for(SanPham sp : qlspBUS.getDssp()) {
            if(sp.getMaSP().equals(maSP)) {
                int cur_qty = sp.getSoLuong();
                Boolean Check = qlspBUS.updateSoluong(maSP,cur_qty-soLuong);
                if(Check) {
                    sp.setSoLuong(cur_qty-soLuong);
                    return true;
                }
            }
        }
        return false;
    }

}
