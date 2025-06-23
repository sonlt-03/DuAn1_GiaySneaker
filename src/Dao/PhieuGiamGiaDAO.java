
package Dao;

import Model.PhieuGiamGia;
import Utils.Dbconnection;
import Utils.XJdbc;
import Utils.XQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PhieuGiamGiaDAO {
    String sql = "SELECT idPhieuGiamGia, maPhieu, tenPhieu, tenHinhThucGG, giaTri, soLuong, ngayBatDau, ngayKetThuc, dieuKienApDung, trangThai FROM PhieuGiamGia;";
    String create_SQL = "INSERT INTO PhieuGiamGia(maPhieu, tenPhieu, tenHinhThucGG, giaTri, soLuong, ngayBatDau, ngayKetThuc, dieuKienApDung, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    String update_SQL = "UPDATE PhieuGiamGia SET maPhieu=?, tenPhieu=?, tenHinhThucGG=?, giaTri=?, soLuong=?, ngayBatDau=?, ngayKetThuc=?, dieuKienApDung=?, trangThai=? WHERE idPhieuGiamGia=?";
    String update_status_SQL = "UPDATE PhieuGiamGia SET trangThai= 0 WHERE idPhieuGiamGia=?";

    public List<PhieuGiamGia> getAll() {
        return XQuery.getBeanList(PhieuGiamGia.class, sql);
    }
    public PhieuGiamGia create(PhieuGiamGia p) {
        Object[] values ={
                p.getMaPhieu(),
                p.getTenPhieu(),
                p.getTenHinhThucGG(),
                p.getGiaTri(),
                p.getSoLuong(),
                p.getNgayBatDau(),
                p.getNgayKetThuc(),
                p.getDieuKienApDung(),
                p.isTrangThai()
            };
            XJdbc.executeUpdate(create_SQL,values);
                    return p;
    }
    public PhieuGiamGia update(PhieuGiamGia p) {
    Object[] values = {
        p.getMaPhieu(),
        p.getTenPhieu(),
        p.getTenHinhThucGG(),
        p.getGiaTri(),
        p.getSoLuong(),
        p.getNgayBatDau(),
        p.getNgayKetThuc(),
        p.getDieuKienApDung(),
        p.isTrangThai(),
        p.getIdPhieuGiamGia()
    };
    XJdbc.executeUpdate(update_SQL, values);
    return p;
    }
    public PhieuGiamGia delete(PhieuGiamGia p){
        Object[] values = {
            p.getIdPhieuGiamGia()
        };
        XJdbc.executeUpdate(update_status_SQL, values);
        return p;
    }
    
    public boolean isMaPhieuExists(String maPhieu) {
    String sql = "SELECT COUNT(*) FROM PhieuGiamGia WHERE maPhieu = ?";
    try (Connection conn = Dbconnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, maPhieu);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


}
