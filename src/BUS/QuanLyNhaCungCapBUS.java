package BUS;

import DAO.QuanLyNhaCungCapDAO;
import DTO.KhachHang;
import DTO.NhaCungCap;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class QuanLyNhaCungCapBUS {
    private static ArrayList<NhaCungCap> dsncc = new ArrayList<>();
    private QuanLyNhaCungCapDAO qlnccDAO = new QuanLyNhaCungCapDAO();

    public QuanLyNhaCungCapBUS() {
        if(dsncc.isEmpty()) {
            dsncc = qlnccDAO.readDB();
        }
    }
    public String[] getHeaders() {
        return new String[]{"Mã NCC", "Tên NCC", "Email", "Địa chỉ", "SĐT"};
    }

    public void readDB() {
        if(dsncc.isEmpty()) {
            dsncc = qlnccDAO.readDB();
        }
    }

    public NhaCungCap getNhaCungCap(String mancc) {
        for(NhaCungCap ncc : dsncc) {
            if(ncc.getMaNCC().equals(mancc))
                return ncc;
        }
        return null;
    }
    public ArrayList<NhaCungCap> timkiem(String mancc, String tenncc) {
        ArrayList<NhaCungCap> result = new ArrayList<>();

        return result;
    }

    public Boolean themNhaCungCap(NhaCungCap ncc) {
        Boolean check = qlnccDAO.add(ncc);

        if(check) {
            dsncc.add(ncc);
        }
        return check;
    }

    public Boolean themNhaCungCap(String maNcc, String tenNcc, String email, String diaChi, String SDT) {
        NhaCungCap ncc = new NhaCungCap(maNcc, tenNcc, email, diaChi, SDT);
        return themNhaCungCap(ncc);
    }

    public Boolean xoaNhaCungCap(String mancc) {
        Boolean check = qlnccDAO.delete(mancc);

        if(check) {
            for (int i = (dsncc.size() - 1); i >= 0; i--) {
                if (dsncc.get(i).getMaNCC().equals(mancc)) {
                    dsncc.remove(i);
                }
            }
        }
        return check;
    }

    public Boolean updateNhaCungCap(String maNcc, String tenNcc, String email, String diaChi, String SDT) {
        Boolean check = qlnccDAO.update(maNcc, tenNcc, email, diaChi, SDT);

        if(check) {
            dsncc.forEach((ncc) -> {
                if(ncc.getMaNCC().equals(maNcc)) {
                    ncc.setTenNCC(tenNcc);
                    ncc.setEmail(email);
                    ncc.setDiaChi(diaChi);
                    ncc.setSDT(SDT);
                }
            });
        }

        return  check;

    }
    public ArrayList<NhaCungCap> getDsncc() {
        return dsncc;
    }
    public String setMaNCC() {
        int max=0;
        for(NhaCungCap ncc : dsncc) {
            String  latestID = ncc.getMaNCC();
            int n = Integer.parseInt(latestID.substring(3));
            if(max < n) {
                max = n;
            }
        }


         return "NCC"+String.valueOf(++max);
    }
    public ArrayList<NhaCungCap> searchByName(String tenNCC) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        for(NhaCungCap ncc : dsncc) {
            if (ncc.getTenNCC().toLowerCase().contains(tenNCC.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }

    public ArrayList<NhaCungCap> searchByID(String maNCC) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        for(NhaCungCap ncc : dsncc) {
            if (ncc.getMaNCC().toLowerCase().contains(maNCC.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }
    public ArrayList<NhaCungCap> search(String tenNCC, String maNCC) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        for(NhaCungCap ncc : dsncc) {
            if(ncc.getMaNCC().toLowerCase().contains(maNCC.toLowerCase()) && ncc.getTenNCC().toLowerCase().contains(tenNCC.toLowerCase())) {
                result.add(ncc);
            }
        }
        return result;
    }


}
