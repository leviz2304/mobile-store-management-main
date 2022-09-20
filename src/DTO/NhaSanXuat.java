package DTO;

public class NhaSanXuat {
    private String maNSX, tenNSX;

    public NhaSanXuat() {
        ;
    }
    public NhaSanXuat(String maNSX, String tenNSX) {
        this.maNSX = maNSX;
        this.tenNSX = tenNSX;
    }
    public NhaSanXuat(NhaSanXuat nsx) {
        this.maNSX = nsx.getMaNSX();
        this.tenNSX = nsx.getTenNSX();
    }

    public String getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(String maNSX) {
        this.maNSX = maNSX;
    }

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }
    
    
}
