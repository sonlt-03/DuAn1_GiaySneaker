/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import Model.GioHang;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Model.PhieuGiamGia;
import Model.PhuongThucThanhToan;
import Model.SanPham;
import Repository.Repository_SanPham;
import Repository.Responsitory_HoaDon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;

/**
 *
 * @author nguye
 */
public class Menu_HoaDon extends javax.swing.JInternalFrame {
    private Repository.Responsitory_HoaDon rp = new Responsitory_HoaDon();
    private Repository.Repository_SanPham rpsp = new Repository_SanPham();
    private DefaultTableModel mol;
    private int i;
    
    /**

    /**
     * Creates new form Menu_KhachHang
     */
    public Menu_HoaDon() {
        initComponents();
        this.fillTable(rp.getAll());
        this.fillcbbPTTT();
        this.fillcbbPGG();
        this.fillcbbNV();
        this.fillSP(rpsp.getAll());
        this.updateTongTien();
        this.clearForm();
        this.fillGioHang(rp.getAllGioHang());
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui =(BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        
        cbbPGG.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        PhieuGiamGia selected = (PhieuGiamGia) cbbPGG.getSelectedItem();
        if (selected != null && selected.getSoLuong() > 0) {
            boolean giamSoLuong = rp.giamSoLuongPhieu(selected.getIdPhieuGiamGia());
            if (giamSoLuong) {
                selected.setSoLuong(selected.getSoLuong() - 1); 
                capNhatTongTienSauGiam();
            } else {
                JOptionPane.showMessageDialog(null, "Phiếu giảm giá đã hết lượt sử dụng!");
                cbbPGG.setSelectedIndex(0); 
            }
        }
    }
});


    }
    

    public void fillTable(List<HoaDon> list) {
    mol = (DefaultTableModel) tblHoaDon.getModel();
    mol.setRowCount(0); 
    for (HoaDon hd : list) {
        mol.addRow(new Object[]{
            hd.getIdHoaDon(),
            hd.getMaHoaDon(),
            hd.getIdNhanVien(),
            hd.getIdKhachHang(),
            hd.getIdPhieuGiamGia(),
            hd.getIdPTTT(),
            hd.getNgayTao(),
            hd.getTongTien(),
            hd.getGhiChu(),
            hd.getTrangThai() ? "Đã thanh toán" : "Chưa thanh toán" 
        });
    }
}
    //UPDATE
    public void fillGioHang(List<GioHang> list) {
    DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
    model.setRowCount(0);  
    for (GioHang gh : list) {
        model.addRow(new Object[]{
            gh.getIdSanPham(),
            gh.getMaSanPham(),
            gh.getTenSanPham(),
            gh.getSoLuong(),
            gh.getDonGia(),
            gh.getGiamGia(),
            gh.getThanhTien()
        });
    }
}

