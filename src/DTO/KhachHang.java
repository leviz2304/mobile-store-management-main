package DTO;

public class KhachHang {
    private String maKhachHang, ho, ten, gioiTinh, Email, SDT, diachi;
    private int tongChiTieu = 0;

    public KhachHang() {
        ;
    }
    public KhachHang(String maKhachHang, String ho, String ten, String gioiTinh, String Email,String diachi, String SDT, int tongChiTieu) {
        this.maKhachHang = maKhachHang;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.Email = Email;
        this.SDT = SDT;
        this.tongChiTieu = tongChiTieu;
        this.diachi = diachi;
    }
    public KhachHang(KhachHang kh) {
        this.maKhachHang = kh.getMaKhachHang();
        this.ho = kh.getHo();
        this.ten = kh.getTen();
        this.gioiTinh = kh.getGioiTinh();
        this.Email = getEmail();
        this.SDT = kh.getSDT();
        this.tongChiTieu = kh.getTongChiTieu();
        this.diachi = kh.getDiachi();
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
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

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getTongChiTieu() {
        return tongChiTieu;
    }

    public void setTongChiTieu(int tongChiTieu) {
        this.tongChiTieu = tongChiTieu;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKhachHang='" + maKhachHang + '\'' +
                ", ho='" + ho + '\'' +
                ", ten='" + ten + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", Email='" + Email + '\'' +
                ", SDT='" + SDT + '\'' +
                ", diachi='" + diachi + '\'' +
                ", tongChiTieu=" + tongChiTieu +
                '}';
    }
}
