package DTO;

import java.time.LocalDate;

public class ChuongTrinhGiamGia {
    private String maGiamGia, tenChuongTrinh;
    private LocalDate ngayBatDau, ngayKetThuc;

    public ChuongTrinhGiamGia() {
        ;
    }
    public ChuongTrinhGiamGia(String maGiamGia, String tenChuongTrinh, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        this.maGiamGia = maGiamGia;
        this.tenChuongTrinh = tenChuongTrinh;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }
    public ChuongTrinhGiamGia(ChuongTrinhGiamGia ctgg) {
        this.maGiamGia = ctgg.getMaGiamGia();
        this.tenChuongTrinh = ctgg.getTenChuongTrinh();
        this.ngayBatDau = ctgg.getNgayBatDau();
        this.ngayKetThuc = ctgg.getNgayKetThuc();
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getTenChuongTrinh() {
        return tenChuongTrinh;
    }

    public void setTenChuongTrinh(String tenChuongTrinh) {
        this.tenChuongTrinh = tenChuongTrinh;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
    
    
}