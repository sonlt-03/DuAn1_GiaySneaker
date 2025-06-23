package Dao;

import Model.SanPham;
import Utils.Dbconnection;

import java.sql.*;

public class DAO_SanPhamChiTiet {

    public boolean add(SanPham sp) throws SQLException {
        String sql = "INSERT INTO SanPham (maSanPham, tenSanPham, idMauSac, idKichCo, idThuongHieu, "
                   + "idLoaiGiay, idChatLieu, giaBan, soLuong, imgPath) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setInt(3, sp.getIdMauSac());
            ps.setInt(4, sp.getIdKichCo());
            ps.setInt(5, sp.getIdThuongHieu());
            ps.setInt(6, sp.getIdLoaiGiay());
            ps.setInt(7, sp.getIdChatLieu());
            ps.setDouble(8, sp.getGiaBan());
            ps.setInt(9, sp.getSoLuong());
            ps.setString(10, sp.getImgPath());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(String maSanPhamCu, SanPham sp) throws SQLException {
        String sql = "UPDATE SanPham SET maSanPham=?, tenSanPham=?, idMauSac=?, idKichCo=?, "
                   + "idThuongHieu=?, idLoaiGiay=?, idChatLieu=?, giaBan=?, soLuong=?, imgPath=? "
                   + "WHERE maSanPham=?";

        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setInt(3, sp.getIdMauSac());
            ps.setInt(4, sp.getIdKichCo());
            ps.setInt(5, sp.getIdThuongHieu());
            ps.setInt(6, sp.getIdLoaiGiay());
            ps.setInt(7, sp.getIdChatLieu());
            ps.setDouble(8, sp.getGiaBan());
            ps.setInt(9, sp.getSoLuong());
            ps.setString(10, sp.getImgPath());
            ps.setString(11, maSanPhamCu);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(String maSanPham) {
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
