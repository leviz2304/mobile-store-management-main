package BUS;

import DAO.QuanLyHoaDonDAO;
import DTO.HoaDon;
import DTO.KhachHang;

import java.time.LocalDate;
import java.util.ArrayList;

public class QuanLyHoaDonBUS {
    private static ArrayList<HoaDon> dshd = new ArrayList<>();
    private QuanLyHoaDonDAO qlhdDAO = new QuanLyHoaDonDAO();

    public QuanLyHoaDonBUS() {
        if(dshd.isEmpty()) {
            dshd = qlhdDAO.readDB();
        }
    }
    public ArrayList<HoaDon> getDshd() {
        return this.dshd;
    }

    public void setDshd() {
        if(dshd.isEmpty()) {
            dshd = qlhdDAO.readDB();
        }
    }

    public String[] getHeader() {
        return new String[] {"Mã hóa đơn", "Mã khách hàng", "Mã nhân viên", "Mã giảm giá", "Ngày Lập", "Tổng tiền", "Tổng chiết khấu"};
    }

    public HoaDon getHoaDon(String mahd) {
        for(HoaDon hd: dshd) {
            if(hd.getMaHoaDon().equals(mahd))
                return hd;
        }
        return null;
    }
    public String getMaKH(String maHD) {
        for(HoaDon hd : dshd) {
            if(hd.getMaHoaDon().equals(maHD)) {
                return hd.getMaKhachHang();
            }
        }
        return null;
    }

    public int getTotal(String maHD) {
        for(HoaDon hd : dshd) {
            if(hd.getMaHoaDon().equals(maHD)) {
                return hd.getTongTien();
            }
        }
        return 0;
    }
    public int getDiscount(String maHD) {
        for(HoaDon hd : dshd) {
            if(hd.getMaHoaDon().equals(maHD)) {
                return hd.getTongTienGiamGia();
            }
        }
        return 0;
    }

    public String getMaNV(String maHD) {
        for(HoaDon hd : dshd) {
            if(hd.getMaHoaDon().equals(maHD)) {
                return hd.getMaNhanVien();
            }
        }
        return null;
    }

    public Boolean themHD(HoaDon hd) {
        Boolean check = qlhdDAO.add(hd);

        if(check) {
            dshd.add(hd);
            return true;
        }
        return false;
    }

    public Boolean updateHD(HoaDon hd) {
        Boolean check = qlhdDAO.update(hd);
        if(check) {
            dshd.forEach((hd1) -> {
                if(hd1.getMaHoaDon().equals(hd.getMaHoaDon()));
                    hd1 = hd;
            });
            return true;
        }
        return false;
    }

    public Boolean xoaHD(String maHD) {
        Boolean check = qlhdDAO.delete(maHD);
        if(check) {
            for (int i = (dshd.size() - 1); i >= 0; i--) {
                if (dshd.get(i).getMaHoaDon().equals(maHD)) {
                    dshd.remove(i);
                    return true;
                }
            }
            return true;

        }
        return false;
    }
    public String setMaHD() {
        int max = 0;
        for (HoaDon hd : dshd) {
            String latestID = hd.getMaHoaDon();
            int n = Integer.parseInt(latestID.substring(2));
            if (max < n) {
                max = n;
            }
        }

        return "HD" + String.valueOf(++max);
    }

    public Boolean updateTongTien(String maHD, int tongTien) {
        for(HoaDon hd : dshd) {
            if(hd.getMaHoaDon().equals(maHD)) {
                int curTotal = hd.getTongTien();
                Boolean check = qlhdDAO.updateTongTien(maHD,curTotal+tongTien);
                if(check) {
                    hd.setTongTien(curTotal+tongTien);
                    return true;
                }

            }
        }
        return false;
    }
    public ArrayList<HoaDon> search(String option, String keyword) {
        ArrayList<HoaDon> result = new ArrayList<>();
        dshd.forEach((hd) -> {
            switch (option) {
                case "Mã hoá đơn":
                    if(hd.getMaHoaDon().toLowerCase().equals(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                    break;
                case "Mã nhân viên":
                    if(hd.getMaNhanVien().toLowerCase().equals(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                    break;
                case "Mã khách hàng":
                    if(hd.getMaKhachHang().toLowerCase().equals(keyword.toLowerCase()))
                        result.add(hd);
                    break;
                case "Mã giảm giá":
                    if(hd.getMaGiamGia().toLowerCase().equals(keyword.toLowerCase()))
                        result.add(hd);
                    break;

            }
        });
        return result;
    }
    public ArrayList<HoaDon> searchByDate(LocalDate date1, LocalDate date2) {
        ArrayList<HoaDon> result = new ArrayList<>();
        dshd.forEach((hd) -> {
            if(hd.getNgayLap().isBefore(date2) && hd.getNgayLap().isAfter(date1)) {
                result.add(hd);
            }
        });
        return result;
    }
}
