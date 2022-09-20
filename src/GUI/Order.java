/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.*;
import DAO.QuanLyChiTietGiamGiaDAO;
import DTO.*;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gyn
 */
public class Order extends javax.swing.JFrame {

    private QuanLyHoaDonBUS qlhdBUS;
    private QuanLyChiTietHoaDonBus qlcthdBUS;
    private QuanLyNhanVienBUS qlnsvBUS;
    private QuanLyKhachHangBUS qlkhBUS;
    private QuanLyChuongTrinhGiamGiaBUS promoBUS;
    private QuanLySanPhamBUS qlspBUS;
    private QuanLyChiTietGiamGiaBUS qlctggBUS;
    /**
     * Creates new form Order
     */
    public Order() {
        initComponents();
        qlhdBUS = new QuanLyHoaDonBUS();
        qlcthdBUS = new QuanLyChiTietHoaDonBus();
        qlnsvBUS = new QuanLyNhanVienBUS();
        qlkhBUS = new QuanLyKhachHangBUS();
        promoBUS = new QuanLyChuongTrinhGiamGiaBUS();
        qlspBUS = new QuanLySanPhamBUS();
        qlctggBUS = new QuanLyChiTietGiamGiaBUS();
        loadModel();
        initComboNV();
        initComboKH();
        initComboMGG();
    }
    public void initComboNV() {
        jComboBox1.removeAllItems();
        for(NhanVien nv : qlnsvBUS.getDsnv()) {
            jComboBox1.addItem(nv.getMaNhanVien());
        }
    }
    public void initComboKH() {
        jComboBox2.removeAllItems();
        for(KhachHang khv : qlkhBUS.getDskh()) {
            jComboBox2.addItem(khv.getMaKhachHang());
        }
    }

    public void initComboMGG() {
        jComboBox3.removeAllItems();
        for(ChuongTrinhGiamGia ctgg : promoBUS.getDSCTGG()) {
            jComboBox3.addItem(ctgg.getMaGiamGia());
        }
    }
    public void loadModel(String option, String keyword) {
        DefaultTableModel model = new DefaultTableModel();
        if(model.getRowCount()==0) {
            model = new DefaultTableModel(qlhdBUS.getHeader(),0);
        }
        qlhdBUS.setDshd();
        for(HoaDon hd : qlhdBUS.search(option,keyword)) {
            Vector rowData = new Vector();
            rowData.add(hd.getMaHoaDon());
            rowData.add(hd.getMaKhachHang());
            rowData.add(hd.getMaNhanVien());
            rowData.add(hd.getMaGiamGia());
            rowData.add(hd.getNgayLap());
            rowData.add(hd.getTongTien());
            rowData.add(hd.getTongTienGiamGia());

            model.addRow(rowData);
        }
        jTextField1.setText(qlhdBUS.setMaHD());
        jTable1.setModel(model);
        jTextField5.setText(LocalDate.now().toString());

    }
    public void loadModelSearchByDate(LocalDate date1, LocalDate date2) {
        DefaultTableModel model = new DefaultTableModel();
        if(model.getRowCount()==0) {
            model = new DefaultTableModel(qlhdBUS.getHeader(),0);
        }
        qlhdBUS.setDshd();
        for(HoaDon hd : qlhdBUS.searchByDate(date1,date2)) {
            Vector rowData = new Vector();
            rowData.add(hd.getMaHoaDon());
            rowData.add(hd.getMaKhachHang());
            rowData.add(hd.getMaNhanVien());
            rowData.add(hd.getMaGiamGia());
            rowData.add(hd.getNgayLap());
            rowData.add(hd.getTongTien());
            rowData.add(hd.getTongTienGiamGia());

            model.addRow(rowData);
        }
        jTextField1.setText(qlhdBUS.setMaHD());
        jTable1.setModel(model);
        jTextField5.setText(LocalDate.now().toString());

    }

