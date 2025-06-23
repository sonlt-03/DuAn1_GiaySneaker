
package Views;


import Dao.PhieuGiamGiaDAO;
import Model.PhieuGiamGia;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;


public class Menu_KhuyenMai extends javax.swing.JInternalFrame {
    private PhieuGiamGiaDAO phieuGiamGiaDAO;
    private DefaultTableModel defaultTableModel;
    private int selectedId = -1;
   
    public Menu_KhuyenMai() {
        initComponents();
        phieuGiamGiaDAO = new PhieuGiamGiaDAO();
        defaultTableModel = new DefaultTableModel();

        tblKM.setModel(defaultTableModel);
        defaultTableModel.addColumn("idPhieuGiamGia");
        defaultTableModel.addColumn("maPhieu");
        defaultTableModel.addColumn("tenPhieu");
        defaultTableModel.addColumn("tenHinhThucGG");
        defaultTableModel.addColumn("giaTri");
        defaultTableModel.addColumn("soLuong");
        defaultTableModel.addColumn("ngayBatDau");
        defaultTableModel.addColumn("ngayKetThuc");
        defaultTableModel.addColumn("dieuKienApDung");
        defaultTableModel.addColumn("trangThai");

        fillTable(phieuGiamGiaDAO.getAll());

        
        
    }
    
