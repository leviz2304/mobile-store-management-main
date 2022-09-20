package DTO;

public class NhaCungCap {
    
    private String maNCC, tenNCC , Email , diaChi , SDT;

    public NhaCungCap() {
        ;
    }
    public NhaCungCap(String maNCC, String tenNCC , String Email , String diaChi , String SDT) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.Email = Email;
        this.diaChi = diaChi;
        this.SDT = SDT;
    }
    public NhaCungCap(NhaCungCap ncc) {
        this.maNCC = ncc.getMaNCC();
        this.tenNCC = ncc.getTenNCC();
        this.Email = ncc.getEmail();
        this.diaChi = ncc.getDiaChi();
        this.SDT = ncc.getSDT();
    }
    
    public String getMaNCC() {
        return maNCC;
    }
    
    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }
    
    public String getTenNCC() {
        return tenNCC ;
    }
    
    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC ;
    }
    
    public String getEmail() {
        return Email ;
    }
    
    public void setEmail(String Email) {
        this.Email = Email ;
    }
    
    public String getDiaChi() {
        return diaChi ;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi ;
    }
    
    public String getSDT() {
        return SDT ;
    }
    
    public void setSDT(String SDT) {
        this.SDT = SDT ;
    }
}