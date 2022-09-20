/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhaSanXuat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class QuanLyNhaSanXuatDAO {
    ConnectionDB qlnsxConnection;
    
    public QuanLyNhaSanXuatDAO() {

    }

    public ArrayList<NhaSanXuat> readDB() {
        qlnsxConnection = new ConnectionDB();
        ArrayList<NhaSanXuat> dsnsx = new ArrayList<>();
        try {
            String qry = "SELECT * FROM nhasanxuat";
            ResultSet r = qlnsxConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String mansx = r.getString("MANSX");
                    String tennsx = r.getString("TENNSX");

                    dsnsx.add(new NhaSanXuat(mansx, tennsx));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng loại sản phẩm");
        } finally {
            qlnsxConnection.closeConnect();
        }
        return dsnsx;
    }

    public ArrayList<NhaSanXuat> search(String columnName, String value) {
        qlnsxConnection = new ConnectionDB();
        ArrayList<NhaSanXuat> dsnsx = new ArrayList<>();

        try {
            String qry = "SELECT * FROM nhasanxuat WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qlnsxConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String mansx = r.getString("MANSX");
                    String tennsx = r.getString("TENNSX");

                    dsnsx.add(new NhaSanXuat(mansx, tennsx));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng nhà sản xuất");
        } finally {
            qlnsxConnection.closeConnect();
        }

        return dsnsx;
    }

    public Boolean add(NhaSanXuat nsx) {
        qlnsxConnection = new ConnectionDB();
        Boolean ok = qlnsxConnection.sqlUpdate("INSERT INTO `nhasanxuat` (`MANSX`, `TENNSX`) VALUES ('"
                + nsx.getMaNSX()+ "', '" + nsx.getTenNSX()+ "');");
        qlnsxConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String mansx) {
        qlnsxConnection = new ConnectionDB();
        Boolean ok = qlnsxConnection.sqlUpdate("DELETE FROM `nhasanxuat` WHERE `nhasanxuat`.`MANSX` = '" + mansx + "'");
        qlnsxConnection.closeConnect();
        return ok;
    }

    public Boolean update(String mansx, String tennsx) {
        qlnsxConnection = new ConnectionDB();
        Boolean ok = qlnsxConnection.sqlUpdate("UPDATE `nhasanxuat` SET TENNSX='" + tennsx + "' where MANSX='" + mansx + "'");
        qlnsxConnection.closeConnect();
        return ok;
    }

    public void close() {
        qlnsxConnection.closeConnect();
    }

    
}
