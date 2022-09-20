/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.QuanLyChiTietGiamGiaBUS;
import BUS.QuanLyChuongTrinhGiamGiaBUS;
import DTO.ChiTietGiamGia;
import DTO.ChuongTrinhGiamGia;
import com.formdev.flatlaf.FlatLightLaf;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gyn
 */
public class Coupon extends javax.swing.JFrame {

    QuanLyChuongTrinhGiamGiaBUS qlctggBUS;
    QuanLyChiTietGiamGiaBUS qlchitietggBUS;

    /**
     * Creates new form Coupon
     */
    public Coupon() {
        initComponents();
        qlctggBUS = new QuanLyChuongTrinhGiamGiaBUS();
        qlchitietggBUS = new QuanLyChiTietGiamGiaBUS();
        loadModelChuongTrinh();
        tfMaGiamGia.setText(qlctggBUS.getLatestID());
        loadModelChiTiet();
    }

    public void loadModelChuongTrinh() {
        DefaultTableModel model = new DefaultTableModel();
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(qlctggBUS.getHeaders(), 0);
        }
        qlctggBUS.readDB();
        for (ChuongTrinhGiamGia ctgg : qlctggBUS.getDSCTGG()) {
            Vector rowData = new Vector();
            rowData.add(ctgg.getMaGiamGia());
            rowData.add(ctgg.getTenChuongTrinh());
            rowData.add(ctgg.getNgayBatDau());
            rowData.add(ctgg.getNgayKetThuc());

            model.addRow(rowData);
        }
        jTableChuongTrinh.setModel(model);
    }

    public void loadModelChiTiet(String maCT) {
        DefaultTableModel model = new DefaultTableModel();
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(qlchitietggBUS.getHeaders(), 0);
        }
        qlchitietggBUS.readDB();
        for (ChiTietGiamGia chitietgg : qlchitietggBUS.getChiTietGGbyID(maCT)) {
            Vector rowData = new Vector();
            rowData.add(chitietgg.getMaGiamGia());
            rowData.add(chitietgg.getMaSanPham());
            rowData.add(chitietgg.getChietKhau());

            model.addRow(rowData);
        }
        jTableChiTiet.setModel(model);
    }

    public void loadModelChiTiet() {
        DefaultTableModel model = new DefaultTableModel();
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(qlchitietggBUS.getHeaders(), 0);
        }
        qlchitietggBUS.readDB();
        for (ChiTietGiamGia chitietgg : qlchitietggBUS.getDSChiTietGG()) {
            Vector rowData = new Vector();
            rowData.add(chitietgg.getMaGiamGia());
            rowData.add(chitietgg.getMaSanPham());
            rowData.add(chitietgg.getChietKhau());

            model.addRow(rowData);
        }
        jTableChiTiet.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableChuongTrinh = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableChiTiet = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfTenChuongTrinh = new javax.swing.JTextField();
        tfMaGiamGia = new javax.swing.JTextField();
        tfNgayBatDau = new javax.swing.JTextField();
        tfNgayKetThuc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tfMaGiamGia2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfChietKhau = new javax.swing.JTextField();
        tfMaSanPham = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnDelete1 = new javax.swing.JButton();
        btnAdd1 = new javax.swing.JButton();
        btnRefesh1 = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnUpdate2 = new javax.swing.JButton();
        btnRefesh2 = new javax.swing.JButton();
        btnAdd2 = new javax.swing.JButton();
        btnDelete2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý giảm giá");
        setBackground(new java.awt.Color(243, 243, 243));
        setResizable(false);

        jTableChuongTrinh.setAutoCreateRowSorter(true);
        jTableChuongTrinh.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTableChuongTrinh.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableChuongTrinh.setShowHorizontalLines(true);
        jTableChuongTrinh.setShowVerticalLines(true);
        jTableChuongTrinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableChuongTrinhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableChuongTrinh);

        jTableChiTiet.setAutoCreateRowSorter(true);
        jTableChiTiet.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableChiTiet.setShowHorizontalLines(true);
        jTableChiTiet.setShowVerticalLines(true);
        jTableChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableChiTietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableChiTiet);

        jLabel1.setText("Chương trình giảm giá");

        jLabel2.setText("Chi tiết chương trình giảm giá");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Chương trình giảm giá"));

        jLabel3.setText("Mã giảm giá:");

        jLabel4.setText("Tên chương trình:");

        jLabel5.setText("Ngày bắt đầu:");

        jLabel6.setText("Ngày kết thúc:");

        tfTenChuongTrinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTenChuongTrinhActionPerformed(evt);
            }
        });

        tfMaGiamGia.setEditable(false);

        tfNgayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNgayBatDauActionPerformed(evt);
            }
        });

        tfNgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNgayKetThucActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Lưu ý: Ngày tháng năm được định dạng theo yyyy-MM-dd || VD: 2021-05-15");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfNgayBatDau, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfTenChuongTrinh, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfMaGiamGia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(tfTenChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(tfNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(tfNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết chương trình giảm giá"));

        tfMaGiamGia2.setEditable(false);
        tfMaGiamGia2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMaGiamGia2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Mã giảm giá:");

        jLabel9.setText("Mã sản phẩm:");

        jLabel10.setText("Chiết khấu:");

        tfMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMaSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(tfMaGiamGia2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfMaSanPham)
                            .addComponent(tfChietKhau))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfMaGiamGia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_cancel_20px.png"))); // NOI18N
        btnDelete1.setText("Xoá");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        btnAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_add_20px.png"))); // NOI18N
        btnAdd1.setText("Thêm");
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        btnRefesh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_restart_20px_1.png"))); // NOI18N
        btnRefesh1.setText("Làm mới");
        btnRefesh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefesh1ActionPerformed(evt);
            }
        });

        btnUpdate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_maintenance_20px.png"))); // NOI18N
        btnUpdate1.setText("Sửa");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btnUpdate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(67, 67, 67)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefesh1)
                    .addComponent(btnDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete1)
                    .addComponent(btnAdd1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefesh1)
                    .addComponent(btnUpdate1))
                .addGap(14, 14, 14))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnUpdate2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_maintenance_20px.png"))); // NOI18N
        btnUpdate2.setText("Sửa");
        btnUpdate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate2ActionPerformed(evt);
            }
        });

        btnRefesh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/icons8_restart_20px_1.png"))); // NOI18N
        btnRefesh2.setText("Làm mới");
        btnRefesh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefesh2ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btnUpdate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelete2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefesh2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd2)
                    .addComponent(btnDelete2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefesh2)
                    .addComponent(btnUpdate2))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfTenChuongTrinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTenChuongTrinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTenChuongTrinhActionPerformed

    private void tfNgayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNgayBatDauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNgayBatDauActionPerformed

    private void tfNgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNgayKetThucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNgayKetThucActionPerformed

    private void tfMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMaSanPhamActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:
        String maGiamGia = tfMaGiamGia.getText().trim();
        String tenChuongTrinh = tfTenChuongTrinh.getText().trim();
        LocalDate ngayBatDau = LocalDate.parse(tfNgayKetThuc.getText());
        LocalDate ngayKetThuc = LocalDate.parse(tfNgayKetThuc.getText());
        Boolean check = qlctggBUS.updateCTGG(maGiamGia, tenChuongTrinh, ngayBatDau, ngayKetThuc);
        if (check) {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin chương trình giảm giá thành công");
            loadModelChuongTrinh();
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin chương trình giảm giá thất bại");

        }
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        // TODO add your handling code here:
        String maGiamGia = tfMaGiamGia.getText().trim();
        if (qlctggBUS.XoaChuongTrinhGiamGia(maGiamGia)) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            tfTenChuongTrinh.setText("");
            tfNgayBatDau.setText("");
            tfNgayKetThuc.setText("");
            tfMaGiamGia.setText(qlctggBUS.getLatestID());
            loadModelChuongTrinh();
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        // TODO add your handling code here:
        String maGiamGia = tfMaGiamGia.getText().trim();
        String tenChuongTrinh = tfTenChuongTrinh.getText().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ngayBatDau = LocalDate.parse(tfNgayBatDau.getText(),formatter);
        LocalDate ngayKetThuc = LocalDate.parse(tfNgayKetThuc.getText(),formatter);
        ChuongTrinhGiamGia ctgg = new ChuongTrinhGiamGia(maGiamGia, tenChuongTrinh, ngayBatDau, ngayKetThuc);
        if (qlctggBUS.themChuongTrinhGiamGia(ctgg)) {
            JOptionPane.showMessageDialog(null, "Thêm chương trình giảm giá thành công");
            loadModelChuongTrinh();
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void btnRefesh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefesh1ActionPerformed
        // TODO add your handling code here:
        tfMaGiamGia.setText(qlctggBUS.getLatestID());
        tfTenChuongTrinh.setText("");
        tfNgayBatDau.setText("");
        tfNgayKetThuc.setText("");
        loadModelChuongTrinh();
    }//GEN-LAST:event_btnRefesh1ActionPerformed

    private void btnUpdate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate2ActionPerformed
        // TODO add your handling code here:
        String maGiamGia2 = tfMaGiamGia2.getText().trim();
        String maSanPham = tfMaSanPham.getText().trim();
        int chietKhau = Integer.parseInt(tfChietKhau.getText().trim());
        Boolean check = qlchitietggBUS.updateCTGG(maGiamGia2, maSanPham, chietKhau);
        if (check) {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin chi tiết giảm giá thành công");
            loadModelChiTiet(maGiamGia2);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin chi tiết giảm giá thất bại");

        }
    }//GEN-LAST:event_btnUpdate2ActionPerformed

    private void btnAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd2ActionPerformed
        // TODO add your handling code here:
        String maGiamGia2 = tfMaGiamGia2.getText().trim();
        String maSanPham = tfMaSanPham.getText().trim();
        int chietKhau = Integer.parseInt(tfChietKhau.getText());
        ChiTietGiamGia ctgg = new ChiTietGiamGia(maGiamGia2, maSanPham, chietKhau);
        if (qlchitietggBUS.themChiTietGiamGia(ctgg)) {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm vào chương trình giảm giá thành công");
            loadModelChiTiet(maGiamGia2);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnAdd2ActionPerformed

    private void btnRefesh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefesh2ActionPerformed
        // TODO add your handling code here:
        tfMaSanPham.setText("");
        tfChietKhau.setText("");
        loadModelChiTiet();
    }//GEN-LAST:event_btnRefesh2ActionPerformed

    private void btnDelete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete2ActionPerformed
        // TODO add your handling code here:
        String maGiamGia2 = tfMaGiamGia2.getText().trim();
        String maSanPham = tfMaSanPham.getText().trim();
        if (qlchitietggBUS.XoaChiTietGiamGia(maGiamGia2,maSanPham)) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            loadModelChiTiet(maGiamGia2);
            tfMaSanPham.setText("");
            tfChietKhau.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnDelete2ActionPerformed

    private void jTableChuongTrinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableChuongTrinhMouseClicked
        // TODO add your handling code here:
        int i = jTableChuongTrinh.getSelectedRow();
        tfMaGiamGia.setText(jTableChuongTrinh.getModel().getValueAt(i, 0).toString());
        tfTenChuongTrinh.setText(jTableChuongTrinh.getModel().getValueAt(i, 1).toString());
        tfNgayBatDau.setText(jTableChuongTrinh.getModel().getValueAt(i, 2).toString());
        tfNgayKetThuc.setText(jTableChuongTrinh.getModel().getValueAt(i, 3).toString());
        tfMaGiamGia2.setText(jTableChuongTrinh.getModel().getValueAt(i, 0).toString());

        loadModelChiTiet(jTableChuongTrinh.getModel().getValueAt(i,0).toString());



    }//GEN-LAST:event_jTableChuongTrinhMouseClicked

    private void jTableChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableChiTietMouseClicked
        // TODO add your handling code here:
        int i = jTableChiTiet.getSelectedRow();
        tfMaGiamGia2.setText(jTableChiTiet.getModel().getValueAt(i, 0).toString());
        tfMaSanPham.setText(jTableChiTiet.getModel().getValueAt(i, 1).toString());
        tfChietKhau.setText(jTableChiTiet.getModel().getValueAt(i, 2).toString());
    }//GEN-LAST:event_jTableChiTietMouseClicked

    private void tfMaGiamGia2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMaGiamGia2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMaGiamGia2ActionPerformed

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
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println(e);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Coupon frame = new Coupon();
                Menu menuFrame = new Menu();
                if(menuFrame.checkLogin()) {
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
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
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnDelete2;
    private javax.swing.JButton btnRefesh1;
    private javax.swing.JButton btnRefesh2;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JButton btnUpdate2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTable jTableChiTiet;
    private javax.swing.JTable jTableChuongTrinh;
    private javax.swing.JTextField tfChietKhau;
    private javax.swing.JTextField tfMaGiamGia;
    private javax.swing.JTextField tfMaGiamGia2;
    private javax.swing.JTextField tfMaSanPham;
    private javax.swing.JTextField tfNgayBatDau;
    private javax.swing.JTextField tfNgayKetThuc;
    private javax.swing.JTextField tfTenChuongTrinh;
    // End of variables declaration//GEN-END:variables
}
