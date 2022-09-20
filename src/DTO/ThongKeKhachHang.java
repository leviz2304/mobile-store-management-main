package DTO;

import java.util.Date;

public class ThongKeKhachHang {
    private String hotenKH;
    private int soLuongMua;
    private Date ngayMua;
    private int tongTien;

    public ThongKeKhachHang(String tenKH, int soLuongMua, Date ngayMua, int tongTien) {
        this.hotenKH = tenKH;
        this.soLuongMua = soLuongMua;
        this.ngayMua = ngayMua;
        this.tongTien = tongTien;
    }

    public String getHotenKH() {
        return hotenKH;
    }

    public void setHotenKH(String hotenKH) {
        this.hotenKH = hotenKH;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
