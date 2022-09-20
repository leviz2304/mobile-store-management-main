/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.QuanLyLoaiSanPhamDAO;
import DTO.LoaiSanPham;
import java.util.ArrayList;

public class QuanLyLoaiSanPhamBUS {
    
    private static ArrayList<LoaiSanPham> dslsp = new ArrayList<>();
    private QuanLyLoaiSanPhamDAO qllspDAO = new QuanLyLoaiSanPhamDAO();
    
    public QuanLyLoaiSanPhamBUS() {
        if(dslsp.isEmpty()) {
            dslsp = qllspDAO.readDB();
        }
    }
    
    public String[] getHeaders() {
        return new String[]{"Mã Loại", "Tên Loại"};
    }
    
    public void readDB() {
        if(dslsp.isEmpty()) {
            dslsp = qllspDAO.readDB();
        }
    }
    
    public LoaiSanPham getLoaiSanPham(String malsp) {
        for(LoaiSanPham lsp : dslsp) {
            if(lsp.getMaLoai().equals(malsp))
                return lsp;
        }
        return null;
    }
    
    public Boolean themLoaiSanPham(LoaiSanPham lsp) {
        Boolean check = qllspDAO.add(lsp);

        if(check) {
            dslsp.add(lsp);
        }
        return check;
    }

    public Boolean themLoaiSanPham(String maLSP, String tenLSP) {
        LoaiSanPham lsp = new LoaiSanPham(maLSP,tenLSP);
        return themLoaiSanPham(lsp);
    }
    
    public Boolean xoaLoaiSanPham(String malsp) {
        Boolean check = qllspDAO.delete(malsp);

        if(check) {
            for (int i = (dslsp.size() - 1); i >= 0; i--) {
                if (dslsp.get(i).getMaLoai().equals(malsp)) {
                    dslsp.remove(i);
                }
            }
        }
        return check;
    }
    
    public Boolean updateLSP(String maLSP, String tenLSP) {
        Boolean check = qllspDAO.update(maLSP, tenLSP);

        if(check) {
            dslsp.forEach((lsp) -> {
                if(lsp.getMaLoai().equals(maLSP)) {
                    lsp.setTenLoai(tenLSP);
                }
            });
        }

        return  check;

    }
    
    public ArrayList<LoaiSanPham> getDslsp() {
        return dslsp;
    }

    public String getLatestID() {
        int max=0;
        for(LoaiSanPham lsp : dslsp) {
            String  latestID = lsp.getMaLoai();
            int n = Integer.parseInt(latestID.substring(3));
            if(max < n) {
                max = n;
            }
        }


         return "LSP"+String.valueOf(++max);
    }


}
