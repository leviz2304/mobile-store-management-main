/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.QuanLyChuongTrinhGiamGiaDAO;
import DTO.ChiTietGiamGia;
import DTO.ChuongTrinhGiamGia;
import DTO.SanPham;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Gyn
 */
public class QuanLyChuongTrinhGiamGiaBUS {

    private static ArrayList<ChuongTrinhGiamGia> dschuongtrinhgg = new ArrayList();
    private QuanLyChuongTrinhGiamGiaDAO qlchuongtrinhggDAO = new QuanLyChuongTrinhGiamGiaDAO();


    public QuanLyChuongTrinhGiamGiaBUS() {
        if (dschuongtrinhgg.isEmpty()) {
            dschuongtrinhgg = qlchuongtrinhggDAO.readDB();
        }
    }

    public String[] getHeaders() {
        return new String[]{"Mã giảm giá", "Tên chương trình", "Ngày bắt đầu", "Ngày kết thúc"};
    }

    public void readDB() {
        if (dschuongtrinhgg.isEmpty()) {
            dschuongtrinhgg = qlchuongtrinhggDAO.readDB();
        }
    }

    public ChuongTrinhGiamGia getChuongTrinhGiamGia(String MaGiamGia) {
        for (ChuongTrinhGiamGia ctgg : dschuongtrinhgg) {
            if (ctgg.getMaGiamGia().equals(MaGiamGia)) {
                return ctgg;
            }
        }
        return null;
    }

    public Boolean themChuongTrinhGiamGia(ChuongTrinhGiamGia ctgg) {
        Boolean check = qlchuongtrinhggDAO.add(ctgg);
        if (check) {
            dschuongtrinhgg.add(ctgg);
        }
        return check;
    }

    public Boolean themChuongTrinhGiamGia(String MaGiamGia, String TenChuongTrinh, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        ChuongTrinhGiamGia ctgg = new ChuongTrinhGiamGia(MaGiamGia, TenChuongTrinh, ngayBatDau, ngayKetThuc);
        return themChuongTrinhGiamGia(ctgg);
    }

    public Boolean XoaChuongTrinhGiamGia(String MaGiamGia) {
        Boolean check = qlchuongtrinhggDAO.delete(MaGiamGia);
        if (check) {
            for (int i = (dschuongtrinhgg.size() - 1); i >= 0; i--) {
                if (dschuongtrinhgg.get(i).getMaGiamGia().equals(MaGiamGia)) {
                    dschuongtrinhgg.remove(i);
                }
            }
        }
        return check;
    }

    public Boolean updateCTGG(String maGiamGia, String tenChuongTrinh, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        Boolean check = qlchuongtrinhggDAO.update(maGiamGia, tenChuongTrinh, ngayBatDau, ngayKetThuc);
        if (check) {
            dschuongtrinhgg.forEach((ChuongTrinhGiamGia) ->{ 
            if (ChuongTrinhGiamGia.getMaGiamGia().equals(maGiamGia)) {
                ChuongTrinhGiamGia.setTenChuongTrinh(tenChuongTrinh);
                ChuongTrinhGiamGia.setNgayBatDau(ngayBatDau);
                ChuongTrinhGiamGia.setNgayKetThuc(ngayKetThuc);
                }
            });
        }
        return check;
    }
    public ArrayList<ChuongTrinhGiamGia> getDSCTGG(){
        return dschuongtrinhgg;
    }

    public String getLatestID() {
        int max=0;
        for(ChuongTrinhGiamGia ct : dschuongtrinhgg) {
            String latestID = ct.getMaGiamGia();
            int n = Integer.parseInt(latestID.substring(2));
            if(max < n) {
                max = n;
            }
        }


        return "GG"+String.valueOf(++max);
    }

}
