package DTO;

public class SanPham {
    private String maSP, maLoaiSP, maNSX, tenSP;
    private int soLuong;
    private int giaTien;

    public SanPham() {
        ;
    }

    public SanPham(String maSP, String tenSP, String maLoaiSP, String maNSX, int soLuong, int giaTien) {
        this.maSP = maSP;
        this.maLoaiSP = maLoaiSP;
        this.maNSX = maNSX;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    public SanPham(SanPham sp) {
        this.maSP = sp.getMaSP();
        this.tenSP = sp.getTenSP();
        this.maLoaiSP = sp.getMaLoaiSP();
        this.maNSX = sp.getMaNSX();
        this.soLuong = sp.getSoLuong();
        this.giaTien = sp.getGiaTien();
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(String maNSX) {
        this.maNSX = maNSX;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP='" + maSP + '\'' +
                ", maLoaiSP='" + maLoaiSP + '\'' +
                ", maNSX='" + maNSX + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", soLuong=" + soLuong +
                ", giaTien=" + giaTien +
                '}';
    }
}
