package DTO;

public class NhanVien {
    private String maNhanVien, ho, ten, gioiTinh, Email, diaChi, chucVu; 
    private int luong;
    private String SDT, hinhAnh,chuThich;
    public NhanVien() {
        ;
    }

    public NhanVien(String maNhanVien, String ho, String ten, String gioiTinh, String Email, String diaChi, String chucVu, int luong, String SDT, String hinhAnh, String chuThich) {
        this.maNhanVien = maNhanVien;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.Email = Email;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
        this.luong = luong;
        this.SDT = SDT;
        this.hinhAnh = hinhAnh;
        this.chuThich = chuThich;
    }

    
    
    public NhanVien(NhanVien nv){
        this.maNhanVien = nv.getMaNhanVien();
        this.ho = nv.getHo();
        this.ten = nv.getTen();
        this.gioiTinh = nv.getGioiTinh();
        this.Email = nv.getEmail();
        this.diaChi = nv.getDiaChi();
        this.chucVu = nv.getChucVu();
        this.luong = nv.getLuong();
        this.SDT = nv.getSDT();
        this.hinhAnh = nv.getHinhAnh();
        this.chuThich = nv.getChuThich();
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    
    public String getHo() {
        return ho;
    }
    
    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    
    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }

    
    
    
}
