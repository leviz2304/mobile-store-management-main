package DTO;

import java.time.LocalDate;

public class PhieuNhap {
    
    private String maPhieuNhap, maNhanVien, maNCC;
    private LocalDate ngayNhap;
    private int tongTien;

    public PhieuNhap() {
        ;
        this.ngayNhap = LocalDate.now();
    }
    public PhieuNhap(String maPhieuNhap, String maNhanVien, String maNCC, LocalDate ngayNhap, int tongTien) {
        this.maPhieuNhap = maPhieuNhap;
        this.maNhanVien = maNhanVien;
        this.maNCC = maNCC;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
    }
    public PhieuNhap(PhieuNhap pn) {
        this.maPhieuNhap = pn.getMaPhieuNhap();
        this.maNhanVien = pn.getMaNhanVien();
        this.maNCC = pn.getMaNCC();
        this.ngayNhap = pn.getNgayNhap();
        this.tongTien = pn.getTongTien();
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
    
}
