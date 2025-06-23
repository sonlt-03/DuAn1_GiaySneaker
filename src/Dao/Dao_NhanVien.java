/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Utils.Dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.NhanVien;

public class Dao_NhanVien {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        sql = "SELECT idNhanVien, maNhanVien, hoTen, gioiTinh, soDienThoai, email, matKhau, idChucVu, vaiTro, trangThai FROM NhanVien WHERE trangThai = 0";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt("idNhanVien"),
                        rs.getString("maNhanVien"),
                        rs.getString("hoTen"),
                        rs.getString("gioiTinh"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        rs.getString("matKhau"),
                        rs.getInt("idChucVu"),
                        rs.getBoolean("vaiTro") ? 1 : 0,
                        rs.getBoolean("trangThai") ? 1 : 0
                );
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int add(NhanVien n) {
        sql = "INSERT INTO NhanVien(maNhanVien, hoTen, gioiTinh, soDienThoai, email, matKhau, idChucVu, vaiTro, trangThai) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, n.getMaNhanVien());
            pstm.setString(2, n.getHoTen());
            pstm.setString(3, n.getGioiTinh());
            pstm.setString(4, n.getSoDienThoai());
            pstm.setString(5, n.getEmail());
            pstm.setString(6, n.getMatKhau());
            pstm.setInt(7, n.getIdChucVu());
            pstm.setBoolean(8, n.getVaiTro() == 1);
            pstm.setBoolean(9, n.getTrangThai() == 1);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int update(NhanVien n) {
        sql = "UPDATE NhanVien SET maNhanVien=?, hoTen=?, gioiTinh=?, soDienThoai=?, email=?, matKhau=?, idChucVu=?, vaiTro=?, trangThai=? WHERE idNhanVien=?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, n.getMaNhanVien());
            pstm.setString(2, n.getHoTen());
            pstm.setString(3, n.getGioiTinh());
            pstm.setString(4, n.getSoDienThoai());
            pstm.setString(5, n.getEmail());
            pstm.setString(6, n.getMatKhau());
            pstm.setInt(7, n.getIdChucVu());
            pstm.setBoolean(8, n.getVaiTro() == 1);
            pstm.setBoolean(9, n.getTrangThai() == 1);
            pstm.setInt(10, n.getIdNhanVien());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public boolean delete(String maNV) {
        sql = "UPDATE NhanVien SET trangThai = 1 WHERE maNhanVien = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maNV);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<NhanVien> search(String phoneNumber) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE soDienThoai LIKE ? AND trangThai = 0";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + phoneNumber + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt("idNhanVien"),
                        rs.getString("maNhanVien"),
                        rs.getString("hoTen"),
                        rs.getString("gioiTinh"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        rs.getString("matKhau"),
                        rs.getInt("idChucVu"),
                        rs.getBoolean("vaiTro") ? 1 : 0,
                        rs.getBoolean("trangThai") ? 1 : 0
                );
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public NhanVien checkLogin(String email, String matKhau) {
        String sql = "SELECT * FROM NhanVien WHERE email = ? AND matKhau = ? AND trangThai = 0";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new NhanVien(
                        rs.getInt("idNhanVien"),
                        rs.getString("maNhanVien"),
                        rs.getString("hoTen"),
                        rs.getString("gioiTinh"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        rs.getString("matKhau"),
                        rs.getInt("idChucVu"),
                        rs.getInt("vaiTro"),
                        rs.getInt("trangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NhanVien> getByStatus(int status) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE trangThai = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt("idNhanVien"),
                        rs.getString("maNhanVien"),
                        rs.getString("hoTen"),
                        rs.getString("gioiTinh"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        rs.getString("matKhau"),
                        rs.getInt("idChucVu"),
                        rs.getBoolean("vaiTro") ? 1 : 0,
                        rs.getBoolean("trangThai") ? 1 : 0
                );
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
}
