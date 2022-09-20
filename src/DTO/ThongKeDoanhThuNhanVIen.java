package DTO;

public class ThongKeDoanhThuNhanVIen {
    private String maNhanVien;
    private String tenNhanVien;
    private int soLuong;
    private int tongTien;

    public ThongKeDoanhThuNhanVIen(String maNhanVien, String tenNhanVien, int soLuong, int tongTien) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