//    public void fillGioHang(List<GioHang> list) {
//    mol = (DefaultTableModel) tblGioHang.getModel();
//    mol.setRowCount(0);
//    for (GioHang gh : list) {
//        mol.addRow(new Object[]{
//            gh.getIdSanPham(),
//            gh.getMaSanPham(),
//            gh.getTenSanPham(),
//            gh.getSoLuong(),
//            gh.getDonGia(),
//            gh.getGiamGia(),
//            gh.getThanhTien()
//        });
//    }
//}

    
    public void fillSP(List<SanPham> list){
        mol = (DefaultTableModel) tblSanPham.getModel();
        mol.setRowCount(0);
        for(SanPham sp : list){
            mol.addRow(new Object[]{
            sp.getIdSanPham(),
            sp.getMaSanPham(),
            sp.getTenSanPham(),
            sp.getIdMauSac(),
            sp.getIdChatLieu(),
            sp.getIdKieuDang(),
            sp.getIdLoaiGiay(),
            sp.getIdThuongHieu(),
            sp.getIdKichCo(),
            sp.getGiaBan(),
            sp.getSoLuong(),
            sp.getMoTa()
        });
        }
    }

    public void fillcbbPGG() {
    DefaultComboBoxModel<PhieuGiamGia> cmol = new DefaultComboBoxModel<>();
    cbbPGG.setModel(cmol); 
    ArrayList<PhieuGiamGia> list = rp.getAllPhieuGiamGia(); 
    if (list.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không có phiếu giảm giá!");
    }
    for (PhieuGiamGia pgg : list) {
        cmol.addElement(pgg); 
    }
}


    public void fillcbbPTTT() {
        DefaultComboBoxModel<PhuongThucThanhToan> cmol = new DefaultComboBoxModel<>();
        cbbHTTT.setModel(cmol);
        ArrayList<PhuongThucThanhToan> list = rp.getAllPhuongThucThanhToan();
        for (PhuongThucThanhToan pttt : list) {
            cmol.addElement(pttt);
        }
    }

    public void fillcbbNV() {
        DefaultComboBoxModel<NhanVien> cmol = new DefaultComboBoxModel<>();
        cbbNhanVien.setModel(cmol);
        ArrayList<NhanVien> list = rp.getAllNhanVien();
        for (NhanVien nv : list) {
            cmol.addElement(nv);
        }
    }
    
    public void updateTongTien() {
    double tongTien = 0;

    for (int row = 0; row < tblGioHang.getRowCount(); row++) {
        try {
            Object oDonGia = tblGioHang.getValueAt(row, 4);
            Object oSoLuong = tblGioHang.getValueAt(row, 3);

            if (oDonGia == null || oSoLuong == null) {
                continue;
            }

            double donGia = Double.parseDouble(oDonGia.toString());
            int soLuong = Integer.parseInt(oSoLuong.toString());

            double thanhTien = soLuong * donGia;
            tongTien += thanhTien;

            tblGioHang.setValueAt(thanhTien, row, 6); 
        } catch (NumberFormatException e) {
            System.err.println("Lỗi định dạng ở dòng " + row + ": " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    if (tongTien <= 0) {
        txtTongTien.setText("0");
    } else {
        txtTongTien.setText(String.format("%,.0f", tongTien)); 
    }
}





public void showData() {
    int row = tblHoaDon.getSelectedRow();
    if (row >= 0) {
        txtMaHD.setText(tblHoaDon.getValueAt(row, 1).toString());
        
        int idNhanVien = Integer.parseInt(tblHoaDon.getValueAt(row, 2).toString());
        for (int i = 0; i < cbbNhanVien.getItemCount(); i++) {
            NhanVien nv = (NhanVien) cbbNhanVien.getItemAt(i);
            if (nv.getIdNhanVien() == idNhanVien) {
                cbbNhanVien.setSelectedItem(nv);  
                break;
            }
        }

        if (row >= 0) {
        int idKhachHang = Integer.parseInt(tblHoaDon.getValueAt(row, 3).toString());
        try {
            KhachHang kh = rp.getKhachHangInfoById(idKhachHang);
            txtTenKH.setText(kh.getHoTen());
            txtSDT.setText(kh.getSoDienThoai());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy thông tin khách hàng!");
        }
    }

        int idPhieuGiamGia = Integer.parseInt(tblHoaDon.getValueAt(row, 3).toString());
        for (int i = 0; i < cbbPGG.getItemCount(); i++) {
            PhieuGiamGia pgg = (PhieuGiamGia) cbbPGG.getItemAt(i);
            if (pgg.getIdPhieuGiamGia() == idPhieuGiamGia) {
                cbbPGG.setSelectedItem(pgg); 
                break;
            }
        }

        int idPTTT = Integer.parseInt(tblHoaDon.getValueAt(row, 5).toString());
        for(int i = 0; i<cbbHTTT.getItemCount(); i++){
            PhuongThucThanhToan pttt = (PhuongThucThanhToan) cbbHTTT.getItemAt(i);
            if(pttt.getIdPTTT() == idPTTT){
                cbbHTTT.setSelectedItem(pttt);
                break;
            }
        }
        
        String ngayTao = tblHoaDon.getValueAt(row, 6).toString();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            txtDate.setDateFormatString("yyyy-MM-dd");
            Date date = sdf.parse(ngayTao);
            txtDate.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        txtTongTien.setText(tblHoaDon.getValueAt(row, 7).toString());

        txtGhiChu.setText(tblHoaDon.getValueAt(row, 8).toString());
        
    }
}

    public void clearForm(){
        txtTenKH.setText("");
        txtSDT.setText("");
        txtMaHD.setText("");
        txtGhiChu.setText("");
        txtTongTien.setText("0");
    }

    private void capNhatTongTienSauGiam() {
        double tongTamTinh = 0;
        DefaultTableModel modelGH = (DefaultTableModel) tblGioHang.getModel();

        for (int i = 0; i < modelGH.getRowCount(); i++) {
            tongTamTinh += Double.parseDouble(modelGH.getValueAt(i, 6).toString());
        }

        double giamGia = 0;
        PhieuGiamGia pgg = (PhieuGiamGia) cbbPGG.getSelectedItem();
        if (pgg != null && pgg.getGiaTri() != null) {
            giamGia = pgg.getGiaTri().doubleValue();
        }

        if (giamGia > tongTamTinh) {
            giamGia = tongTamTinh;
        }

        double tongSauGiam = tongTamTinh - giamGia;
        txtTongTien.setText(String.format("%,.0f", tongSauGiam));
    }

    

    




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        tbnDanhSachHoaDon = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnThemHD = new javax.swing.JButton();
        btnXoaHD = new javax.swing.JButton();
        btnSuaHD = new javax.swing.JButton();
        btnCTHD = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        btnXoaGH = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbbHTTT = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        cbbNhanVien = new javax.swing.JComboBox();
        cbbPGG = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        jLabel11.setText("jLabel11");

        setPreferredSize(new java.awt.Dimension(870, 530));

        tbnDanhSachHoaDon.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblHoaDon.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Hóa đơn", "Mã hóa đơn", "Id Nhân viên", "Id Khách hàng", "Id Phiếu giảm giá", "Id HTTT", "Ngày tạo", "Tổng tiền", "Ghi chú", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(342, 342, 342))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("DANH SÁCH HÓA ĐƠN");

        btnThemHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Add.png"))); // NOI18N
        btnThemHD.setText("Tạo hóa đơn");
        btnThemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHDActionPerformed(evt);
            }
        });

        btnXoaHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        btnXoaHD.setText("Xóa hóa đơn");
        btnXoaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHDActionPerformed(evt);
            }
        });

        btnSuaHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Edit.png"))); // NOI18N
        btnSuaHD.setText("Sửa hóa đơn");
        btnSuaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaHDActionPerformed(evt);
            }
        });

        btnCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Lists.png"))); // NOI18N
        btnCTHD.setText("Tổng hóa đơn");
        btnCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCTHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tbnDanhSachHoaDonLayout = new javax.swing.GroupLayout(tbnDanhSachHoaDon);
        tbnDanhSachHoaDon.setLayout(tbnDanhSachHoaDonLayout);
        tbnDanhSachHoaDonLayout.setHorizontalGroup(
            tbnDanhSachHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbnDanhSachHoaDonLayout.createSequentialGroup()
                .addGroup(tbnDanhSachHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbnDanhSachHoaDonLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addGap(0, 405, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tbnDanhSachHoaDonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThemHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCTHD)))
                .addContainerGap())
        );
        tbnDanhSachHoaDonLayout.setVerticalGroup(
            tbnDanhSachHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbnDanhSachHoaDonLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tbnDanhSachHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemHD)
                    .addComponent(btnXoaHD)
                    .addComponent(btnSuaHD)
                    .addComponent(btnCTHD))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblGioHang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID SP", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tblGioHang);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Giỏ hàng");

        btnXoaGH.setText("Xóa khỏi giỏ hàng");
        btnXoaGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(btnXoaGH))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnXoaGH)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Tên KH");

        jLabel4.setText("Nhân viên");

        jLabel8.setText("HT Thanh toán");

        cbbHTTT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền mặt", "Chuyển khoản", "Ví điện tử" }));
        cbbHTTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHTTTActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Dollar.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Ngày tạo");

        jLabel9.setText("Phiếu giảm giá");

        jLabel10.setText("Mã hóa đơn");

        jLabel12.setText("Ghi chú");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Tổng tiền : ");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTongTien.setText("0");

        jLabel1.setText("SĐT");

        cbbPGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbPGGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(cbbPGG, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbHTTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(41, 41, 41)
                                .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(51, 51, 51)
                                .addComponent(txtGhiChu))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaHD)
                                    .addComponent(txtTenKH)
                                    .addComponent(txtSDT)
                                    .addComponent(cbbNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbbPGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbbHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Sản phẩm");

        tblSanPham.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id sản phẩm", "Mã sản phẩm", "Tên sản phẩm", "Màu sắc", "Chất liệu", "Kiểu dáng", "Loại giày", "Thương hiệu", "Kích cỡ", "Giá", "Số lượng", "Mô tả"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbnDanhSachHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbnDanhSachHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHDActionPerformed
        try {
        String tenKhachHang = txtTenKH.getText().trim();
        String sdt = txtSDT.getText().trim();
        String maHoaDon = txtMaHD.getText().trim();
        Date ngayTaoDate = txtDate.getDate();
        String tongTienStr = txtTongTien.getText().trim().replace(",", "").replace(".", "");

        if (tenKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng không được để trống!");
            return;
        }

        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
            return;
        }

        if (!sdt.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! Phải là 10 chữ số.");
            return;
        }

        if (maHoaDon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã hóa đơn không được để trống!");
            return;
        }
        
        for(HoaDon hdTT : rp.getAll()){
            if(hdTT.getMaHoaDon().equalsIgnoreCase(maHoaDon)){
                JOptionPane.showMessageDialog(this, "Mã hóa đơn đã tồn tại");
                return;
            }
        }

        if (ngayTaoDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày tạo!");
            return;
        }
        

        if (tongTienStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tổng tiền đang trống!");
            return;
        }

        double tongTien;
        try {
            tongTien = Double.parseDouble(tongTienStr);
            if (tongTien <= 0) {
                JOptionPane.showMessageDialog(this, "Tổng tiền phải lớn hơn 0!");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tổng tiền không hợp lệ! Vui lòng kiểm tra lại định dạng.");
            return;
        }

//
//        double tongTien;
//        try {
//            tongTien = Double.parseDouble(tongTienStr);
//            if (tongTien <= 0) {
//                JOptionPane.showMessageDialog(this, "Tổng tiền phải lớn hơn 0!");
//                return;
//            }
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(this, "Tổng tiền không hợp lệ!");
//            return;
//        }

        int idKhachHang = rp.getKhachHangIdBySDT(sdt);
        if (idKhachHang == -1) {
            idKhachHang = rp.addNewKhachHang(tenKhachHang, sdt);
            if (idKhachHang == -1) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm khách hàng!");
                return;
            }
        }

        int idNhanVien = ((NhanVien) cbbNhanVien.getSelectedItem()).getIdNhanVien();
        int idPhieuGiamGia = ((PhieuGiamGia) cbbPGG.getSelectedItem()).getIdPhieuGiamGia();
        int idPTTT = ((PhuongThucThanhToan) cbbHTTT.getSelectedItem()).getIdPTTT();
        String ngayTao = new SimpleDateFormat("yyyy-MM-dd").format(ngayTaoDate);
        String ghiChu = txtGhiChu.getText().trim();

        HoaDon hoaDon = new HoaDon(0, maHoaDon, idNhanVien, idKhachHang, idPhieuGiamGia, idPTTT, ngayTao, tongTien, ghiChu, false);
        int result = rp.add(hoaDon);

        if (result > 0) {
        int idHoaDonMoi = rp.getIdHoaDonByMa(maHoaDon); // Viết hàm này nếu chưa có

        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            GioHang sp = new GioHang();
            sp.setIdSanPham(Integer.parseInt(tblGioHang.getValueAt(i, 0).toString()));
            sp.setMaSanPham(tblGioHang.getValueAt(i, 1).toString());
            sp.setTenSanPham(tblGioHang.getValueAt(i, 2).toString());
            sp.setSoLuong(Integer.parseInt(tblGioHang.getValueAt(i, 3).toString()));
            sp.setDonGia(Double.parseDouble(tblGioHang.getValueAt(i, 4).toString()));
            sp.setGiamGia(Double.parseDouble(tblGioHang.getValueAt(i, 5).toString()));
            sp.setThanhTien(Double.parseDouble(tblGioHang.getValueAt(i, 6).toString()));

            rp.addHoaDonChiTiet(idHoaDonMoi, sp);
        }

        JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công!");
        this.fillTable(rp.getAll());
        this.fillGioHang(new ArrayList<>()); 
        this.clearForm();
        capNhatTongTienSauGiam();
        }else {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm hóa đơn!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi cơ sở dữ liệu: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
    }
    }//GEN-LAST:event_btnThemHDActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        i = tblHoaDon.getSelectedRow();
        showData();

        int idHoaDon = Integer.parseInt(tblHoaDon.getValueAt(i, 0).toString());
        List<GioHang> dsChiTiet = rp.getGioHangByHoaDon(idHoaDon);
        fillGioHang(dsChiTiet);  
        updateTongTien();       
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void cbbHTTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHTTTActionPerformed
      
    }//GEN-LAST:event_cbbHTTTActionPerformed

    private void cbbPGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbPGGActionPerformed
        PhieuGiamGia selectedPhieuGiamGia = (PhieuGiamGia) cbbPGG.getSelectedItem();
        if (selectedPhieuGiamGia != null) {
            capNhatTongTienSauGiam();
        }
    }//GEN-LAST:event_cbbPGGActionPerformed

    private void btnXoaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHDActionPerformed
    int selectedRow = tblHoaDon.getSelectedRow();
    if (selectedRow >= 0) {
        int idHoaDon = (int) tblHoaDon.getValueAt(selectedRow, 0); 
        int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa hóa đơn này?", "Xóa hóa đơn", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            boolean isDeleted = rp.delete(idHoaDon);
            if (isDeleted) {
                JOptionPane.showMessageDialog(this, "Xóa hóa đơn thành công!");
                rp.clearGioHang();
                this.fillTable(rp.getAll());
                this.fillGioHang(rp.getAllGioHang());
                this.clearForm();
                capNhatTongTienSauGiam();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa hóa đơn!");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để xóa!");
    }
    }//GEN-LAST:event_btnXoaHDActionPerformed

    private void btnSuaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaHDActionPerformed
    try {
        String maHoaDon = txtMaHD.getText().trim();
        String sdt = txtSDT.getText().trim();
        String tenKhachHang = txtTenKH.getText().trim();
        String tongTienStr = txtTongTien.getText().trim().replace(",", "").replace(".", "");


        if (sdt.isEmpty() || tenKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng và SĐT không được để trống!");
            return;
        }

        int rowHD = tblHoaDon.getSelectedRow();
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Chọn hóa đơn cần sửa.");
            return;
        }

        int idHoaDon = Integer.parseInt(tblHoaDon.getValueAt(rowHD, 0).toString());
        int idKhachHang = Integer.parseInt(tblHoaDon.getValueAt(rowHD, 3).toString());

        if (!rp.updateKhachHang(idKhachHang, tenKhachHang, sdt)) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật khách hàng!");
            return;
        }

        if (tblGioHang.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng không được để trống.");
            return;
        }

        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        DefaultTableModel modelGH = (DefaultTableModel) tblGioHang.getModel();

        for (int i = 0; i < modelGH.getRowCount(); i++) {
            int soLuong = Integer.parseInt(modelGH.getValueAt(i, 3).toString());
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm phải > 0.");
                return;
            }

            int idSanPham = Integer.parseInt(modelGH.getValueAt(i, 0).toString());
            double donGia = Double.parseDouble(modelGH.getValueAt(i, 4).toString());
            double giamGia = Double.parseDouble(modelGH.getValueAt(i, 5).toString());
            double thanhTien = Double.parseDouble(modelGH.getValueAt(i, 6).toString());

            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setIdHoaDon(idHoaDon);
            hdct.setIdSanPham(idSanPham);
            hdct.setSoLuong(soLuong);
            hdct.setDonGia(donGia);
            hdct.setGiamGia(giamGia);
            hdct.setThanhTien(thanhTien);

            listHDCT.add(hdct);
        }

        int idNhanVien = ((NhanVien) cbbNhanVien.getSelectedItem()).getIdNhanVien();
        int idPhieuGiamGia = ((PhieuGiamGia) cbbPGG.getSelectedItem()).getIdPhieuGiamGia();
        int idPTTT = ((PhuongThucThanhToan) cbbHTTT.getSelectedItem()).getIdPTTT();
        String ngayTao = new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate());
        double tongTien = Double.parseDouble(txtTongTien.getText());
        String ghiChu = txtGhiChu.getText();

        HoaDon hd = new HoaDon(idHoaDon, maHoaDon, idNhanVien, idKhachHang, idPhieuGiamGia, idPTTT, ngayTao, tongTien, ghiChu, false);

        if (rp.update(hd)) {
            rp.deleteHDCTByHoaDon(idHoaDon);           
            rp.insertHDCTs(listHDCT);              
            JOptionPane.showMessageDialog(this, "Cập nhật hóa đơn thành công!");
            this.fillTable(rp.getAll());
            this.fillGioHang(rp.getAllGioHangByHoaDon(idHoaDon));
            capNhatTongTienSauGiam();

        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật hóa đơn!");
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }

    }//GEN-LAST:event_btnSuaHDActionPerformed

    private void btnCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCTHDActionPerformed
        int i = JOptionPane.showConfirmDialog(this, "Xem tổng hóa đơn ?");
        if (i == 0) {
            View_HDCT view = new View_HDCT();
            view.setVisible(true);
        }
    }//GEN-LAST:event_btnCTHDActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int selectedRow = tblHoaDon.getSelectedRow();
        if (selectedRow >= 0) {
        int idHoaDon = Integer.parseInt(tblHoaDon.getValueAt(selectedRow, 0).toString()); 
        try {
            boolean isUpdated = rp.updateTrangThaiHoaDon(idHoaDon, true);
            if (isUpdated) {
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
                    this.fillTable(rp.getAll());
                    rp.clearGioHang();
                    this.fillGioHang(rp.getAllGioHang());
                    this.clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi chuyển hóa đơn vào lịch sử!");
                }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để thanh toán!");
    }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
    int i = JOptionPane.showConfirmDialog(this, "Thêm sản phẩm vào giỏ hàng?");
    if (i == JOptionPane.YES_OPTION) {
    int selectedRow = tblSanPham.getSelectedRow();
    if (selectedRow >= 0) {
        int idSanPham = Integer.parseInt(tblSanPham.getValueAt(selectedRow, 0).toString());
        String maSanPham = tblSanPham.getValueAt(selectedRow, 1).toString();
        String tenSanPham = tblSanPham.getValueAt(selectedRow, 2).toString();
        int soLuongTrongKho = Integer.parseInt(tblSanPham.getValueAt(selectedRow, 10).toString());
        double donGia = Double.parseDouble(tblSanPham.getValueAt(selectedRow, 9).toString());
        double giamGia = 0;

        // Nhập số lượng muốn mua
        String soLuongStr = JOptionPane.showInputDialog(this, "Nhập số lượng muốn thêm:");
        if (soLuongStr != null && !soLuongStr.trim().isEmpty()) {
            try {
                int soLuongMuonThem = Integer.parseInt(soLuongStr);
                if (soLuongMuonThem <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
                    return;
                }
                if (soLuongMuonThem > soLuongTrongKho) {
                    JOptionPane.showMessageDialog(this, "Không đủ hàng trong kho!");
                    return;
                }

                // Trừ hàng trong kho
                boolean capNhatThanhCong = rp.giamSoLuongSanPhamTheoSL(idSanPham, soLuongMuonThem);
                if (!capNhatThanhCong) {
                    JOptionPane.showMessageDialog(this, "Không thể cập nhật số lượng sản phẩm!");
                    return;
                }

                double thanhTien = (donGia * soLuongMuonThem) - giamGia;

                GioHang gh = new GioHang(0, idSanPham, maSanPham, tenSanPham, soLuongMuonThem, donGia, giamGia, thanhTien);
                if (rp.insertGioHang(gh)) {
                    DefaultTableModel modelGioHang = (DefaultTableModel) tblGioHang.getModel();
                    modelGioHang.addRow(new Object[]{idSanPham, maSanPham, tenSanPham, soLuongMuonThem, donGia, giamGia, thanhTien});
                    tblSanPham.setValueAt(soLuongTrongKho - soLuongMuonThem, selectedRow, 10);
                    updateTongTien();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm vào giỏ hàng thất bại (DB)!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
            }
        }
    }
}


    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnXoaGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGHActionPerformed
    int selectedRow = tblGioHang.getSelectedRow();
    if (selectedRow >= 0) {
    String idSanPham = tblGioHang.getValueAt(selectedRow, 0).toString();
    String maSanPham = tblGioHang.getValueAt(selectedRow, 1).toString(); 
    int soLuongXoa = Integer.parseInt(tblGioHang.getValueAt(selectedRow, 3).toString()); // Lấy số lượng

    System.out.println("Mã sản phẩm được chọn: " + maSanPham);
    System.out.println("Số lượng bị xóa khỏi giỏ: " + soLuongXoa);

    int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này khỏi giỏ hàng?", "Xóa sản phẩm", JOptionPane.YES_NO_OPTION);
    if (confirmation == JOptionPane.YES_OPTION) {
        boolean isDeleted = rp.deleteGioHang(maSanPham); 
        if (isDeleted) {
            JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
            this.fillGioHang(rp.getAllGioHang());

            // Cộng lại số lượng vào bảng sản phẩm
            boolean xoaThanhCong = rp.themSoLuongSanPham(idSanPham, soLuongXoa);

            this.fillSP(rpsp.getAll());
            updateTongTien();
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa sản phẩm!");
            System.err.println("Lỗi khi xóa: mã sản phẩm không tìm thấy hoặc SQL lỗi.");
        }
    }
} else {
    JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xóa!");
}

    }//GEN-LAST:event_btnXoaGHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCTHD;
    private javax.swing.JButton btnSuaHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemHD;
    private javax.swing.JButton btnXoaGH;
    private javax.swing.JButton btnXoaHD;
    private javax.swing.JComboBox cbbHTTT;
    private javax.swing.JComboBox cbbNhanVien;
    private javax.swing.JComboBox cbbPGG;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JPanel tbnDanhSachHoaDon;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
