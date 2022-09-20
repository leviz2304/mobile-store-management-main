package DTO;

import java.time.LocalDate;

public class HoaDon {
    private String maHoaDon, maKhachHang, maNhanVien, maGiamGia;
    private LocalDate ngayLap;
    private int tongTien;
    private int tongTienGiamGia;
    
    public HoaDon() {
        ;
        ngayLap = LocalDate.now();
    }
    public HoaDon(String maHoaDon, String maKhachHang, String maNhanVien, String maGiamGia, LocalDate ngayLap, int tongTien, int tongTienGiamGia) {
        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
        this.maNhanVien = maNhanVien;
        this.maGiamGia = maGiamGia;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.tongTienGiamGia = tongTienGiamGia;
    }
    public HoaDon(HoaDon hd) {
        this.maHoaDon = hd.getMaHoaDon();
        this.maKhachHang = hd.getMaKhachHang();
        this.maNhanVien = hd.getMaNhanVien();
        this.maGiamGia = hd.getMaGiamGia();
        this.ngayLap = hd.getNgayLap();
        this.tongTien = hd.getTongTien();
        this.tongTienGiamGia = hd.getTongTienGiamGia();
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getTongTienGiamGia() {
        return tongTienGiamGia;
    }

    public void setTongTienGiamGia(int tongTienGiamGia) {
        this.tongTienGiamGia = tongTienGiamGia;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", maKhachHang='" + maKhachHang + '\'' +
                ", maNhanVien='" + maNhanVien + '\'' +
                ", maGiamGia='" + maGiamGia + '\'' +
                ", ngayLap=" + ngayLap +
                ", tongTien=" + tongTien +
                ", tongTienGiamGia=" + tongTienGiamGia +
                '}';
    }
}
