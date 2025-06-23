package Dao;

import Model.KhachHang;
import Utils.Dbconnection;
import Utils.XJdbc;
import Utils.XQuery;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

public class KhachHang_DAO {
    String sql = "SELECT idKhachHang, maKhachHang, hoTen, gioiTinh, diaChi, soDienThoai, email, ghiChu, trangThai FROM KhachHang";
    String find_by_hoTen_sql ="SELECT * FROM KhachHang WHERE hoTen LIKE ? ";
    String find_by_soDienThoai_sql ="SELECT * FROM KhachHang WHERE soDienThoai LIKE ? ";
    String create_SQL = "INSERT INTO KhachHang(maKhachHang,hoTen,gioiTinh,diaChi,soDienThoai,email,ghiChu,trangThai) VALUES(?,?,?,?,?,?,?,?)";
    String update_SQL = "UPDATE KhachHang SET maKhachHang=?, hoTen=?,gioiTinh=?,diaChi=?,soDienThoai=?,email=?,ghiChu=?,trangThai=? WHERE idKhachHang=?";
    String update_status_SQL = "UPDATE KhachHang SET trangThai= 0 WHERE idKhachHang=?";

    public List<KhachHang> getAll() {
        return XQuery.getBeanList(KhachHang.class, sql);
    }
    public List<KhachHang> findByName(String hoTen){
        return XQuery.getBeanList(KhachHang.class, find_by_hoTen_sql,"%" + hoTen + "%") ;
    }
    public List<KhachHang> findByPhone(String soDienThoai){
        return XQuery.getBeanList(KhachHang.class, find_by_soDienThoai_sql,"%" + soDienThoai+ "%") ;
    }
    public KhachHang create(KhachHang e) {
        Object[] values ={
                e.getMaKhachHang(),
                e.getHoTen(),
                e.getGioiTinh(),
                e.getDiaChi(),
                e.getSoDienThoai(),
                e.getEmail(),
                e.getGhiChu(),
                e.isTrangThai()
            };
            XJdbc.executeUpdate(create_SQL,values);
                    return e;
    }
    public KhachHang update(KhachHang e) {
    Object[] values = {
        e.getMaKhachHang(),
        e.getHoTen(),
        e.getGioiTinh(),
        e.getDiaChi(),
        e.getSoDienThoai(),
        e.getEmail(),
        e.getGhiChu(),
        e.isTrangThai(),
        e.getIdKhachHang()
    };
    XJdbc.executeUpdate(update_SQL, values);
    return e;
    }
    public KhachHang delete(KhachHang e){
        Object[] values = {
            e.getIdKhachHang()
        };
        XJdbc.executeUpdate(update_status_SQL, values);
        return e;
    }
    
    public boolean existsMaKhachHang(String maKH) {
    String sql = "SELECT COUNT(*) FROM KhachHang WHERE maKhachHang = ?";
    try (Connection conn = Dbconnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, maKH);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

}
