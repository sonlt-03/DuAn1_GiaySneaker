/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Utils.Dbconnection;
import java.sql.Connection;
import java.util.ArrayList;
import model.NhanVien;

/**
 *
 * @author 5than
 */
public class Repository_NhanVien {


    public ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE trangThai = 0";
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
                nv.setVaiTro(rs.getBoolean("vaiTro") ? 1 : 0);
                nv.setTrangThai(rs.getBoolean("trangThai") ? 1 : 0);
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public NhanVien login(String email, String matKhau) {
        String sql = "SELECT * FROM NhanVien WHERE email = ? AND matKhau = ? AND trangThai = 0";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, matKhau);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setIdNhanVien(rs.getInt("idNhanVien"));
                    nv.setMaNhanVien(rs.getString("maNhanVien"));
                    nv.setHoTen(rs.getString("hoTen"));
                    nv.setGioiTinh(rs.getString("gioiTinh"));
                    nv.setSoDienThoai(rs.getString("soDienThoai"));
                    nv.setEmail(rs.getString("email"));
                    nv.setMatKhau(rs.getString("matKhau"));
                    nv.setIdChucVu(rs.getInt("idChucVu"));
                    nv.setVaiTro(rs.getBoolean("vaiTro") ? 1 : 0);
                    nv.setTrangThai(rs.getBoolean("trangThai") ? 1 : 0);
                    return nv;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int add(NhanVien nv) {
        String sql = "INSERT INTO NhanVien(maNhanVien, hoTen, gioiTinh, soDienThoai, email, matKhau, idChucVu, vaiTro, trangThai) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getHoTen());
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getSoDienThoai());
            ps.setString(5, nv.getEmail());
            ps.setString(6, nv.getMatKhau());
            ps.setInt(7, nv.getIdChucVu());
            ps.setBoolean(8, nv.getVaiTro() == 1);
            ps.setBoolean(9, nv.getTrangThai() == 1);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(NhanVien nv) {
        String sql = "UPDATE NhanVien SET maNhanVien = ?, hoTen = ?, gioiTinh = ?, soDienThoai = ?, email = ?, matKhau = ?, idChucVu = ?, vaiTro = ?, trangThai = ? " +
                     "WHERE idNhanVien = ?";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getHoTen());
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getSoDienThoai());
            ps.setString(5, nv.getEmail());
            ps.setString(6, nv.getMatKhau());
            ps.setInt(7, nv.getIdChucVu());
            ps.setBoolean(8, nv.getVaiTro() == 1);
            ps.setBoolean(9, nv.getTrangThai() == 1);
            ps.setInt(10, nv.getIdNhanVien());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // "Xóa" nhân viên = set trangThai = 1
    public boolean delete(String maNhanVien) {
        String sql = "UPDATE NhanVien SET trangThai = 1 WHERE maNhanVien = ?";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maNhanVien);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<NhanVien> searchByPhone(String keyword) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE soDienThoai LIKE ? AND trangThai = 0";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
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
                    nv.setVaiTro(rs.getBoolean("vaiTro") ? 1 : 0);
                    nv.setTrangThai(rs.getBoolean("trangThai") ? 1 : 0);
                    list.add(nv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<NhanVien> getByStatus(int status) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE trangThai = ?";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, status);
            try (ResultSet rs = ps.executeQuery()) {
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
                    nv.setVaiTro(rs.getBoolean("vaiTro") ? 1 : 0);
                    nv.setTrangThai(rs.getBoolean("trangThai") ? 1 : 0);
                    list.add(nv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
