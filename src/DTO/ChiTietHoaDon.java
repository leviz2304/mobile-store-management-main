package DTO;

public class ChiTietHoaDon {
    private String maHoaDon, maSanPham;
    private int soLuong, thanhTien, chietKhau;

    public ChiTietHoaDon() {
        ;
    }
    public ChiTietHoaDon(String maHoaDon, String maSanPham, int soLuong, int thanhTien, int chietKhau) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.chietKhau = chietKhau;
    }
    public ChiTietHoaDon(ChiTietHoaDon cthd) {
        this.maHoaDon = cthd.getMaHoaDon();
        this.maSanPham = cthd.getMaSanPham();
        this.soLuong = cthd.getSoLuong();
        this.thanhTien = cthd.getThanhTien();
        this.chietKhau = cthd.getChietKhau();
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(int chietKhau) {
        this.chietKhau = chietKhau;
    }

    
    
}

