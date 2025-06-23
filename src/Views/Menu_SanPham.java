package Views;

import Model.ChatLieu;
import Model.KichCo;
import Model.LoaiGiay;
import Model.MauSac;
import Model.SanPham;
import Model.ThuongHieu;
import Repository.rp_SanPham;
import Repository.rp_SanPhamChiTiet;
import Repository.rp_ThuocTinh;
import Utils.Dbconnection;
import Utils.EnumUtil;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;

public class Menu_SanPham extends javax.swing.JInternalFrame {

    private Repository.rp_SanPhamChiTiet rp = new rp_SanPhamChiTiet();
    private Repository.rp_SanPham rpSP = new rp_SanPham();
    private Repository.rp_ThuocTinh rpTT = new rp_ThuocTinh();
    private DefaultTableModel modelThuocTinh;
    private DefaultTableModel model;
    private int i;

    private String chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn hình ảnh sản phẩm");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg", "gif"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    private void loadImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(lblIMG.getWidth(), lblIMG.getHeight(), Image.SCALE_SMOOTH);
        lblIMG.setIcon(new ImageIcon(img));
        this.path = imagePath;
    }

    public Menu_SanPham() {
        initComponents();
        fillTable(rp.getAll());
        fillComboMauSac();
        fillComboKichCo();
        fillComboThuongHieu();
        fillComboLoaiGiay();
        fillComboChatLieu();
        fillTableSanPham(rpSP.getAll());
        modelThuocTinh = (DefaultTableModel) tblThuocTinh.getModel();
        addRadioButtonListeners();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        EnumUtil.setDanhSachMauSac(rpTT.getAllMauSac());
        EnumUtil.setDanhSachKichCo(rpTT.getAllKichCo());
        EnumUtil.setDanhSachThuongHieu(rpTT.getAllThuongHieu());
        EnumUtil.setDanhSachLoaiGiay(rpTT.getAllLoaiGiay());
        EnumUtil.setDanhSachChatLieu(rpTT.getAllChatLieu());

    }
    String path = null;

    public void fillTable(List<SanPham> list) {
        model = (DefaultTableModel) tblSPCT.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (SanPham sp : list) {
            model.addRow(new Object[]{
                stt++,
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                EnumUtil.getTenMauSacById(sp.getIdMauSac()),
                EnumUtil.getTenKichCoById(sp.getIdKichCo()),
                EnumUtil.getTenThuongHieuById(sp.getIdThuongHieu()),
                EnumUtil.getTenLoaiGiayById(sp.getIdLoaiGiay()),
                EnumUtil.getTenChatLieuById(sp.getIdChatLieu()),
                sp.getGiaBan(),
                sp.getSoLuong(),
                sp.getImgPath()
            });

        }
    }

    public void fillTableSanPham(List<SanPham> listSP) {
        model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (SanPham sp : listSP) {
            System.out.println("Thêm sản phẩm: " + sp.getMaSanPham() + " - " + sp.getTenSanPham() + " - " + sp.getSoLuong());
            model.addRow(new Object[]{
                stt++,
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                sp.getSoLuong()
            });
        }
        tblSanPham.repaint();
        tblSanPham.revalidate();
    }

    public void showData() {
        int row = tblSPCT.getSelectedRow();
        if (row >= 0) {
            txtMaSPCT.setText(getSafeValue(tblSPCT, row, 1));
            txtTenSPCT.setText(getSafeValue(tblSPCT, row, 2));
            cbbMauSac.setSelectedItem(getSafeValue(tblSPCT, row, 3));
            cbbKichThuoc.setSelectedItem(getSafeValue(tblSPCT, row, 4));
            cbbThuongHieu.setSelectedItem(getSafeValue(tblSPCT, row, 5));
            cbbLoaiGiay.setSelectedItem(getSafeValue(tblSPCT, row, 6));
            cbbChatLieu.setSelectedItem(getSafeValue(tblSPCT, row, 7));
            txtGiaBan.setText(getSafeValue(tblSPCT, row, 8));
            txtSoLuongCT.setText(getSafeValue(tblSPCT, row, 9));

            String imgPath = getSafeValue(tblSPCT, row, 10);
            System.out.println(">>> Đường dẫn ảnh lấy từ bảng: " + imgPath);
            if (imgPath != null && !imgPath.trim().isEmpty()) {
                loadImage(imgPath);
            } else {
                lblIMG.setIcon(null);
            }
        }
    }

    private String getSafeValue(JTable table, int row, int col) {
        Object val = table.getValueAt(row, col);
        return val != null ? val.toString() : "";
    }

    public void showDataSP() {
        int row = tblSanPham.getSelectedRow();
        if (row >= 0) {
            txtMaSanPham.setText(tblSanPham.getValueAt(row, 1).toString());
            txtTenSanPham.setText(tblSanPham.getValueAt(row, 2).toString());
        }
    }

    private void clearForm() {
        txtMaSPCT.setText("");
        txtTenSPCT.setText("");
        cbbMauSac.setSelectedIndex(-1);
        cbbKichThuoc.setSelectedIndex(-1);
        cbbThuongHieu.setSelectedIndex(-1);
        cbbLoaiGiay.setSelectedIndex(-1);
        cbbChatLieu.setSelectedIndex(-1);
        txtGiaBan.setText("");
        txtSoLuongCT.setText("");

    }

    private void clearFormSP() {
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");

    }

    private void clearFormTT() {
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
        txtMoTa.setText("");
    }

    private int getIdMauSacByName(String ten) {
        String sql = "SELECT idMauSac FROM MauSac WHERE tenMau = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idMauSac");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getIdKichCoByName(String ten) {
        String sql = "SELECT idKichCo FROM KichCo WHERE kichThuoc = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idKichCo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getIdThuongHieuByName(String ten) {
        String sql = "SELECT idThuongHieu FROM ThuongHieu WHERE tenThuongHieu = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idThuongHieu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getIdLoaiGiayByName(String ten) {
        String sql = "SELECT idLoaiGiay FROM LoaiGiay WHERE tenLoaiGiay = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idLoaiGiay");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getIdChatLieuByName(String ten) {
        String sql = "SELECT idChatLieu FROM ChatLieu WHERE tenChatLieu = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idChatLieu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void addRadioButtonListeners() {
        rdoMauSac.addActionListener(e -> loadMauSacData());
        rdoKichThuoc.addActionListener(e -> loadKichCoData());
        rdoThuongHieu.addActionListener(e -> loadThuongHieuData());
        rdoChatLieu.addActionListener(e -> loadChatLieuData());
        rdoLoaiGiay.addActionListener(e -> loadLoaiGiayData());
    }

    private void loadMauSacData() {
        modelThuocTinh.setRowCount(0);
        String sql = "SELECT maMau, tenMau, moTa FROM MauSac";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            int stt = 1;
            while (rs.next()) {
                modelThuocTinh.addRow(new Object[]{
                    stt++, // STT
                    rs.getString("maMau"), // Mã
                    rs.getString("tenMau"), // Tên
                    rs.getString("moTa") // Mô tả
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu màu sắc: " + e.getMessage());
        }
    }

    private void loadKichCoData() {
        modelThuocTinh.setRowCount(0);
        String sql = "SELECT maKichCo, kichThuoc, moTa FROM KichCo";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            int stt = 1;
            while (rs.next()) {
                modelThuocTinh.addRow(new Object[]{
                    stt++,
                    rs.getString("maKichCo"),
                    rs.getString("kichThuoc"),
                    rs.getString("moTa")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu kích cỡ: " + e.getMessage());
        }
    }

    private void loadThuongHieuData() {
        modelThuocTinh.setRowCount(0);
        modelThuocTinh.setColumnIdentifiers(new Object[]{"STT", "Mã", "Tên", "Mô tả"});
        String sql = "SELECT maThuongHieu, tenThuongHieu, moTa FROM ThuongHieu";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            int stt = 1;
            while (rs.next()) {
                modelThuocTinh.addRow(new Object[]{
                    stt++,
                    rs.getString("maThuongHieu"),
                    rs.getString("tenThuongHieu"),
                    rs.getString("moTa")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu thương hiệu: " + e.getMessage());
        }
    }

    private void loadChatLieuData() {
        modelThuocTinh.setRowCount(0);
        modelThuocTinh.setColumnIdentifiers(new Object[]{"STT", "Mã", "Tên", "Mô tả"});
        String sql = "SELECT maChatLieu, tenChatLieu, moTa FROM ChatLieu";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            int stt = 1;
            while (rs.next()) {
                modelThuocTinh.addRow(new Object[]{
                    stt++,
                    rs.getString("maChatLieu"),
                    rs.getString("tenChatLieu"),
                    rs.getString("moTa")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu chất liệu: " + e.getMessage());
        }
    }

    private void loadLoaiGiayData() {
        modelThuocTinh.setRowCount(0);
        modelThuocTinh.setColumnIdentifiers(new Object[]{"STT", "Mã", "Tên", "Mô tả"});
        String sql = "SELECT maLoaiGiay, tenLoaiGiay, moTa FROM LoaiGiay";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            int stt = 1;
            while (rs.next()) {
                modelThuocTinh.addRow(new Object[]{
                    stt++,
                    rs.getString("maLoaiGiay"),
                    rs.getString("tenLoaiGiay"),
                    rs.getString("moTa")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu loại giày: " + e.getMessage());
        }
    }

    private void loadDataToTable(String sql, String loaiThuocTinh, boolean coMoTa) {
        modelThuocTinh.setRowCount(0);
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                if (coMoTa) {
                    modelThuocTinh.addRow(new Object[]{
                        rs.getString(1), // id thật như maMau
                        loaiThuocTinh,
                        rs.getString(2), // tên
                        rs.getString(3) // mô tả
                    });
                } else {
                    modelThuocTinh.addRow(new Object[]{
                        rs.getString(1),
                        loaiThuocTinh,
                        rs.getString(2)
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    public void showDataThuocTinh() {
        int row = tblThuocTinh.getSelectedRow();

        if (row >= 0) {

            txtMaThuocTinh.setText(tblThuocTinh.getValueAt(row, 1).toString());
            txtTenThuocTinh.setText(tblThuocTinh.getValueAt(row, 2).toString());
            txtMoTa.setText(tblThuocTinh.getValueAt(row, 3).toString());
        }
    }

    private void fillComboMauSac() {
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cbbMauSac.getModel();
        cmol.removeAllElements();
        ArrayList<MauSac> list = rpTT.getAllMauSac();
        for (MauSac ms : list) {
            cmol.addElement(ms.getTenMau());
        }
        cbbMauSac.setSelectedIndex(-1);
    }

    private void fillComboKichCo() {
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cbbKichThuoc.getModel();
        cmol.removeAllElements();
        ArrayList<KichCo> list = rpTT.getAllKichCo();
        for (KichCo kc : list) {
            cmol.addElement(kc.getKichThuoc());
        }
        cbbKichThuoc.setSelectedIndex(-1);
    }

    private void fillComboThuongHieu() {
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cbbThuongHieu.getModel();
        cmol.removeAllElements();
        ArrayList<ThuongHieu> list = rpTT.getAllThuongHieu();
        for (ThuongHieu th : list) {
            cmol.addElement(th.getTenThuongHieu());
        }
        cbbThuongHieu.setSelectedIndex(-1);
    }

    private void fillComboLoaiGiay() {
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cbbLoaiGiay.getModel();
        cmol.removeAllElements();
        ArrayList<LoaiGiay> list = rpTT.getAllLoaiGiay();
        for (LoaiGiay lg : list) {
            cmol.addElement(lg.getTenLoaiGiay());
        }
        cbbLoaiGiay.setSelectedIndex(-1);
    }

    private void fillComboChatLieu() {
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cbbChatLieu.getModel();
        cmol.removeAllElements();
        ArrayList<ChatLieu> list = rpTT.getAllChatLieu();
        for (ChatLieu cl : list) {
            cmol.addElement(cl.getTenChatLieu());
        }
        cbbChatLieu.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        txtTenSanPham = new javax.swing.JTextField();
        btnThemSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnLamMoiSP = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblIMG = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSPCT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSPCT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbbKichThuoc = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        cbbLoaiGiay = new javax.swing.JComboBox<>();
        cbbChatLieu = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuongCT = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnThemSanPham = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        btnSuaSanPham = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSPCT = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoKichThuoc = new javax.swing.JRadioButton();
        rdoThuongHieu = new javax.swing.JRadioButton();
        rdoChatLieu = new javax.swing.JRadioButton();
        rdoLoaiGiay = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();
        btnThemTT = new javax.swing.JButton();
        btnXoaTT = new javax.swing.JButton();
        btnSuaTT = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        btnLamMoiTT = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(870, 530));

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel1.setText("Mã Sản Phẩm:");

        jLabel12.setText("Tên Sản Phẩm:");

        txtMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanPhamActionPerformed(evt);
            }
        });

        txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPhamActionPerformed(evt);
            }
        });

        btnThemSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Add.png"))); // NOI18N
        btnThemSP.setText("  Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnSuaSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Edit.png"))); // NOI18N
        btnSuaSP.setText("   Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        jButton3.setText("   Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnLamMoiSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Refresh.png"))); // NOI18N
        btnLamMoiSP.setText("   Làm Mới");
        btnLamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel8);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblIMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/Nike AirForce1.jpg"))); // NOI18N
        lblIMG.setText("IMG");
        lblIMG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIMGMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel2.setText("Mã sản phẩm");

        txtMaSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPCTActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel3.setText("Tên sản phẩm");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel4.setText("Màu sắc");

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel5.setText("Kích thước");

        cbbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel6.setText("Thương hiệu");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel7.setText("Loại giày");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel8.setText("Chất liệu");

        cbbThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbLoaiGiay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel10.setText("Giá bán");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel11.setText("Số lượng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbMauSac, 0, 201, Short.MAX_VALUE)
                            .addComponent(txtTenSPCT)
                            .addComponent(txtMaSPCT)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSoLuongCT, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbLoaiGiay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbKichThuoc, 0, 201, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtMaSPCT)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel7))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbLoaiGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoLuongCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIMG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 310));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnThemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Add.png"))); // NOI18N
        btnThemSanPham.setText("Thêm sản phẩm");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnXoaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        btnXoaSanPham.setText("Xóa sản phẩm");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        btnSuaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Edit.png"))); // NOI18N
        btnSuaSanPham.setText("Sửa sản phẩm");
        btnSuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSanPhamActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(btnSuaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 180, 310));

        tblSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Màu sắc", "Kích thước", "Thương hiệu", "Loại giày", "Chất liệu", "Giá bán", "Số lượng", "IMG"
            }
        ));
        tblSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPCTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSPCT);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 840, 290));

        jTabbedPane1.addTab("Sản Phẩm Chi Tiết", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonGroup1.add(rdoMauSac);
        rdoMauSac.setText("Màu sắc");

        buttonGroup1.add(rdoKichThuoc);
        rdoKichThuoc.setText("Kích thước");

        buttonGroup1.add(rdoThuongHieu);
        rdoThuongHieu.setText("Thương hiệu");

        buttonGroup1.add(rdoChatLieu);
        rdoChatLieu.setText("Chất liệu");

        buttonGroup1.add(rdoLoaiGiay);
        rdoLoaiGiay.setText("Loại giày");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Mã thuộc tính");

        txtMaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaThuocTinhActionPerformed(evt);
            }
        });

        btnThemTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Add.png"))); // NOI18N
        btnThemTT.setText("Thêm");
        btnThemTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTTActionPerformed(evt);
            }
        });

        btnXoaTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        btnXoaTT.setText("Xóa");
        btnXoaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTTActionPerformed(evt);
            }
        });

        btnSuaTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Edit.png"))); // NOI18N
        btnSuaTT.setText("Sửa");
        btnSuaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTTActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tên thuộc tính");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane4.setViewportView(txtMoTa);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Mô tả");

        btnLamMoiTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Refresh.png"))); // NOI18N
        btnLamMoiTT.setText("Làm Mới");
        btnLamMoiTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(btnThemTT)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaTT)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoiTT, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(12, 12, 12)
                                .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoMauSac)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoKichThuoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoThuongHieu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoChatLieu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoLoaiGiay)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoMauSac)
                    .addComponent(rdoKichThuoc)
                    .addComponent(rdoThuongHieu)
                    .addComponent(rdoChatLieu)
                    .addComponent(rdoLoaiGiay)
                    .addComponent(jLabel9)
                    .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnThemTT, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(btnXoaTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLamMoiTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 840, 180));

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Thuộc Tính", "Tên Thuộc Tính", "Mô Tả"
            }
        ));
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblThuocTinh);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 840, 500));

        jTabbedPane1.addTab("Thuộc Tính", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblIMGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIMGMouseClicked
        String imagePath = chooseImage();
        if (imagePath != null) {
            loadImage(imagePath);
        }
    }//GEN-LAST:event_lblIMGMouseClicked

    private void tblSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPCTMouseClicked
        // TODO add your handling code here:
        i = tblSPCT.getSelectedRow();
        showData();
    }//GEN-LAST:event_tblSPCTMouseClicked

    private void btnLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiMouseClicked
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnLamMoiMouseClicked

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        try {
            String maSanPham = txtMaSPCT.getText().trim();
            if (maSanPham.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống!");
                txtMaSPCT.requestFocus();
                return;
            }

            for (SanPham sp : rp.getAll()) {
                if (sp.getMaSanPham().equalsIgnoreCase(maSanPham)) {
                    JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại!");
                    return;
                }
            }

            String tenSanPham = txtTenSPCT.getText().trim();
            if (tenSanPham.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
                txtTenSPCT.requestFocus();
                return;
            }

            // Kiểm tra các combo box
            if (cbbMauSac.getSelectedItem() == null || cbbKichThuoc.getSelectedItem() == null
                    || cbbThuongHieu.getSelectedItem() == null || cbbLoaiGiay.getSelectedItem() == null
                    || cbbChatLieu.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ các thuộc tính!");
                return;
            }

            double giaBan;
            try {
                giaBan = Double.parseDouble(txtGiaBan.getText().trim());
                if (giaBan < 0) {
                    JOptionPane.showMessageDialog(this, "Giá bán không hợp lệ!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá bán phải là số!");
                return;
            }

            int soLuong;
            try {
                soLuong = Integer.parseInt(txtSoLuongCT.getText().trim());
                if (soLuong < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn hoặc bằng 0!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên!");
                return;
            }

            // Lấy ID thuộc tính từ tên
            int idMauSac = getIdMauSacByName(cbbMauSac.getSelectedItem().toString());
            int idKichCo = getIdKichCoByName(cbbKichThuoc.getSelectedItem().toString());
            int idThuongHieu = getIdThuongHieuByName(cbbThuongHieu.getSelectedItem().toString());
            int idLoaiGiay = getIdLoaiGiayByName(cbbLoaiGiay.getSelectedItem().toString());
            int idChatLieu = getIdChatLieuByName(cbbChatLieu.getSelectedItem().toString());

            // Gán dữ liệu
            SanPham sp = new SanPham();
            sp.setMaSanPham(maSanPham);
            sp.setTenSanPham(tenSanPham);
            sp.setIdMauSac(idMauSac);
            sp.setIdKichCo(idKichCo);
            sp.setIdThuongHieu(idThuongHieu);
            sp.setIdLoaiGiay(idLoaiGiay);
            sp.setIdChatLieu(idChatLieu);
            sp.setGiaBan(giaBan);
            sp.setSoLuong(soLuong);

// Sửa tại đây
            if (path == null || path.trim().isEmpty()) {
                path = "D:\\DuAn1_GiaySneaker\\src\\IMAGE\\default.jpg";
            }
            sp.setImgPath(path);

            boolean success = rp.add(sp);
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
                fillTable(rp.getAll());
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed

        try {
            int selectedRow = tblSPCT.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xóa.");
                return;
            }

            String maSanPham = tblSPCT.getValueAt(selectedRow, 1).toString();

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa sản phẩm có mã: " + maSanPham + "?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);
            System.out.println("maSanPham cần xóa: " + maSanPham);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = rp.delete(maSanPham);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
                    fillTable(rp.getAll());
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnSuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSanPhamActionPerformed
        try {
            int row = tblSPCT.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để sửa");
                return;
            }

            String maSanPhamCu = tblSPCT.getValueAt(row, 1).toString();
            String maSanPhamMoi = txtMaSPCT.getText().trim();

            if (maSanPhamMoi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống");
                txtMaSPCT.requestFocus();
                return;
            }

            // Kiểm tra mã mới có trùng với mã khác không
            List<SanPham> allSanPham = rp.getAll();
            for (SanPham spExist : allSanPham) {
                if (!spExist.getMaSanPham().equalsIgnoreCase(maSanPhamCu)
                        && spExist.getMaSanPham().equalsIgnoreCase(maSanPhamMoi)) {
                    JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại");
                    txtMaSPCT.requestFocus();
                    return;
                }
            }

            // Kiểm tra tên sản phẩm
            String tenSanPham = txtTenSPCT.getText().trim();
            if (tenSanPham.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống");
                txtTenSPCT.requestFocus();
                return;
            }

            // Kiểm tra các combobox thuộc tính
            if (cbbMauSac.getSelectedItem() == null
                    || cbbKichThuoc.getSelectedItem() == null
                    || cbbThuongHieu.getSelectedItem() == null
                    || cbbLoaiGiay.getSelectedItem() == null
                    || cbbChatLieu.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ các thuộc tính!");
                return;
            }

            // Kiểm tra giá bán
            double giaBan;
            try {
                giaBan = Double.parseDouble(txtGiaBan.getText().trim());
                if (giaBan < 0) {
                    JOptionPane.showMessageDialog(this, "Giá bán không được là số âm");
                    txtGiaBan.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá bán phải là số hợp lệ");
                txtGiaBan.requestFocus();
                return;
            }

            // Kiểm tra số lượng
            int soLuong;
            try {
                soLuong = Integer.parseInt(txtSoLuongCT.getText().trim());
                if (soLuong < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng không được là số âm");
                    txtSoLuongCT.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên hợp lệ");
                txtSoLuongCT.requestFocus();
                return;
            }

            // Nếu path ảnh rỗng thì dùng mặc định
            if (path == null || path.trim().isEmpty()) {
                path = "D:\\DuAn1_GiaySneaker\\src\\IMAGE\\default.jpg";
            }

            // Gán dữ liệu
            SanPham sp = new SanPham();
            sp.setMaSanPham(maSanPhamMoi);
            sp.setTenSanPham(tenSanPham);
            sp.setIdMauSac(getIdMauSacByName(cbbMauSac.getSelectedItem().toString()));
            sp.setIdKichCo(getIdKichCoByName(cbbKichThuoc.getSelectedItem().toString()));
            sp.setIdThuongHieu(getIdThuongHieuByName(cbbThuongHieu.getSelectedItem().toString()));
            sp.setIdLoaiGiay(getIdLoaiGiayByName(cbbLoaiGiay.getSelectedItem().toString()));
            sp.setIdChatLieu(getIdChatLieuByName(cbbChatLieu.getSelectedItem().toString()));
            sp.setGiaBan(giaBan);
            sp.setSoLuong(soLuong);
            sp.setImgPath(path);

            boolean success = rp.update(maSanPhamCu, sp);
            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");
                fillTable(rp.getAll());
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSuaSanPhamActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        try {
            int row = tblSanPham.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để sửa");
                return;
            }

            String maSanPhamCu = tblSanPham.getValueAt(row, 1).toString().trim();
            String maSanPhamMoi = txtMaSanPham.getText().trim();
            String tenSanPham = txtTenSanPham.getText().trim();

            if (maSanPhamMoi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Mã sản phẩm không được để trống");
                txtMaSanPham.requestFocus();
                return;
            }

            if (tenSanPham.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống");
                txtTenSanPham.requestFocus();
                return;
            }

            for (SanPham sp : rpSP.getAll()) {
                if (!sp.getMaSanPham().equalsIgnoreCase(maSanPhamCu)
                        && sp.getMaSanPham().equalsIgnoreCase(maSanPhamMoi)) {
                    JOptionPane.showMessageDialog(null, "Mã sản phẩm đã tồn tại");
                    txtMaSanPham.requestFocus();
                    return;
                }
            }

            SanPham sp = new SanPham();
            sp.setMaSanPham(maSanPhamMoi);
            sp.setTenSanPham(tenSanPham);

            if (rpSP.updateSanPham(maSanPhamCu, sp)) {
                JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thành công!");
                fillTableSanPham(rpSP.getAll());
                clearFormSP();
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thất bại!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }

    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        i = tblSanPham.getSelectedRow();
        showDataSP();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        try {
            String maSanPham = txtMaSanPham.getText().trim();
            String tenSanPham = txtTenSanPham.getText().trim();

            if (maSanPham.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Mã sản phẩm không được để trống");
                txtMaSanPham.requestFocus();
                return;
            }

            for (SanPham sp : rp.getAll()) {
                if (sp.getMaSanPham().equalsIgnoreCase(maSanPham)) {
                    JOptionPane.showMessageDialog(null, "Mã sản phẩm đã tồn tại");
                    txtMaSanPham.requestFocus();
                    return;
                }
            }

            if (tenSanPham.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống");
                txtTenSanPham.requestFocus();
                return;
            }

            SanPham sp = new SanPham();
            sp.setMaSanPham(maSanPham);
            sp.setTenSanPham(tenSanPham);

            if (rpSP.addSanPham(sp)) {
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
                fillTableSanPham(rpSP.getAll());
                clearFormSP();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }

    }//GEN-LAST:event_btnThemSPActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        fillTable(rp.getAll());
        fillTableSanPham(rpSP.getAll());
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int row = tblSanPham.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để xoá");
                return;
            }

            String maSanPham = tblSanPham.getValueAt(row, 1).toString();
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc muốn xoá sản phẩm có mã: " + maSanPham + "?",
                    "Xác nhận xoá", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            if (rpSP.deleteSanPham(maSanPham)) {
                JOptionPane.showMessageDialog(null, "Xoá sản phẩm thành công!");
                fillTableSanPham(rpSP.getAll());
                clearFormSP();
            } else {
                JOptionPane.showMessageDialog(null, "Xoá sản phẩm thất bại!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnLamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSPActionPerformed
        // TODO add your handling code here:
        clearFormSP();
    }//GEN-LAST:event_btnLamMoiSPActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        // TODO add your handling code here:
        i = tblThuocTinh.getSelectedRow();
        showDataThuocTinh();
    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void btnThemTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTTActionPerformed
        // TODO add your handling code here:

        String maTT = txtMaThuocTinh.getText().trim();
        String tenTT = txtTenThuocTinh.getText().trim();
        String moTa = txtMoTa.getText().trim();

        if (maTT.isEmpty() || tenTT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã và tên thuộc tính không được để trống");
            return;
        }

        try {
            if (rdoMauSac.isSelected()) {
                if (rpTT.kiemTraTrungMauSac(maTT, tenTT)) {
                    JOptionPane.showMessageDialog(this, "Mã màu hoặc tên màu đã tồn tại");
                    return;
                }
                MauSac ms = new MauSac();
                ms.setMaMau(maTT);
                ms.setTenMau(tenTT);
                ms.setMoTa(moTa);
                if (rpTT.themMauSac(ms)) {
                    JOptionPane.showMessageDialog(this, "Thêm màu sắc thành công");
                    loadMauSacData();
                    fillComboMauSac();
                    EnumUtil.setDanhSachMauSac(rpTT.getAllMauSac());
                }
            } else if (rdoKichThuoc.isSelected()) {
                if (rpTT.kiemTraTrungKichCo(maTT, tenTT)) {
                    JOptionPane.showMessageDialog(this, "Mã kích cỡ hoặc tên kích cỡ đã tồn tại");
                    return;
                }
                KichCo kc = new KichCo();
                kc.setMaKichCo(maTT);
                kc.setKichThuoc(tenTT);
                kc.setMoTa(moTa);
                if (rpTT.themKichCo(kc)) {
                    JOptionPane.showMessageDialog(this, "Thêm kích cỡ thành công");
                    loadKichCoData();
                    fillComboKichCo();
                    EnumUtil.setDanhSachKichCo(rpTT.getAllKichCo());
                }
            } else if (rdoThuongHieu.isSelected()) {
                if (rpTT.kiemTraTrungThuongHieu(maTT, tenTT)) {
                    JOptionPane.showMessageDialog(this, "Mã thương hiệu hoặc tên thương hiệu đã tồn tại");
                    return;
                }
                ThuongHieu th = new ThuongHieu();
                th.setMaThuongHieu(maTT);
                th.setTenThuongHieu(tenTT);
                th.setMoTa(moTa);
                if (rpTT.themThuongHieu(th)) {
                    JOptionPane.showMessageDialog(this, "Thêm thương hiệu thành công");
                    loadThuongHieuData();
                    fillComboThuongHieu();
                    EnumUtil.setDanhSachThuongHieu(rpTT.getAllThuongHieu());
                }
            } else if (rdoChatLieu.isSelected()) {
                if (rpTT.kiemTraTrungChatLieu(maTT, tenTT)) {
                    JOptionPane.showMessageDialog(this, "Mã chất liệu hoặc tên chất liệu đã tồn tại");
                    return;
                }
                ChatLieu cl = new ChatLieu();
                cl.setMaChatLieu(maTT);
                cl.setTenChatLieu(tenTT);
                cl.setMoTa(moTa);
                if (rpTT.themChatLieu(cl)) {
                    JOptionPane.showMessageDialog(this, "Thêm chất liệu thành công");
                    loadChatLieuData();
                    fillComboChatLieu();
                    EnumUtil.setDanhSachChatLieu(rpTT.getAllChatLieu());
                }
            } else if (rdoLoaiGiay.isSelected()) {
                if (rpTT.kiemTraTrungLoaiGiay(maTT, tenTT)) {
                    JOptionPane.showMessageDialog(this, "Mã loại giày hoặc tên loại giày đã tồn tại");
                    return;
                }
                LoaiGiay lg = new LoaiGiay();
                lg.setMaLoaiGiay(maTT);
                lg.setTenLoaiGiay(tenTT);
                lg.setMoTa(moTa);
                if (rpTT.themLoaiGiay(lg)) {
                    JOptionPane.showMessageDialog(this, "Thêm loại giày thành công");
                    loadLoaiGiayData();
                    fillComboLoaiGiay();
                    EnumUtil.setDanhSachLoaiGiay(rpTT.getAllLoaiGiay());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }


    }//GEN-LAST:event_btnThemTTActionPerformed

    private void btnXoaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTTActionPerformed
        // TODO add your handling code here:

        int row = tblThuocTinh.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thuộc tính để xóa");
            return;
        }

        String maTT = tblThuocTinh.getValueAt(row, 1).toString();

        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn xóa thuộc tính này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean success = false;
                if (rdoMauSac.isSelected()) {
                    success = rpTT.xoaMauSac(maTT);
                } else if (rdoKichThuoc.isSelected()) {
                    success = rpTT.xoaKichCo(maTT);
                } else if (rdoThuongHieu.isSelected()) {
                    success = rpTT.xoaThuongHieu(maTT);
                } else if (rdoChatLieu.isSelected()) {
                    success = rpTT.xoaChatLieu(maTT);
                } else if (rdoLoaiGiay.isSelected()) {
                    success = rpTT.xoaLoaiGiay(maTT);
                }

                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa thuộc tính thành công");
                    if (rdoMauSac.isSelected()) {
                        loadMauSacData();
                        fillComboMauSac();
                    } else if (rdoKichThuoc.isSelected()) {
                        loadKichCoData();
                        fillComboKichCo();
                    } else if (rdoThuongHieu.isSelected()) {
                        loadThuongHieuData();
                        fillComboThuongHieu();
                    } else if (rdoChatLieu.isSelected()) {
                        loadChatLieuData();
                        fillComboChatLieu();
                    } else if (rdoLoaiGiay.isSelected()) {
                        loadLoaiGiayData();
                        fillComboLoaiGiay();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thuộc tính thất bại");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnXoaTTActionPerformed

    private void btnSuaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTTActionPerformed
        // TODO add your handling code here:
        int row = tblThuocTinh.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thuộc tính để sửa");
            return;
        }

        String maTT = txtMaThuocTinh.getText().trim();
        String tenTT = txtTenThuocTinh.getText().trim();
        String moTa = txtMoTa.getText().trim();

        if (maTT.isEmpty() || tenTT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã và tên thuộc tính không được để trống");
            return;
        }

        try {
            boolean success = false;
            if (rdoMauSac.isSelected()) {
                MauSac ms = new MauSac();
                ms.setMaMau(maTT);
                ms.setTenMau(tenTT);
                ms.setMoTa(moTa);
                success = rpTT.capNhatMauSac(ms);
            } else if (rdoKichThuoc.isSelected()) {
                KichCo kc = new KichCo();
                kc.setMaKichCo(maTT);
                kc.setKichThuoc(tenTT);
                kc.setMoTa(moTa);
                success = rpTT.capNhatKichCo(kc);
            } else if (rdoThuongHieu.isSelected()) {
                ThuongHieu th = new ThuongHieu();
                th.setMaThuongHieu(maTT);
                th.setTenThuongHieu(tenTT);
                th.setMoTa(moTa);
                success = rpTT.capNhatThuongHieu(th);
            } else if (rdoChatLieu.isSelected()) {
                ChatLieu cl = new ChatLieu();
                cl.setMaChatLieu(maTT);
                cl.setTenChatLieu(tenTT);
                cl.setMoTa(moTa);
                success = rpTT.capNhatChatLieu(cl);
            } else if (rdoLoaiGiay.isSelected()) {
                LoaiGiay lg = new LoaiGiay();
                lg.setMaLoaiGiay(maTT);
                lg.setTenLoaiGiay(tenTT);
                lg.setMoTa(moTa);
                success = rpTT.capNhatLoaiGiay(lg);
            }

            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật thuộc tính thành công");
                if (rdoMauSac.isSelected()) {
                    loadMauSacData();
                    fillComboMauSac();
                } else if (rdoKichThuoc.isSelected()) {
                    loadKichCoData();
                    fillComboKichCo();
                } else if (rdoThuongHieu.isSelected()) {
                    loadThuongHieuData();
                    fillComboThuongHieu();
                } else if (rdoChatLieu.isSelected()) {
                    loadChatLieuData();
                    fillComboChatLieu();
                } else if (rdoLoaiGiay.isSelected()) {
                    loadLoaiGiayData();
                    fillComboLoaiGiay();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thuộc tính thất bại");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaTTActionPerformed

    private void btnLamMoiTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiTTActionPerformed
        // TODO add your handling code here:
        clearFormTT();
    }//GEN-LAST:event_btnLamMoiTTActionPerformed

    private void txtMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanPhamActionPerformed

    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    private void txtMaSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPCTActionPerformed

    private void txtMaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaThuocTinhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoiSP;
    private javax.swing.JButton btnLamMoiTT;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnSuaSanPham;
    private javax.swing.JButton btnSuaTT;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnThemTT;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JButton btnXoaTT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbKichThuoc;
    private javax.swing.JComboBox<String> cbbLoaiGiay;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblIMG;
    private javax.swing.JRadioButton rdoChatLieu;
    private javax.swing.JRadioButton rdoKichThuoc;
    private javax.swing.JRadioButton rdoLoaiGiay;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JRadioButton rdoThuongHieu;
    private javax.swing.JTable tblSPCT;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSPCT;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuongCT;
    private javax.swing.JTextField txtTenSPCT;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTenThuocTinh;
    // End of variables declaration//GEN-END:variables

}
