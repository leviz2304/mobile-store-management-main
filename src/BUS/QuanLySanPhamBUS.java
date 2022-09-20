package BUS;

import DAO.QuanLySanPhamDAO;
import DTO.KhachHang;
import DTO.NhaSanXuat;
import DTO.SanPham;

import java.util.ArrayList;

public class QuanLySanPhamBUS {
    private static ArrayList<SanPham> dssp = new ArrayList<>();
    private QuanLySanPhamDAO qlspDAO = new QuanLySanPhamDAO();

    public QuanLySanPhamBUS() {
        if(dssp.isEmpty()) {
            dssp = qlspDAO.readDB();
        }
    }
    public void setDssp() {
        if(dssp.isEmpty()) {
            dssp = qlspDAO.readDB();
        }
    }
    public int getGiaTienbyMaSP(String maSP) {
        for (SanPham sp : dssp) {
            if(sp.getMaSP().equals(maSP)) {
                return sp.getGiaTien();
            }
        }
        return 0;
    }
    public int getSoLuongbyMaSP(String maSP) {
        for (SanPham sp : dssp) {
            if(sp.getMaSP().equals(maSP)) {
                return sp.getSoLuong();
            }
        }
        return 0;
    }
    public String[] getHeader() {
        return new String[] {"Mã sản phẩm", "Tên sản phẩm", "Mã loại", "Mã nhà sản xuất", "Số lượng", "Giá tiền"};
    }
    public SanPham getSP(String maSP) {
        for(SanPham sp : dssp) {
            if(sp.getMaSP().equals(maSP))
                return sp;
        }
        return null;
    }
    public String getPName(String maSP) {
        for(SanPham sanPham: dssp) {
            if (sanPham.getMaSP().equals(maSP)) {
                return sanPham.getTenSP();
            }
        }
        return null;
    }
    public Boolean themSP(SanPham sp) {
        Boolean check = qlspDAO.add(sp);
        if(check) {
            dssp.add(sp);
            return true;
        }
        return false;
    }
    public Boolean updateSP(String masp, String tensp, String maloai, String mansx, int soluong, int giatien) {
        Boolean check = qlspDAO.update(masp, tensp, maloai, mansx, soluong, giatien);
        if(check) {
            dssp.forEach((sp) -> {
                if(sp.getMaSP().equals(masp)) {
                    sp.setTenSP(tensp);
                    sp.setMaLoaiSP(maloai);
                    sp.setMaNSX(mansx);
                    sp.setSoLuong(soluong);
                    sp.setGiaTien(giatien);
                }
            });
            return true;
        }
        return false;
    }
    public ArrayList<SanPham> getDssp() {
        return dssp;
    }

    public Boolean updateSoluong(String masp, int soluong) {
        Boolean check = qlspDAO.updateSoLuong(masp,soluong);
        if(check) {
            dssp.forEach((sp) -> {
                if(sp.getMaSP().equals(masp))
                sp.setSoLuong(soluong);
            });
            return true;
        }
        return false;
    }
    public Boolean xoaSP(String masp) {
        Boolean check = qlspDAO.delete(masp);
        if(check) {
            for (int i = (dssp.size() - 1); i >= 0; i--) {
                if (dssp.get(i).getMaSP().equals(masp)) {
                    dssp.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public String getLatestID() {
        int max=0;
        for(SanPham sp : dssp) {
            String  latestID = sp.getMaSP().trim();
            int n = Integer.parseInt(latestID.substring(2));
            if(max < n) {
                max = n;
            }
        }


        return "SP"+String.valueOf(++max);
    }
    public ArrayList<SanPham> getDSSPbyMaNSX(String nsx) {
        ArrayList<SanPham> result = new ArrayList<>();
        for(SanPham sp : dssp) {
            if(sp.getMaNSX().toLowerCase().equals(nsx.toLowerCase())) {
                result.add(sp);
            }
        }
        return result;
    }
    public ArrayList<SanPham> getDSSPbyMALSP(String maloai) {
        ArrayList<SanPham> result = new ArrayList<>();
        for(SanPham sp : dssp) {
            if(sp.getMaLoaiSP().toLowerCase().equals(maloai.toLowerCase())) {
                result.add(sp);
            }
        }
        return result;
    }
    public ArrayList<SanPham> searchByNSXName(ArrayList<NhaSanXuat> nsxList) {
        ArrayList<SanPham> result = new ArrayList<>();

        for(NhaSanXuat nsx : nsxList) {
            for(SanPham sp : dssp) {
                if(sp.getMaNSX().toLowerCase().equals(nsx.getMaNSX().toLowerCase())) {
                    result.add(sp);
                }
            }
        }

        return result;
    }
    public ArrayList<SanPham> searchByName(String name) {
        ArrayList<SanPham> result = new ArrayList<>();
        for(SanPham sp : dssp) {
            if(sp.getTenSP().toLowerCase().contains(name.toLowerCase())) {
                result.add(sp);
            }
        }
        return result;
    }
    public ArrayList<SanPham> searchByID(String ID) {
        ArrayList<SanPham> result = new ArrayList<>();
        for(SanPham sp : dssp) {
            if(sp.getMaSP().toLowerCase().contains(ID.toLowerCase())) {
                result.add(sp);
            }
        }
        return result;
    }






}