    public void loadModel() {
        DefaultTableModel model = new DefaultTableModel();
        if(model.getRowCount()==0) {
            model = new DefaultTableModel(qlhdBUS.getHeader(),0);
        }
        qlhdBUS.setDshd();
        for(HoaDon hd : qlhdBUS.getDshd()) {
            Vector rowData = new Vector();
            rowData.add(hd.getMaHoaDon());
            rowData.add(hd.getMaKhachHang());
            rowData.add(hd.getMaNhanVien());
            rowData.add(hd.getMaGiamGia());
            rowData.add(hd.getNgayLap());
            rowData.add(hd.getTongTien());
            rowData.add(hd.getTongTienGiamGia());

            model.addRow(rowData);
        }
        jTextField1.setText(qlhdBUS.setMaHD());
        jTable1.setModel(model);
        jTextField7.setText("0");
        jTextField6.setText("0");
        jTextField5.setText(LocalDate.now().toString());

    }
    public void loadDetail(String mahd) {
        DefaultTableModel detailModel = new DefaultTableModel();
        if(detailModel.getRowCount() == 0) {
            detailModel = new DefaultTableModel(qlcthdBUS.getHeader(),0);
        }
        ArrayList<ChiTietHoaDon> dsct = qlcthdBUS.getCTbyID(mahd);
        for(ChiTietHoaDon cthd : dsct) {
            Vector rowData = new Vector();
            rowData.add(cthd.getMaHoaDon());
            rowData.add(cthd.getMaSanPham());
            rowData.add(cthd.getSoLuong());
            rowData.add(cthd.getThanhTien());
            rowData.add(cthd.getChietKhau());

            detailModel.addRow(rowData);
        }
        jTable2.setModel(detailModel);
    }
    public int dayInMonth(int month, int year) {
        int result = 0;
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                result = 31;
                break;
            case 4: case 6: case 9: case 11:
                result =  30;
                break;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
                    result = 29;
                else result = 28;
                break;

        }
        return result;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnAdd1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        btnRefesh1 = new javax.swing.JButton();
        btnAdd2 = new javax.swing.JButton();
        btnDelete2 = new javax.swing.JButton();
        btnRefesh2 = new javax.swing.JButton();
        btnUpdate2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnAdd5 = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
        btnAdd3 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý hoá đơn");
        setBackground(new java.awt.Color(243, 243, 243));
        setResizable(false);

        jLabel1.setText("Thông tin chung hoá đơn");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã sản phẩm", "Số lượng", "Thành tiền", "Chiết khấu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setText("Thông tin chi tiết hoá đơn");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin phiếu mua"));

        jLabel4.setText("Mã hoá đơn:");

        jLabel5.setText("Mã khách hàng:");

        jLabel6.setText("Ngày lập:");

        jLabel7.setText("Tổng tiền:");

        jLabel8.setText("Tổng chiết khấu");

        jTextField1.setEditable(false);

        jLabel9.setText("Mã nhân viên:");

        jLabel10.setText("Mã giảm giá:");

        jTextField5.setEditable(false);

        jTextField6.setEditable(false);

