/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.QuanLyChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhap;
import DTO.SanPham;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class QuanLyChiTietPhieuNhapBUS {
    
    QuanLyChiTietPhieuNhapDAO qlctpnDAO = new QuanLyChiTietPhieuNhapDAO();
    QuanLyPhieuNhapBUS qlpnBUS = new QuanLyPhieuNhapBUS();
    QuanLySanPhamBUS qlspBUS = new QuanLySanPhamBUS();
    ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();
    
    public QuanLyChiTietPhieuNhapBUS() {
        if(dsctpn.isEmpty()){
            dsctpn = qlctpnDAO.readDB();
        }
    }
    
    public void readDB() {
        if(dsctpn.isEmpty()){
            dsctpn = qlctpnDAO.readDB();
        }
    }
    
    public String[] getHeaders() {
        return new String[]{"Mã Phiếu Nhập", "Mã Sản Phẩm", "Số Lượng", "Đơn Giá"};
    }
    

    
    public ChiTietPhieuNhap getChiTiet(String mapn, String masp) {
        for (ChiTietPhieuNhap ctpn : dsctpn) {
            if (ctpn.getMaSanPham().equals(masp) && ctpn.getMaPhieuNhap().equals(mapn)) {
                return ctpn;
            }
        }
        return null;
    }
    
    public ArrayList<ChiTietPhieuNhap> getAllChiTiet(String mapn) {
        ArrayList<ChiTietPhieuNhap> result = new ArrayList<>();
        for(ChiTietPhieuNhap ctpn : dsctpn) {
            if(ctpn.getMaPhieuNhap().equals(mapn)) {
                result.add(ctpn);
            }
        }
        return result;
    }
    public ArrayList<ChiTietPhieuNhap> getDSChiTietPN() {
        return dsctpn;
    }


    
    public boolean themChiTietPN(String mapn, String masp, Integer soluong, Integer dongia) {
        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(mapn, masp, soluong, dongia);
        return themChiTietPN(ctpn);
    }
    public Boolean themChiTietPN(ChiTietPhieuNhap ctpn) {
        Boolean check = qlctpnDAO.add(ctpn);
        if(check) {
            dsctpn.add(ctpn);
            int TongTien = ctpn.getDonGia() * ctpn.getSoLuong();
            qlpnBUS.updateTongTien(ctpn.getMaPhieuNhap(),TongTien);
            return true;
        }
        return false;

    }
    
    public boolean delete(String mapn, String masp) {
        Boolean ok = qlctpnDAO.delete(mapn,masp);
        if (ok) {
            for (int i = (dsctpn.size() - 1); i >= 0; i--) {
                if (dsctpn.get(i).getMaPhieuNhap().equals(mapn) && dsctpn.get(i).getMaSanPham().equals(masp)) {
                    int TongTien = dsctpn.get(i).getDonGia() * dsctpn.get(i).getSoLuong() *-1;
                    qlpnBUS.updateTongTien(dsctpn.get(i).getMaPhieuNhap(),TongTien);
                    dsctpn.remove(i);
                }
            }

        }
        return ok;
    }
    
    public Boolean update(String mapn, String masp, int soluong, int dongia) {
        Boolean ok = qlctpnDAO.update(mapn, masp, soluong, dongia);

        if (ok) {
            dsctpn.forEach((ctpn) -> {
                if (ctpn.getMaPhieuNhap().equals(mapn) && ctpn.getMaSanPham().equals(masp)) {
                    int curTotal = ctpn.getSoLuong() * ctpn.getDonGia();
                    int newTotal = (soluong * dongia) - curTotal;
                    qlpnBUS.updateTongTien(mapn,newTotal);
                    ctpn.setDonGia(dongia);
                    ctpn.setSoLuong(soluong);
                }
            });
        }

        return ok;
    }
    

    
    
}    
