package DTO;

public class ChiTietGiamGia {
    private String maGiamGia;
    private String maSanPham;
    private int chietKhau;

    public ChiTietGiamGia() {
        ;
    }
    public ChiTietGiamGia(String maGiamGia, String maSanPham, int chietKhau) {
        this.maGiamGia = maGiamGia;
        this.maSanPham = maSanPham;
        this.chietKhau = chietKhau;
    }
    public ChiTietGiamGia(ChiTietGiamGia ctgg) {
        this.maGiamGia = ctgg.getMaGiamGia();
        this.maSanPham = ctgg.getMaSanPham();
        this.chietKhau = ctgg.getChietKhau();
    }

    public int getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(int chietKhau) {
        this.chietKhau = chietKhau;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

}

