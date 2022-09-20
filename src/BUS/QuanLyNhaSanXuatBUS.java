/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.QuanLyNhaSanXuatDAO;
import java.util.ArrayList;
import DTO.NhaSanXuat;
import DTO.SanPham;

/**
 *
 * @author Gyn
 */
public class QuanLyNhaSanXuatBUS {

    private static ArrayList<NhaSanXuat> dsnsx = new ArrayList<>();
    private QuanLyNhaSanXuatDAO qlnsxDAO = new QuanLyNhaSanXuatDAO();
    private QuanLySanPhamBUS qlspBUS = new QuanLySanPhamBUS();

    public QuanLyNhaSanXuatBUS() {
        if (dsnsx.isEmpty()) {
            dsnsx = qlnsxDAO.readDB();
        }
    }
    public void readDB(){
        if(dsnsx.isEmpty()){
            dsnsx = qlnsxDAO.readDB();
        }
    }
    //Set name of column on table
    public String[] getHeaders() {
        return new String[]{"Mã nhà sản xuất", "Tên nhà sản xuất"};
    }
    public ArrayList<NhaSanXuat> getDSNhaSanXuat(){
        return dsnsx;
    }
    public NhaSanXuat getNhaSanXuat(String mansx) {
        for (NhaSanXuat nsx : dsnsx) {
            if (nsx.getMaNSX().equals(mansx)) {
                return nsx;
            }
        }
        return null;
    }

    public Boolean themNhaSanXuat(NhaSanXuat nsx) {
        Boolean check = qlnsxDAO.add(nsx);
        if (check) {
            dsnsx.add(nsx);
        }
        return check;
    }

    public Boolean themNhaSanXuat(String maNSX, String tenNSX) {
        NhaSanXuat nsx = new NhaSanXuat(maNSX, tenNSX);
        return themNhaSanXuat(nsx);
    }

    public Boolean xoaNhaSanXuat(String maNSX) {
        Boolean check = qlnsxDAO.delete(maNSX);
        if (check) {
            for (NhaSanXuat nsx : dsnsx) {
                if (nsx.getMaNSX().equals(maNSX)) {
                    dsnsx.remove(dsnsx.indexOf(nsx));
                }
            }
        }
        return check;
    }

    public Boolean updateNSX(String maNSX, String tenNSX) {
        Boolean check = qlnsxDAO.update(maNSX, tenNSX);
        if (check) {
            dsnsx.forEach((nsx) -> {
                if (nsx.getMaNSX().equals(maNSX)) {
                    nsx.setTenNSX(tenNSX);
                }
            });
        }
        return check;
    }
    public String getLatestID() {
        int max=0;
        for(NhaSanXuat nsx : dsnsx) {
            String  latestID = nsx.getMaNSX();
            int n = Integer.parseInt(latestID.substring(3));
            if(max < n) {
                max = n;
            }
        }


        return "NSX"+String.valueOf(++max);
    }
    public ArrayList<NhaSanXuat> getIDbyName(String id) {
        ArrayList<NhaSanXuat> result = new ArrayList<>();
        for(NhaSanXuat nsx : dsnsx) {
            if(nsx.getTenNSX().contains(id)) {
                result.add(nsx);
            }
        }
        return result;
    }



}
