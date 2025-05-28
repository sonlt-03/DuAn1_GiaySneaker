/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
public class Dao_NhanVien {


    private Connection conn;

    public Dao_NhanVien(Connection conn) {
    this.conn = conn;
}

    public boolean insert(NhanVien nv) throws SQLException {
        String sql = "INSERT INTO NhanVien (maNhanVien, hoTen, gioiTinh, soDienThoai, email, matKhau, idChucVu, vaiTro, trangThai) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getHoTen());
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getSoDienThoai());
            ps.setString(5, nv.getEmail());
            ps.setString(6, nv.getMatKhau());
            ps.setInt(7, nv.getIdChucVu());
            ps.setBoolean(8, nv.getVaiTro() == 1);
            ps.setBoolean(9, nv.getTrangThai() == 1);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(NhanVien nv) throws SQLException {
        String sql = "UPDATE NhanVien SET maNhanVien=?, hoTen=?, gioiTinh=?, soDienThoai=?, email=?, matKhau=?, idChucVu=?, vaiTro=?, trangThai=? " +
                     "WHERE idNhanVien=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
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
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int idNhanVien) throws SQLException {
        String sql = "UPDATE NhanVien SET trangThai = 1 WHERE idNhanVien = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNhanVien);
            return ps.executeUpdate() > 0;
        }
    }

    public List<NhanVien> getAllActive() throws SQLException {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE trangThai = 0";
        try (PreparedStatement ps = conn.prepareStatement(sql);
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
        }
        return list;
    }

    public NhanVien getById(int idNhanVien) throws SQLException {
        String sql = "SELECT * FROM NhanVien WHERE idNhanVien = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNhanVien);
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
        }
        return null;
    }
}