        jTextField7.setEditable(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 27, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jTextField5)
                    .addComponent(jTextField6)
                    .addComponent(jTextField7)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_add_20px.png"))); // NOI18N
        btnAdd1.setText("Thêm");
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        btnDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_cancel_20px.png"))); // NOI18N
        btnDelete1.setText("Xoá");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        btnRefesh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_restart_20px_1.png"))); // NOI18N
        btnRefesh1.setText("Làm mới");
        btnRefesh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefesh1ActionPerformed(evt);
            }
        });

        btnAdd2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_add_20px.png"))); // NOI18N
        btnAdd2.setText("Thêm");
        btnAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd2ActionPerformed(evt);
            }
        });

        btnDelete2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_cancel_20px.png"))); // NOI18N
        btnDelete2.setText("Xoá");
        btnDelete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete2ActionPerformed(evt);
            }
        });

        btnRefesh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_restart_20px_1.png"))); // NOI18N
        btnRefesh2.setText("Làm mới");
        btnRefesh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefesh2ActionPerformed(evt);
            }
        });

        btnUpdate2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_maintenance_20px.png"))); // NOI18N
        btnUpdate2.setText("Sửa");
        btnUpdate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAdd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDelete1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRefesh1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
            .addComponent(btnAdd2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnUpdate2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDelete2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRefesh2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(btnAdd1)
                .addGap(18, 18, 18)
                .addComponent(btnDelete1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefesh1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd2)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate2)
                .addGap(18, 18, 18)
                .addComponent(btnDelete2)
                .addGap(18, 18, 18)
                .addComponent(btnRefesh2)
                .addGap(43, 43, 43))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết phiếu nhập"));

        jLabel16.setText("Chiết khấu:");

        jTextField12.setEditable(false);

        jTextField11.setEditable(false);
        jTextField11.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextField11PropertyChange(evt);
            }
        });

        jLabel15.setText("Thành tiền:");

        jLabel14.setText("Số lượng:");

        btnAdd5.setText("-");
        btnAdd5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd5ActionPerformed(evt);
            }
        });

        jTextField10.setEditable(false);

        btnAdd3.setText("+");
        btnAdd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd3ActionPerformed(evt);
            }
        });

        jLabel13.setText("Mã sản phẩm:");

        jLabel20.setText("Mã hoá đơn:");

        jTextField15.setEditable(false);
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAdd5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField10)
                        .addComponent(btnAdd3)
                        .addComponent(btnAdd5))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField11)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField12)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextField10.setText("1");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã hoá đơn", "Mã nhân viên", "Mã khách hàng", "Mã giảm giá" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_search_20px_1.png"))); // NOI18N
        jButton2.setText("Lọc");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc theo ngày"));

        jLabel3.setText("Từ:");

        jLabel11.setText("Đến:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023" }));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023" }));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_search_20px_1.png"))); // NOI18N
        jButton3.setText("Lọc");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBox7.setSelectedItem("2021");
        jComboBox8.setSelectedItem("2021");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_online_payment_with_a_credit_card_30px.png"))); // NOI18N
        jButton1.setText("Thanh toán");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Payment paymentFrame = new Payment();
        paymentFrame.setUpReceipt(jTextField1.getText().trim());
        paymentFrame.print();
        paymentFrame.setVisible(true);
        paymentFrame.pack();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        // TODO add your handling code here:
        String maHD = jTextField1.getText().trim();
        String maNV = jComboBox1.getSelectedItem().toString();
        String maKH = jComboBox2.getSelectedItem().toString();
        String maGG = jComboBox3.getSelectedItem().toString();
        LocalDate ngayLap = LocalDate.parse(jTextField5.getText());
        int tongTien=0;
        int chietKhau = Integer.parseInt(jTextField6.getText());
        HoaDon hd = new HoaDon(maHD,maKH,maNV,maGG,ngayLap,tongTien,chietKhau);
        System.out.println(hd.toString());
        if(qlhdBUS.themHD(hd)) {
           JOptionPane.showMessageDialog(null,"Thêm hóa đơn thành công");
           loadModel();
        }
        else {
            JOptionPane.showMessageDialog(null,"Thêm hóa đơn thất bại");

        }

    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        // TODO add your handling code here:
        String maHD = jTextField1.getText();
        if(qlhdBUS.xoaHD(maHD)) {
            JOptionPane.showMessageDialog(null,"Xóa thành công");
            loadModel();
            
        }
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void btnRefesh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefesh1ActionPerformed
        // TODO add your handling code here:
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);
        jTextField1.setText(qlhdBUS.setMaHD());
        jTextField5.setText(LocalDate.now().toString());
        jTextField7.setText("0");
        jTextField6.setText("0");
        loadModel();
        jComboBox3.setEnabled(true);
        
    }//GEN-LAST:event_btnRefesh1ActionPerformed

    private void btnAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd2ActionPerformed
        // TODO add your handling code here:
        String maHD = jTextField15.getText();
        String maSP = jTextField9.getText().trim();
        int soLuong = Integer.parseInt(jTextField10.getText());
        int thanhTien = Integer.parseInt(jTextField11.getText());
        int chietKhau = Integer.parseInt(jTextField12.getText());
        ChiTietHoaDon hd = new ChiTietHoaDon(maHD,maSP,soLuong,thanhTien,chietKhau);
        if(qlcthdBUS.themChiTiet(hd)) {
            JOptionPane.showMessageDialog(null,"Thêm sản phẩm vào hóa đơn thành công");
            if(qlcthdBUS.updateSoLuongKho(maSP,soLuong)) {
                JOptionPane.showMessageDialog(null, "Đã cập nhật lại kho.");
            }
            else {
                JOptionPane.showMessageDialog(null, "Cập nhật kho thất bại");
            }
            loadModel();
            loadDetail(jTextField1.getText());
            



        }
        else {
            JOptionPane.showMessageDialog(null,"Thêm thất bại");
        }
    }//GEN-LAST:event_btnAdd2ActionPerformed

    private void btnDelete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete2ActionPerformed
        // TODO add your handling code here:
        String maHD = jTextField15.getText();
        String maSp = jTextField9.getText();
        if(qlcthdBUS.xoaCT(maHD,maSp)) {
            JOptionPane.showMessageDialog(null, "Xóa sản phẩm khỏi hóa đơn thành công");
            loadModel();
            loadDetail(maHD);

        }
        else {
            JOptionPane.showMessageDialog(null,"Xóa thất bại");
        }
    }//GEN-LAST:event_btnDelete2ActionPerformed

    private void btnRefesh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefesh2ActionPerformed
        // TODO add your handling code here:
        jTextField15.setText(jTextField1.getText());
        jTextField9.setText("");
        jTextField9.setEditable(true);
        jTextField6.setText("0");
        jTextField10.setText("1");
        jTextField11.setText("");
        jTextField12.setText("");

    }//GEN-LAST:event_btnRefesh2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int i = jTable1.getSelectedRow();
        jTextField1.setText(jTable1.getModel().getValueAt(i,0).toString());
        jComboBox2.setSelectedItem(jTable1.getModel().getValueAt(i,1).toString());
        jComboBox2.setEnabled(false);
        jComboBox1.setSelectedItem(jTable1.getModel().getValueAt(i,2).toString());
        jComboBox1.setEnabled(false);
        jComboBox3.setSelectedItem(jTable1.getModel().getValueAt(i, 3).toString());
        jTextField5.setText(jTable1.getModel().getValueAt(i,4).toString());
        jTextField6.setText(jTable1.getModel().getValueAt(i,6).toString());
        jTextField7.setText(jTable1.getModel().getValueAt(i,5).toString());

        loadDetail(jTextField1.getText());

        jTextField15.setText(jTextField1.getText());
        jTextField9.setText("");
        jTextField9.setEditable(true);
        jTextField11.setText("");
        jTextField12.setText("");
        jComboBox3.setEnabled(false);




    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int i = jTable2.getSelectedRow();
        jTextField15.setText(jTable2.getModel().getValueAt(i,0).toString());
        jTextField9.setText(jTable2.getModel().getValueAt(i,1).toString());
        jTextField10.setText(jTable2.getModel().getValueAt(i,2).toString());
        jTextField11.setText(jTable2.getModel().getValueAt(i,3).toString());
        jTextField12.setText(jTable2.getModel().getValueAt(i,4).toString());

        jTextField9.setEditable(false);


    }//GEN-LAST:event_jTable2MouseClicked

    private void btnUpdate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate2ActionPerformed
        try {
            // TODO add your handling code here:
            String mahd = jTextField15.getText();
            String masp = jTextField9.getText();
            int soLuong = Integer.parseInt(jTextField10.getText());
            
            Boolean check = qlcthdBUS.updateQty(mahd,masp,soLuong);
            if(check) {
                JOptionPane.showMessageDialog(null,"Cập nhật thành công");
                loadDetail(mahd);
            }
            else {
                JOptionPane.showMessageDialog(null,"Cập nhật thất bại");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdate2ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jTextField11PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextField11PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11PropertyChange

    private void btnAdd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd3ActionPerformed
        // TODO add your handling code here:
        String maGG = jComboBox3.getSelectedItem().toString();
        String masp = jTextField9.getText();
        int soLuong = Integer.parseInt(jTextField10.getText());
        int checkSL = qlspBUS.getSoLuongbyMaSP(masp);
        if(soLuong == 0) {
            JOptionPane.showMessageDialog(null, "Trong kho không còn sản phẩm này");
        }
        else if(soLuong < checkSL) {
            soLuong++;
            jTextField10.setText(String.valueOf(soLuong));
            int GiaTien = qlspBUS.getGiaTienbyMaSP(masp);
            GiaTien = GiaTien * soLuong;
            jTextField11.setText(String.valueOf(GiaTien));

            int ChietKhau = qlctggBUS.getChietKhau(maGG,masp);
            ChietKhau *= soLuong;
            jTextField12.setText(String.valueOf(ChietKhau));

        }
        else {
            JOptionPane.showMessageDialog(null,"Số lượng sản phẩm tối đa");
        }

    }//GEN-LAST:event_btnAdd3ActionPerformed

    private void btnAdd5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd5ActionPerformed
        // TODO add your handling code here:
        String maGG = jComboBox3.getSelectedItem().toString();
        int soLuong = Integer.parseInt(jTextField10.getText());
        if(soLuong > 1) {
            soLuong--;
            jTextField10.setText(String.valueOf(soLuong));
            String masp = jTextField9.getText();
            int GiaTien = qlspBUS.getGiaTienbyMaSP(masp);
            GiaTien = GiaTien * soLuong;
            jTextField11.setText(String.valueOf(GiaTien));

            int ChietKhau = qlctggBUS.getChietKhau(maGG,masp);
            ChietKhau *= soLuong;
            jTextField12.setText(String.valueOf(ChietKhau));
        }
    }//GEN-LAST:event_btnAdd5ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String option = jComboBox4.getSelectedItem().toString();
        String keyword = jTextField2.getText().trim();
        loadModel(option,keyword);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String dDate1 = jComboBox5.getSelectedItem().toString();
        String mDate1 = jComboBox6.getSelectedItem().toString();
        String yDate1 = jComboBox7.getSelectedItem().toString();
        String dDate2 = jComboBox10.getSelectedItem().toString();
        String mDate2 = jComboBox9.getSelectedItem().toString();
        String yDate2 = jComboBox8.getSelectedItem().toString();

        String date = yDate1 +"-" + mDate1 + "-" + dDate1;
        System.out.println(date);
        LocalDate date1 = LocalDate.parse(date);
        date = yDate2 + "-" + mDate2 + "-" + dDate2;
        System.out.println(date);
        LocalDate date2 = LocalDate.parse(date);

        loadModelSearchByDate(date1,date2);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
        int month = Integer.parseInt(jComboBox6.getSelectedItem().toString());
        int year = Integer.parseInt(jComboBox7.getSelectedItem().toString());
        int dayCount = dayInMonth(month,year);
        jComboBox5.removeAllItems();
        for(int i = 1; i<10;i++) {
            String day = "0" + String.valueOf(i);
            jComboBox5.addItem(day);
        }
        for(int i=10; i<=dayCount;i++) {
            jComboBox5.addItem(String.valueOf(i));
        }
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed
        // TODO add your handling code here:
        int month = Integer.parseInt(jComboBox9.getSelectedItem().toString());
        int year = Integer.parseInt(jComboBox8.getSelectedItem().toString());
        int dayCount = dayInMonth(month,year);
        jComboBox10.removeAllItems();
        for(int i = 1; i<10;i++) {
            String day = "0" + String.valueOf(i);
            jComboBox10.addItem(day);
        }
        for(int i=10; i<=dayCount;i++) {
            jComboBox10.addItem(String.valueOf(i));
        }
    }//GEN-LAST:event_jComboBox9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
           UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch (Exception e) {
            System.out.println(e);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Order frame = new Order();
                Menu menuFrame = new Menu();
                if(menuFrame.checkLogin()) {
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);

                    menuFrame.setVisible(false);
                    menuFrame.dispose();
                } else {
                    frame.dispose();
                    menuFrame.setVisible(false);
                    menuFrame.dispose();
                    Login login = new Login();
                    login.setLocationRelativeTo(null);

                    login.setVisible(true);
                    login.pack();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnAdd2;
    private javax.swing.JButton btnAdd3;
    private javax.swing.JButton btnAdd5;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnDelete2;
    private javax.swing.JButton btnRefesh1;
    private javax.swing.JButton btnRefesh2;
    private javax.swing.JButton btnUpdate2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

}
