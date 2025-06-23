package Dao;

import Model.SanPham;
import Utils.Dbconnection;

import java.sql.*;
import java.util.ArrayList;

public class DAO_SanPham {

    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> listSP = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSanPham(rs.getInt("idSanPham"));
                sp.setMaSanPham(rs.getString("maSanPham"));
                sp.setTenSanPham(rs.getString("tenSanPham"));
                sp.setSoLuong(rs.getInt("soLuong"));
                sp.setImgPath(rs.getString("imgPath"));
                listSP.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSP;
    }

    public boolean addSanPham(SanPham sp) {
        String sql = "INSERT INTO SanPham (maSanPham, tenSanPham, imgPath) VALUES (?, ?, ?)";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setString(3, sp.getImgPath());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSanPham(String maSanPhamCu, SanPham sp) {
        String sql = "UPDATE SanPham SET maSanPham = ?, tenSanPham = ?, imgPath = ? WHERE maSanPham = ?";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setString(3, sp.getImgPath());
            ps.setString(4, maSanPhamCu);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSanPhamByMa(String maSanPham) {
        String sql = "DELETE FROM SanPham WHERE maSanPham = ?";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maSanPham);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
