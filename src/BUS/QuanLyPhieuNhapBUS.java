/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.QuanLyNhaCungCapDAO;
import DAO.QuanLyPhieuNhapDAO;
import DTO.PhieuNhap;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class QuanLyPhieuNhapBUS {
    private static ArrayList<PhieuNhap> dspn = new ArrayList<>();
    private QuanLyPhieuNhapDAO qlpnDAO = new QuanLyPhieuNhapDAO();

    public QuanLyPhieuNhapBUS() {
        if(dspn.isEmpty()) {
            dspn = qlpnDAO.readDB();
        }
    }
    
     public ArrayList<PhieuNhap> getDspn() {
        return dspn;
    }
     
    public String[] getHeaders() {
        return new String[]{"Mã Phiếu Nhập", "Mã Nhân Viên", "Mã Nhà Cung Cấp", "Ngày Nhập", "Tổng tiền"};
    }

    public void readDB() {
        if(dspn.isEmpty()) {
            dspn = qlpnDAO.readDB();
        }
    }
    
    public PhieuNhap getPhieuNhap(String mapn) {
        for(PhieuNhap pn : dspn) {
            if(pn.getMaPhieuNhap().equals(mapn))
                return pn;
        }
        return null;
    }

    public Boolean themPhieuNhap(PhieuNhap pn) {
        Boolean check = qlpnDAO.add(pn);

        if(check) {
            dspn.add(pn);
        }
        return check;
    }
    
    public Boolean themPhieuNhap(String maPhieuNhap, String maNhanVien, String maNCC, LocalDate ngayNhap, int tongTien) {
        PhieuNhap pn = new PhieuNhap(maPhieuNhap, maNhanVien, maNCC, ngayNhap, tongTien);
        return themPhieuNhap(pn);
    }
    
     public Boolean update(String maPhieuNhap, String maNhanVien, String maNCC, LocalDate ngayNhap, int tongTien) {
        PhieuNhap pn = new PhieuNhap(maPhieuNhap, maNhanVien, maNCC, ngayNhap,tongTien);
        return update(pn);
    }
    
    public Boolean update(PhieuNhap pn) {
        Boolean success = qlpnDAO.update(pn);
        if (success) {
            for (PhieuNhap cthd : dspn) {
                if (cthd.getMaPhieuNhap().equals(pn.getMaPhieuNhap())) {
                    cthd = pn;
                }
            }
            return true;
        }
        return false;
    }


    
    public boolean xoaPhieuNhap(String mapn) {
        Boolean ok = qlpnDAO.delete(mapn);
        if (ok) {
            for (int i = (dspn.size() - 1); i >= 0; i--) {
                if (dspn.get(i).getMaPhieuNhap().equals(mapn)) {
                    dspn.remove(i);
                    return true;
                }
            }

        }
        return false;
    }
   
    
    public String setMaPN() {
        int max=0;
        for(PhieuNhap pn : dspn) {
            String  latestID = pn.getMaPhieuNhap();
            int n = Integer.parseInt(latestID.substring(2));
            if(max < n) {
                max = n;
            }
        }
         return "PN"+String.valueOf(++max);
    }
    public Boolean updateTongTien(String maPN, int TongTien) {
        for(PhieuNhap pn : dspn) {
            if(pn.getMaPhieuNhap().equals(maPN)) {
                int tongTien = pn.getTongTien() + TongTien;
                Boolean check = qlpnDAO.updateTongTien(maPN,tongTien);
                if(check) {
                    pn.setTongTien(tongTien);
                    return true;
                }
            }
        }
        return false;
    }
}