    private boolean validateForm() {
    if (txtMaPhieu.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phiếu!");
        txtMaPhieu.requestFocus();
        return false;
    }

    if (txtTenPhieu.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập tên chương trình!");
        txtTenPhieu.requestFocus();
        return false;
    }

    if (txtHinhThucGG.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập hình thức giảm giá!");
        txtHinhThucGG.requestFocus();
        return false;
    }

    try {
        BigDecimal giaTri = new BigDecimal(txtGiaTri.getText().trim());
        if (giaTri.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm giá phải lớn hơn 0!");
            txtGiaTri.requestFocus();
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Giá trị giảm giá không hợp lệ!");
        txtGiaTri.requestFocus();
        return false;
    }

    try {
        int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
            txtSoLuong.requestFocus();
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
        txtSoLuong.requestFocus();
        return false;
    }

    Date startDate = dtBatDau.getDate();
    Date endDate = dtKetThuc.getDate();
    if (startDate == null || endDate == null) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu và ngày kết thúc!");
        return false;
    }
    if (startDate.after(endDate)) {
        JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được sau ngày kết thúc!");
        return false;
    }
    
    if (phieuGiamGiaDAO.isMaPhieuExists(txtMaPhieu.getText().trim()) && selectedId == -1) {
    JOptionPane.showMessageDialog(this, "Mã phiếu đã tồn tại!");
    txtMaPhieu.requestFocus();
    return false;
}


    return true;
}

    
    private void fillTable(List<PhieuGiamGia> list) {
        defaultTableModel.setRowCount(0);
        for (PhieuGiamGia p : list) {
            String trangThaiStr = (p.getTrangThai() != null && p.getTrangThai()) ? "Đang hoạt động" : "Ngừng hoạt động";
            defaultTableModel.addRow(new Object[]{
                p.getIdPhieuGiamGia(),
                p.getMaPhieu(),
                p.getTenPhieu(),
                p.getTenHinhThucGG(),
                p.getGiaTri(),
                p.getSoLuong(),
                p.getNgayBatDau(),
                p.getNgayKetThuc(),
                p.getDieuKienApDung(),
                trangThaiStr
            });
        }
    }
private PhieuGiamGia readFromForm() {
    String maPhieu = txtMaPhieu.getText();
    String tenPhieu = txtTenPhieu.getText();
    String tenHinhThucGG = txtHinhThucGG.getText();

    BigDecimal giaTri = BigDecimal.ZERO;
    try {
        giaTri = new BigDecimal(txtGiaTri.getText());
    } catch (NumberFormatException e) {
        // xử lý nếu nhập không đúng định dạng số
    }

    int soLuong = 0;
    try {
        soLuong = Integer.parseInt(txtSoLuong.getText());
    } catch (NumberFormatException e) {
        // xử lý nếu nhập không đúng định dạng số
    }

    // JDateChooser trả về java.util.Date rồi, nên không cần convert sang LocalDate
    Date ngayBatDau = dtBatDau.getDate();
    Date ngayKetThuc = dtKetThuc.getDate();

    String dieuKienApDung = txtDK.getText();

    boolean trangThai = rdoHD.isSelected();

    return new PhieuGiamGia(
        selectedId,
        maPhieu,
        tenPhieu,
        tenHinhThucGG,
        giaTri,
        soLuong,
        ngayBatDau,
        ngayKetThuc,
        dieuKienApDung,
        trangThai
    );
}

private void clearForm() {
    selectedId = -1;
    txtMaPhieu.setText("");
    txtTenPhieu.setText("");
    txtHinhThucGG.setText("");
    txtGiaTri.setText("");
    txtSoLuong.setText("");
    dtBatDau.setDate(new Date());
    dtKetThuc.setDate(new Date());
    txtDK.setText("");
    rdoHD.setSelected(true);
    // Nếu có radio không hoạt động thì có thể set ngược lại nếu muốn
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKM = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenPhieu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGiaTri = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rdoHD = new javax.swing.JRadioButton();
        rdoKHD = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        dtBatDau = new com.toedter.calendar.JDateChooser();
        dtKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txtHinhThucGG = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDK = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText("Danh sách khuyến mại");

        tblKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "Mã phiếu", "Tên chương trình", "Hình thức giảm giá", "Giá trị giảm giá", "Số lượng", "Ngày bắt đầu", "Ngày kết thúc", "Điều kiện áp dụng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKM);
        if (tblKM.getColumnModel().getColumnCount() > 0) {
            tblKM.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setText("Phiếu giảm giá");

        jLabel6.setText("Tên chương trình");

        jLabel7.setText("Giá trị giảm giá");

        jLabel8.setText("Thời gian bắt đầu");

        jLabel9.setText("Thời gian kết thúc");

        jLabel10.setText("Trạng thái");

        buttonGroup1.add(rdoHD);
        rdoHD.setText("Đang hoạt động");
        rdoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHDActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoKHD);
        rdoKHD.setText("Ngừng hoạt động");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel11.setText("Hình thức giảm giá");

        jLabel12.setText("Số lượng");

        jLabel13.setText("Điều kiện áp dụng");

        jLabel1.setText("Mã phiếu ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel9)
                                    .addGap(192, 192, 192))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addGap(26, 26, 26)
                                            .addComponent(txtGiaTri))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtHinhThucGG, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoKHD)
                                    .addComponent(rdoHD)))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtDK, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dtKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dtBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(txtMaPhieu)))
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtTenPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel11))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHinhThucGG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(txtGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(dtBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(dtKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(rdoHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoKHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoHDActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
    if (!validateForm()) return;

    phieuGiamGiaDAO.create(readFromForm());
    clearForm();
    fillTable(phieuGiamGiaDAO.getAll());

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (!validateForm()) return;
        
        phieuGiamGiaDAO.update(readFromForm());
        clearForm();
        fillTable(phieuGiamGiaDAO.getAll());
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            phieuGiamGiaDAO.delete(readFromForm());
            clearForm();
            fillTable(phieuGiamGiaDAO.getAll());
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKMMouseClicked
        int selectedRow = tblKM.getSelectedRow();
    if (selectedRow >= 0) {
        selectedId = Integer.parseInt(tblKM.getValueAt(selectedRow, 0).toString()); // <--- lưu ID

        txtMaPhieu.setText(tblKM.getValueAt(selectedRow, 1).toString());
        txtTenPhieu.setText(tblKM.getValueAt(selectedRow, 2).toString());
        txtHinhThucGG.setText(tblKM.getValueAt(selectedRow, 3).toString());
        txtGiaTri.setText(tblKM.getValueAt(selectedRow, 4).toString());
        txtSoLuong.setText(tblKM.getValueAt(selectedRow, 5).toString());
        Object ngayBatDau = defaultTableModel.getValueAt(selectedRow, 6); // lấy giá trị cột 6 dòng selectedRow
        if (ngayBatDau instanceof Date) {
            dtBatDau.setDate((Date) ngayBatDau);
        } else {
            dtBatDau.setDate(null); // hoặc xử lý nếu null hoặc không phải Date
        }
        Object ngayKetThuc = defaultTableModel.getValueAt(selectedRow, 7); // lấy giá trị cột 6 dòng selectedRow
        if (ngayKetThuc instanceof Date) {
            dtKetThuc.setDate((Date) ngayKetThuc);
        } else {
            dtKetThuc.setDate(null); // hoặc xử lý nếu null hoặc không phải Date
        }
        txtDK.setText(tblKM.getValueAt(selectedRow, 8).toString());
        String trangThai = tblKM.getValueAt(selectedRow, 9).toString();
        if (trangThai.equals("Đang hoạt động")) {
            rdoHD.setSelected(true);
        } else {
            rdoKHD.setSelected(true);
        }
    }
    }//GEN-LAST:event_tblKMMouseClicked

        public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_KhuyenMai().pack(); 
                new Menu_KhuyenMai().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dtBatDau;
    private com.toedter.calendar.JDateChooser dtKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JRadioButton rdoKHD;
    private javax.swing.JTable tblKM;
    private javax.swing.JTextField txtDK;
    private javax.swing.JTextField txtGiaTri;
    private javax.swing.JTextField txtHinhThucGG;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenPhieu;
    // End of variables declaration//GEN-END:variables
}
