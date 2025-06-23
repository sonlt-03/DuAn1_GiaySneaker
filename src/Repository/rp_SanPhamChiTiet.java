/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Dao.DAO_SanPhamChiTiet;
import Model.SanPham;
import Utils.Dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sownj
 */
public class rp_SanPhamChiTiet {

    private DAO_SanPhamChiTiet dao = new DAO_SanPhamChiTiet();

    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> listSP = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSanPham(rs.getInt("idSanPham"));
                sp.setMaSanPham(rs.getString("maSanPham"));
                sp.setTenSanPham(rs.getString("tenSanPham"));
                sp.setIdMauSac(rs.getInt("idMauSac"));
                sp.setIdKichCo(rs.getInt("idKichCo"));
                sp.setIdKieuDang(rs.getInt("idKieuDang"));
                sp.setIdThuongHieu(rs.getInt("idThuongHieu"));
                sp.setIdLoaiGiay(rs.getInt("idLoaiGiay"));
                sp.setIdChatLieu(rs.getInt("idChatLieu"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                sp.setSoLuong(rs.getInt("soLuong"));
                sp.setImgPath(rs.getString("imgPath"));
                listSP.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSP;
    }

    
    public Boolean add(SanPham sp) {
       String sql = "INSERT INTO SanPham(maSanPham, tenSanPham, idMauSac, idKichCo, idThuongHieu, "
           + "idLoaiGiay, idChatLieu, giaBan, soLuong, imgPath) " // thêm imgPath
           + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Dbconnection.getConnection()) {
           PreparedStatement pst = conn.prepareStatement(sql);
pst.setString(1, sp.getMaSanPham());
pst.setString(2, sp.getTenSanPham());
pst.setInt(3, sp.getIdMauSac());
pst.setInt(4, sp.getIdKichCo());
pst.setInt(5, sp.getIdThuongHieu());
pst.setInt(6, sp.getIdLoaiGiay());
pst.setInt(7, sp.getIdChatLieu());
pst.setDouble(8, sp.getGiaBan());
pst.setInt(9, sp.getSoLuong());
pst.setString(10, sp.getImgPath());


            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maSanPham) {
        return dao.delete(maSanPham);
    }

    public boolean update(String maSanPhamCu, SanPham sp) throws SQLException {
        return dao.update(maSanPhamCu, sp);
    }

}
