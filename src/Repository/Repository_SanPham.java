/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.HoaDon;
import Model.SanPham;
import Utils.Dbconnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class Repository_SanPham {
    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "SELECT idSanPham, maSanPham, tenSanPham, idChatLieu, idKieuDang, idLoaiGiay, idThuongHieu, moTa, trangThai, giaBan, soLuong FROM SanPham";
        try (Connection con = Dbconnection.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSanPham(rs.getInt("idSanPham"));
                sp.setMaSanPham(rs.getString("maSanPham"));
                sp.setTenSanPham(rs.getString("tenSanPham"));
                sp.setIdChatLieu(rs.getInt("idChatLieu"));
                sp.setIdKieuDang(rs.getInt("idKieuDang"));
                sp.setIdLoaiGiay(rs.getInt("idLoaiGiay"));
                sp.setIdThuongHieu(rs.getInt("idThuongHieu"));
                sp.setMoTa(rs.getString("moTa"));
                sp.setTrangThai(rs.getInt("trangThai"));
                sp.setGiaBan(rs.getFloat("giaBan"));
                sp.setSoLuong(rs.getInt("soLuong"));
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
