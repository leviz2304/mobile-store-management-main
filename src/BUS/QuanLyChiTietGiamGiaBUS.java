/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.QuanLyChiTietGiamGiaDAO;
import DTO.ChiTietGiamGia;
import DTO.SanPham;

import java.util.ArrayList;

/**
 *
 * @author Gyn
 */
public class QuanLyChiTietGiamGiaBUS {

    private static ArrayList<ChiTietGiamGia> dschitietgg = new ArrayList();
    private QuanLyChiTietGiamGiaDAO qlchitietggDAO = new QuanLyChiTietGiamGiaDAO();
    private QuanLySanPhamBUS qlspBUS = new QuanLySanPhamBUS();
    private QuanLyChuongTrinhGiamGiaBUS qlctggBUS = new QuanLyChuongTrinhGiamGiaBUS();

    public QuanLyChiTietGiamGiaBUS() {
        if (dschitietgg.isEmpty()) {
            dschitietgg = qlchitietggDAO.readDB();
        }
    }

    public void readDB() {
        if (dschitietgg.isEmpty()) {
            dschitietgg = qlchitietggDAO.readDB();
        }
    }


    public String[] getHeaders() {
        return new String[]{"Mã giảm giá", "Mã sản phẩm", "Chiết khấu"};
    }

    public ChiTietGiamGia getChiTietGiamGia(String MaGiamGia) {
        for (ChiTietGiamGia ctgg : dschitietgg) {
            if (ctgg.getMaGiamGia().equals(MaGiamGia)) {
                return ctgg;
            }
        }
        return null;
    }

    public Boolean themChiTietGiamGia(ChiTietGiamGia ctgg) {
        Boolean check = qlchitietggDAO.add(ctgg);
        if (check) {
            dschitietgg.add(ctgg);
        }
        return check;
    }

    public Boolean themChiTietGiamGia(String maGiamGia, String maSanPham, int chietKhau) {
        ChiTietGiamGia ctgg = new ChiTietGiamGia(maGiamGia, maSanPham, chietKhau);
        return themChiTietGiamGia(ctgg);
    }

    public Boolean XoaChiTietGiamGia(String maGiamGia, String maSanPham) {
        Boolean check = qlchitietggDAO.delete(maGiamGia, maSanPham);
        if (check) {
            for (int i = (dschitietgg.size() - 1); i >= 0; --i) {
                if (dschitietgg.get(i).getMaGiamGia().equals(maGiamGia) && dschitietgg.get(i).getMaSanPham().equals(maSanPham)) {
                    dschitietgg.remove(i);
                }
            }
        }
        return check;
    }

    public Boolean updateCTGG(String maGiamGia, String maSanPham, int chietKhau) {
        Boolean check = qlchitietggDAO.update(maGiamGia, maSanPham, chietKhau);
        if (check) {
            dschitietgg.forEach((ChiTietGiamGia) -> {
                if (ChiTietGiamGia.getMaGiamGia().equals(maGiamGia) && ChiTietGiamGia.getMaSanPham().equals(maSanPham)) {
                    ChiTietGiamGia.setChietKhau(chietKhau);
                }
            });
        }
        return check;
    }

    public ArrayList<ChiTietGiamGia> getDSChiTietGG() {
        return dschitietgg;
    }

    public ArrayList<ChiTietGiamGia> getChiTietGGbyID(String maCT) {
        ArrayList<ChiTietGiamGia> dsct = new ArrayList<>();
        dschitietgg.forEach((ct) -> {
            if(ct.getMaGiamGia().equals(maCT)) {
                dsct.add(ct);
            }
        });
        return dsct;

    }
    public int getChietKhau(String maGG, String maSP) {
        for(ChiTietGiamGia ctgg : dschitietgg) {
            if(ctgg.getMaGiamGia().equals(maGG) && ctgg.getMaSanPham().equals(maSP))
                return ctgg.getChietKhau();
        }
        return 0;
    }
}

