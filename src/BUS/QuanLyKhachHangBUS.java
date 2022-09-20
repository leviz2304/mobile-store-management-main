package BUS;

import DAO.QuanLyKhachHangDAO;
import DTO.KhachHang;

import java.util.ArrayList;

public class QuanLyKhachHangBUS {
    private static ArrayList<KhachHang> dskh = new ArrayList<>();
    private QuanLyKhachHangDAO qlkhDAO = new QuanLyKhachHangDAO();

    public QuanLyKhachHangBUS() {
        if(dskh.isEmpty()) {
            dskh = qlkhDAO.readDB();
        }
    }
    public String[] getHeaders() {
        return new String[]{"Mã khách hàng", "Họ","Tên","Giới tính","Email", "Địa chỉ", "Số điện thoại", "Tổng chi tiêu"};
    }

    public void readDB() {
        if(dskh.isEmpty()) {
            dskh = qlkhDAO.readDB();
        }
    }

    public KhachHang getKhachHang(String makh) {
        for(KhachHang kh : dskh) {
            if(kh.getMaKhachHang().equals(makh))
                return kh;
        }
        return null;
    }

    public String getTenKH(String maKH) {
        for(KhachHang kh : dskh) {
            if(kh.getMaKhachHang().equals(maKH)) {
                return kh.getHo().trim() + " " + kh.getTen().trim();
            }
        }
        return null;
    }

    public ArrayList<KhachHang> timkiem(String makh, String tenKh, String sdt) {
        ArrayList<KhachHang> result = new ArrayList<>();

        return result;
    }

    public Boolean themKhachHang(KhachHang kh) {
        Boolean check = qlkhDAO.add(kh);

        if(check) {
            dskh.add(kh);
        }
        return check;
    }

    public Boolean themKhachHang(String maKH, String hoKH, String tenKH, String gender,String email, String diaChi, String SDT) {
        KhachHang kh = new KhachHang(maKH,hoKH, tenKH, gender, email, diaChi, SDT, 0);
        return themKhachHang(kh);
    }

    public Boolean xoaKhachHang(String makh) {
        Boolean check = qlkhDAO.delete(makh);

        if(check) {
            for (int i = (dskh.size() - 1); i >= 0; i--) {
                if (dskh.get(i).getMaKhachHang().equals(makh)) {
                    dskh.remove(i);
                }
            }
        }
        return check;
    }

    public Boolean updateKH(String maKH, String hoKH, String tenKH, String gender, String email, String diaChi, String SDT, int tongChitieu) {
        Boolean check = qlkhDAO.update(maKH, tenKH, hoKH, gender, email, diaChi, SDT, tongChitieu);

        if(check) {
            dskh.forEach((kh) -> {
                if(kh.getMaKhachHang().equals(maKH)) {
                    kh.setTen(tenKH);
                    kh.setHo(hoKH);
                    kh.setGioiTinh(gender);
                    kh.setEmail(email);
                    kh.setDiachi(diaChi);
                    kh.setSDT(SDT);
                    kh.setTongChiTieu(tongChitieu);
                }
            });
        }

        return  check;

    }
    public Boolean updateTCT(String makh, int tongchitieu) {
        Boolean check = qlkhDAO.updateTongChiTieu(makh,tongchitieu);

        if(check) {
            dskh.forEach((kh) -> {
                if(kh.getMaKhachHang().equals(makh)) {
                    kh.setTongChiTieu(tongchitieu);
                }
            });
        }
        return check;
    }
    public ArrayList<KhachHang> getDskh() {
        return dskh;
    }
    public String setMaKH() {
        int max=0;
        for(KhachHang kh : dskh) {
            String  latestID = kh.getMaKhachHang();
            int n = Integer.parseInt(latestID.substring(2));
            if(max < n) {
                max = n;
            }
        }


         return "KH"+String.valueOf(++max);
    }
    public ArrayList<KhachHang> searchKH(String option, String value) {
        ArrayList<KhachHang> result = new ArrayList<>();
        dskh.forEach((kh) -> {
            switch (option) {
                case "Mã khách hàng":
                    if(kh.getMaKhachHang().toLowerCase().contains(value.toLowerCase())){
                        result.add(kh);
                    }
                    break;
                case "Tên":
                    if(kh.getTen().toLowerCase().contains(value.toLowerCase())) {
                        result.add(kh);
                    }
                    break;
                case "Email":
                    if(kh.getEmail().toLowerCase().contains(value.toLowerCase())) {
                        result.add(kh);
                    }
                    break;
                case "Số điện thoại":
                    if(kh.getSDT().toLowerCase().contains(value.toLowerCase())) {
                        result.add(kh);
                    }
                    break;
            }
        });
        return result;
    }



}
