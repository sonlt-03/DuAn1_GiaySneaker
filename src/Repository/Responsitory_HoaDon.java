/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.GioHang;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Model.LichSuHoaDon;
import Model.PhieuGiamGia;
import Model.PhuongThucThanhToan;
import Utils.Dbconnection;
import static Utils.Dbconnection.getConnection;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;

/**
 *
 * @author nguye
 */
public class Responsitory_HoaDon {
   
    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT idHoaDon, maHoaDon, idNhanVien, idKhachHang, idPhieuGiamGia, idPTTT, ngayTao, tongTien, ghiChu, trangThai FROM HoaDon Where trangThai = 0";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdHoaDon(rs.getInt("idHoaDon"));
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setIdNhanVien(rs.getInt("idNhanVien"));
                hd.setIdKhachHang(rs.getInt("idKhachHang"));
                hd.setIdPhieuGiamGia(rs.getInt("idPhieuGiamGia"));
                hd.setIdPTTT(rs.getInt("idPTTT"));
                hd.setNgayTao(rs.getString("ngayTao"));
                hd.setTongTien(rs.getDouble("tongTien"));
                hd.setGhiChu(rs.getString("ghiChu"));
                hd.setTrangThai(rs.getBoolean("trangThai"));
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
//    public ArrayList<HoaDonChiTiet> getAllHDCT() {
//        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
//        String sql = "SELECT idHoaDon, idChiTietSP, soLuong, donGia, thanhTien, ngayMua, ghiChu FROM HoaDonChiTiet";
//        try (Connection con = Dbconnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                HoaDonChiTiet hdct = new HoaDonChiTiet();
//                hdct.setIdHoaDon(rs.getInt("idHoaDon"));
//                hdct.setIdChiTietSP(rs.getInt("idChiTietSP"));
//                hdct.setSoLuong(rs.getInt("soLuong"));
//                hdct.setDonGia(rs.getDouble("donGia"));
//                hdct.setThanhTien(rs.getDouble("thanhTien"));
//                hdct.setNgayMua(rs.getString("ngayMua"));
//                hdct.setGhiChu(rs.getString("ghiChu"));
//                list.add(hdct);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    
    public ArrayList<LichSuHoaDon> getLSHD() {
        ArrayList<LichSuHoaDon> list = new ArrayList<>();
        String sql = "SELECT idHoaDon, maHoaDon, idNhanVien, idKhachHang, idPhieuGiamGia, idPTTT, ngayTao, tongTien, ghiChu, trangThai FROM HoaDon";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LichSuHoaDon lshd = new LichSuHoaDon();
                lshd.setIdHoaDon(rs.getInt("idHoaDon"));
                lshd.setMaHoaDon(rs.getString("maHoaDon"));
                lshd.setIdNhanVien(rs.getInt("idNhanVien"));
                lshd.setIdKhachHang(rs.getInt("idKhachHang"));
                lshd.setIdPhieuGiamGia(rs.getInt("idPhieuGiamGia"));
                lshd.setIdPTTT(rs.getInt("idPTTT"));
                lshd.setNgayTao(rs.getDate("ngayTao"));
                lshd.setTongTien(rs.getFloat("tongTien"));
                lshd.setGhiChu(rs.getString("ghiChu"));
                lshd.setTrangThai(rs.getBoolean("trangThai"));
                list.add(lshd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean updateTrangThaiHoaDon(int idHoaDon, boolean trangThai) throws SQLException {
    String sql = "UPDATE HoaDon SET trangThai = ? WHERE idHoaDon = ?";
    try (Connection con = Dbconnection.getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setBoolean(1, trangThai);
        ps.setInt(2, idHoaDon);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    
    ////////////////////ADDĐ////////////////////////////////////
    public int add(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (maHoaDon, idNhanVien, idKhachHang, idPhieuGiamGia, idPTTT, ngayTao, tongTien, ghiChu, trangThai) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, hd.getMaHoaDon());
                ps.setInt(2, hd.getIdNhanVien());
                ps.setInt(3, hd.getIdKhachHang());
                ps.setInt(4, hd.getIdPhieuGiamGia());
                ps.setInt(5, hd.getIdPTTT());
                ps.setString(6, hd.getNgayTao());
                ps.setDouble(7, hd.getTongTien());
                ps.setString(8, hd.getGhiChu());
                ps.setBoolean(9, hd.getTrangThai());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int getKhachHangIdBySDT(String sdt) throws SQLException {
        String sql = "SELECT idKhachHang FROM KhachHang WHERE soDienThoai = ?";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idKhachHang");
            }
        }
        return -1;  
    }

    public int addNewKhachHang(String tenKhachHang, String sdt) throws SQLException {
    String sql = "INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai) VALUES (?, ?, ?)";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        String maKhachHang = "KH" + System.currentTimeMillis(); 
        ps.setString(1, maKhachHang);  
        ps.setString(2, tenKhachHang);
        ps.setString(3, sdt);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet rs = ps.getGeneratedKeys(); 
            if (rs.next()) {
                return rs.getInt(1); 
            }
        }
        return -1;  // Nếu không thêm được khách hàng
    }
}


    
 //////////////UPDATE/////////////////////////////////////////////////   
    public boolean update(HoaDon hoaDon) {
    String sql = "UPDATE HoaDon SET maHoaDon = ?, idNhanVien = ?, idKhachHang = ?, idPhieuGiamGia = ?, idPTTT = ?, ngayTao = ?, tongTien = ?, ghiChu = ? WHERE maHoaDon = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, hoaDon.getMaHoaDon());
        ps.setInt(2, hoaDon.getIdNhanVien());
        ps.setInt(3, hoaDon.getIdKhachHang());  // Sử dụng idKhachHang
        ps.setInt(4, hoaDon.getIdPhieuGiamGia());
        ps.setInt(5, hoaDon.getIdPTTT());
        ps.setString(6, hoaDon.getNgayTao());
        ps.setDouble(7, hoaDon.getTongTien());
        ps.setString(8, hoaDon.getGhiChu());
        ps.setString(9, hoaDon.getMaHoaDon());  // Đảm bảo cập nhật đúng hóa đơn
        
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public boolean updateCustomer(String tenKhachHang, String sdt, int idKhachHang) {
    String sql = "UPDATE KhachHang SET HoTen = ?, SoDienThoai = ? WHERE idKhachHang = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, tenKhachHang);
        stmt.setString(2, sdt);
        stmt.setInt(3, idKhachHang);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public boolean updateKhachHang(int idKhachHang, String tenKhachHang, String sdt) {
    String sql = "UPDATE KhachHang SET HoTen = ?, SoDienThoai = ? WHERE idKhachHang = ?";
    try (Connection conn = getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, tenKhachHang);
        stmt.setString(2, sdt);
        stmt.setInt(3, idKhachHang);
        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;  // Trả về true nếu cập nhật thành công
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}





    
    public int getIdKhachHangBySDTAndTen(String sdt, String tenKhachHang) {
    String sql = "SELECT idKhachHang FROM KhachHang WHERE soDienThoai = ? AND hoTen = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, sdt);
        ps.setString(2, tenKhachHang);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("idKhachHang");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; 
}






    ///////////////DELETE/////////////////////////////////////////
    public boolean delete(int idHoaDon) {
    String sqlDeleteCTHD = "DELETE FROM HoaDonChiTiet WHERE idHoaDon = ?";
    String sqlDeleteHD = "DELETE FROM HoaDon WHERE idHoaDon = ?";

    try (Connection con = Dbconnection.getConnection()) {
        con.setAutoCommit(false);

        try (
            PreparedStatement ps1 = con.prepareStatement(sqlDeleteCTHD);
            PreparedStatement ps2 = con.prepareStatement(sqlDeleteHD)
        ) {
            ps1.setInt(1, idHoaDon);
            ps1.executeUpdate();

            ps2.setInt(1, idHoaDon);
            int rowsAffected = ps2.executeUpdate();
            con.commit();
            return rowsAffected > 0;

        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
            return false;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    
    
    
   public KhachHang getKhachHangInfoById(int id) throws SQLException {
    String sql = "SELECT hoTen, soDienThoai FROM KhachHang WHERE idKhachHang = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)){
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String tenKhachHang = rs.getString("hoTen");
            String soDienThoai = rs.getString("soDienThoai");
            return new KhachHang(id, "Không rõ", tenKhachHang, null, null, soDienThoai, null, null, false);
        }
            return new KhachHang(id, "Không rõ", "Khong ro", null, null, "Khong ro", null, null, false);
    } catch(SQLException e){
        e.printStackTrace();
            return new KhachHang(id, "Không rõ", "Khong ro", null, null, "Khong ro", null, null, false);
    }

}
   
   public String getTenNhanVienById(int id)throws SQLException {
String sql = "SELECT hoTen FROM NhanVien WHERE idNhanVien = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)){
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("hoTen");
        }
        return "Không rõ";
    } catch(SQLException e){
        e.printStackTrace();
        return "Không rõ";   
        }
    }
   
   public ArrayList<PhieuGiamGia> getAllPhieuGiamGia() {
    ArrayList<PhieuGiamGia> list = new ArrayList<>();
    String sql = "SELECT idPhieuGiamGia, maPhieu, tenPhieu, tenHinhThucGG, giaTri, soLuong, ngayBatDau, ngayKetThuc, dieuKienApDung, trangThai FROM PhieuGiamGia where trangThai = 1";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            PhieuGiamGia pgg = new PhieuGiamGia();
            pgg.setIdPhieuGiamGia(rs.getInt("idPhieuGiamGia"));
            pgg.setMaPhieu(rs.getString("maPhieu"));
            pgg.setTenPhieu(rs.getString("tenPhieu"));
            pgg.setTenHinhThucGG(rs.getString("tenHinhThucGG"));
            pgg.setGiaTri(rs.getBigDecimal("giaTri"));
            pgg.setSoLuong(rs.getInt("soLuong"));
            pgg.setNgayBatDau(rs.getDate("ngayBatDau"));
            pgg.setNgayKetThuc(rs.getDate("ngayKetThuc"));
            pgg.setDieuKienApDung(rs.getString("dieuKienApDung"));
            pgg.setTrangThai(rs.getBoolean("trangThai"));
            list.add(pgg);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
//   public ArrayList<PhieuGiamGia> getAllPhieuGiamGia() {
//    ArrayList<PhieuGiamGia> list = new ArrayList<>();
//    String sql = "SELECT tenPhieu FROM PhieuGiamGia";  // Lấy chỉ tên phiếu giảm giá
//    try (Connection con = Dbconnection.getConnection();
//         PreparedStatement ps = con.prepareStatement(sql);
//         ResultSet rs = ps.executeQuery()) {
//        while (rs.next()) {
//            PhieuGiamGia pgg = new PhieuGiamGia();
//            pgg.setTenPhieu(rs.getString("tenPhieu"));  // Chỉ gán tên phiếu
//            list.add(pgg);
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    return list;
//}

   
   public ArrayList<PhuongThucThanhToan> getAllPhuongThucThanhToan() {
        ArrayList<PhuongThucThanhToan> list = new ArrayList<>();
        String sql = "SELECT idPTTT, maPTTT, tenPTTT, moTa, trangThai FROM PhuongThucThanhToan";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhuongThucThanhToan pttt = new PhuongThucThanhToan();
                pttt.setIdPTTT(rs.getInt("idPTTT"));
                pttt.setMaPTTT(rs.getString("maPTTT"));
                pttt.setTenPTTT(rs.getString("tenPTTT"));
                pttt.setMoTa(rs.getString("moTa"));
                pttt.setTrangThai(rs.getInt("trangThai"));
                list.add(pttt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
   
   public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT idNhanVien, maNhanVien, hoTen, gioiTinh, soDienThoai, email, matKhau, idChucVu, vaiTro, trangThai FROM NhanVien Where trangThai = 0";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIdNhanVien(rs.getInt("idNhanVien"));
                nv.setMaNhanVien(rs.getString("maNhanVien"));
                nv.setHoTen(rs.getString("hoTen"));
                nv.setGioiTinh(rs.getString("gioiTinh"));
                nv.setSoDienThoai(rs.getString("soDienThoai"));
                nv.setEmail(rs.getString("email"));
                nv.setMatKhau(rs.getString("matKhau"));
                nv.setIdChucVu(rs.getInt("idChucVu"));
                nv.setVaiTro(rs.getInt("vaiTro"));
                nv.setTrangThai(rs.getInt("trangThai"));
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }   
   public List<HoaDonChiTiet> getHoaDonChiTietByMa(int idHoaDon) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDonChiTiet WHERE IdHoaDon = ?";
        
        try (Connection conn = Utils.Dbconnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet(
                    rs.getInt("id"),
                    rs.getInt("IdHoaDon"),
                    rs.getInt("IdSanPham"),
                    rs.getInt("SoLuong"),
                    rs.getDouble("DonGia"),
                    rs.getDouble("GiamGia"),
                    rs.getDouble("ThanhTien")
                );
                list.add(hdct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
   
    public boolean deleteGioHang(String maSanPham) {
    String sql = "DELETE FROM GioHang WHERE maSanPham = ?";
    try (Connection con = Utils.Dbconnection.getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, maSanPham);
        int result = ps.executeUpdate();
        return result > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



   
   public ArrayList<GioHang> getAllGioHang() {
        ArrayList<GioHang> list = new ArrayList<>();
        String sql = "SELECT idSanPham, maSanPham, tenSanPham, soLuong, donGia, giamGia, thanhTien FROM GioHang";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GioHang gh = new GioHang();
                gh.setIdSanPham(rs.getInt("idSanPham"));
                gh.setMaSanPham(rs.getString("maSanPham"));
                gh.setTenSanPham(rs.getString("tenSanPham"));
                gh.setSoLuong(rs.getInt("soLuong"));
                gh.setDonGia(rs.getDouble("donGia"));
                gh.setGiamGia(rs.getDouble("giamGia"));
                gh.setThanhTien(rs.getDouble("thanhTien"));
                list.add(gh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }   
   
   public boolean giamSoLuongSanPham(String idSanPham) {
    String sql = "UPDATE SanPham SET soLuong = soLuong - 1 WHERE idSanPham = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, idSanPham);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
   
   public int getSoLuongTonKho(int idSanPham) {
    String sql = "SELECT soLuong FROM SanPham WHERE idSanPham = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idSanPham);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("soLuong");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

public void capNhatSoLuongTonKho(int idSanPham, int soLuongMoi) {
    String sql = "UPDATE SanPham SET soLuong = ? WHERE idSanPham = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, soLuongMoi);
        ps.setInt(2, idSanPham);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public boolean themSoLuongSanPham(String idSanPham, int soLuongThem) {
    String sql = "UPDATE SanPham SET soLuong = soLuong + ? WHERE idSanPham = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, soLuongThem);
        ps.setString(2, idSanPham);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean giamSoLuongSanPhamTheoSL(int idSanPham, int soLuongGiam) {
    String sql = "UPDATE SanPham SET soLuong = soLuong - ? WHERE idSanPham = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, soLuongGiam);
        ps.setInt(2, idSanPham);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


   
   public boolean insertGioHang(GioHang gh) {
    String sql = "INSERT INTO GioHang (idSanPham, maSanPham, tenSanPham, soLuong, donGia, giamGia, thanhTien) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, gh.getIdSanPham());
        ps.setString(2, gh.getMaSanPham());
        ps.setString(3, gh.getTenSanPham());
        ps.setInt(4, gh.getSoLuong());
        ps.setDouble(5, gh.getDonGia());
        ps.setDouble(6, gh.getGiamGia());
        ps.setDouble(7, gh.getThanhTien());
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

   public boolean clearGioHang() {
    String sql = "DELETE FROM GioHang";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

   
   public boolean addHoaDonChiTiet(int idHoaDon, GioHang sp) {
    String sql = "INSERT INTO HoaDonChiTiet (idHoaDon, idSanPham, soLuong, donGia, giamGia, thanhTien) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idHoaDon);
        ps.setInt(2, sp.getIdSanPham());
        ps.setInt(3, sp.getSoLuong());
        ps.setDouble(4, sp.getDonGia());
        ps.setDouble(5, sp.getGiamGia());
        ps.setDouble(6, sp.getThanhTien());
        ps.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
   
   public int getIdHoaDonByMa(String maHoaDon) {
    String sql = "SELECT idHoaDon FROM HoaDon WHERE maHoaDon = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, maHoaDon);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("idHoaDon");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;
}
   
   public List<GioHang> getGioHangByHoaDon(int idHoaDon) {
    List<GioHang> list = new ArrayList<>();
    String sql = "SELECT sp.idSanPham, sp.maSanPham, sp.tenSanPham, hdct.soLuong, hdct.donGia, hdct.giamGia, hdct.thanhTien " +
                 "FROM HoaDonChiTiet hdct " +
                 "JOIN SanPham sp ON hdct.idSanPham = sp.idSanPham " +
                 "WHERE hdct.idHoaDon = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idHoaDon);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            GioHang gh = new GioHang();
            gh.setIdSanPham(rs.getInt("idSanPham"));
            gh.setMaSanPham(rs.getString("maSanPham"));
            gh.setTenSanPham(rs.getString("tenSanPham"));
            gh.setSoLuong(rs.getInt("soLuong"));
            gh.setDonGia(rs.getDouble("donGia"));
            gh.setGiamGia(rs.getDouble("giamGia"));
            gh.setThanhTien(rs.getDouble("thanhTien"));
            list.add(gh);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
   
   public boolean deleteHDCTByHoaDon(int idHoaDon) {
    String sql = "DELETE FROM HoaDonChiTiet WHERE idHoaDon = ?";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idHoaDon);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

   public boolean insertHDCTs(List<HoaDonChiTiet> list) {
    String sql = "INSERT INTO HoaDonChiTiet (idHoaDon, idSanPham, soLuong, donGia, giamGia, thanhTien) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        for (HoaDonChiTiet hdct : list) {
            ps.setInt(1, hdct.getIdHoaDon());
            ps.setInt(2, hdct.getIdSanPham());
            ps.setInt(3, hdct.getSoLuong());
            ps.setDouble(4, hdct.getDonGia());
            ps.setDouble(5, hdct.getGiamGia());
            ps.setDouble(6, hdct.getThanhTien());
            ps.addBatch();
        }

        int[] results = ps.executeBatch();
        return results.length == list.size();

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
   
   public boolean updateTrangThaiHoaDonLS(int idHoaDon, boolean trangThai) throws SQLException {
    String sql = "UPDATE HoaDon SET trangThai = ? WHERE idHoaDon = ?";
    try (Connection con = Dbconnection.getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setBoolean(1, trangThai);
        ps.setInt(2, idHoaDon);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
   
   public List<GioHang> getAllGioHangByHoaDon(int idHoaDon) {
    List<GioHang> list = new ArrayList<>();
    String sql = "SELECT ct.idSanPham, sp.maSanPham, sp.tenSanPham, ct.soLuong, ct.donGia, ct.giamGia, ct.thanhTien " +
                 "FROM HoaDonChiTiet ct " +
                 "JOIN SanPham sp ON ct.idSanPham = sp.idSanPham " +
                 "WHERE ct.idHoaDon = ?";

    try (Connection con = Dbconnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idHoaDon);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            GioHang gh = new GioHang();
            gh.setIdSanPham(rs.getInt("idSanPham"));
            gh.setMaSanPham(rs.getString("maSanPham"));
            gh.setTenSanPham(rs.getString("tenSanPham"));
            gh.setSoLuong(rs.getInt("soLuong"));
            gh.setDonGia(rs.getDouble("donGia"));
            gh.setGiamGia(rs.getDouble("giamGia"));
            gh.setThanhTien(rs.getDouble("thanhTien"));
            list.add(gh);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}

   public boolean giamSoLuongPhieu(int idPhieuGiamGia) {
    String sql = "UPDATE PhieuGiamGia SET soLuong = soLuong - 1 WHERE idPhieuGiamGia = ? AND soLuong > 0";
    try (Connection conn = Dbconnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idPhieuGiamGia);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

   



}



//   public ArrayList<PhieuGiamGia> getTenPhieu(){
//       ArrayList<PhieuGiamGia> tenPhieu = new ArrayList<>();
//               String sql = "SELECT tenPhieu FROM PhieuGiamGia";
//        try (Connection con = Dbconnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()){
//                PhieuGiamGia pggTP = new PhieuGiamGia();
//                pggTP.setTenPhieu(rs.getString("tenPhieu"));
//                tenPhieu.add(pggTP);
//            }
//            }catch (SQLException e){
//                    e.printStackTrace();
//                    }
//        return tenPhieu;
//   }
   
//   public PhieuGiamGia getPhieuGiamGiaById(int idPhieuGiamGia) throws SQLException {
//    String sql = "SELECT * FROM PhieuGiamGia WHERE idPhieuGiamGia = ?";
//    try (Connection con = Dbconnection.getConnection();
//         PreparedStatement ps = con.prepareStatement(sql)) {
//        ps.setInt(1, idPhieuGiamGia);
//        ResultSet rs = ps.executeQuery();
//        if (rs.next()) {
//            PhieuGiamGia pgg = new PhieuGiamGia();
//            pgg.setIdPhieuGiamGia(rs.getInt("idPhieuGiamGia"));
//            pgg.setMaPhieu(rs.getString("maPhieu"));
//            pgg.setTenPhieu(rs.getString("tenPhieu"));
//            pgg.setIdHinhThucGG(rs.getInt("idHinhThucGG"));
//            pgg.setGiaTri(rs.getDouble("giaTri"));
//            pgg.setSoLuong(rs.getInt("soLuong"));
//            pgg.setNgayBatDau(rs.getString("ngayBatDau"));
//            pgg.setNgayKetThuc(rs.getString("ngayKetThuc"));
//            pgg.setDieuKienApDung(rs.getString("dieuKienApDung"));
//            pgg.setTrangThai(rs.getInt("trangThai"));
//            return pgg;
//        }
//        return null;
//    }
//}



