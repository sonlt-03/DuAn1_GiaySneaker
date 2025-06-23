package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.ThongKeDoanhThu;
import Model.ThongKeSanPham;
import Utils.Dbconnection;
import java.util.ArrayList;
import java.util.Date;

public class DAO_ThongKe {

    public int demTongDonHangTrongNgay(Date date) {
        String sql = "SELECT COUNT(*) FROM HoaDon WHERE CAST(ngayTao AS DATE) = ? AND trangThai = 1";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double tinhDoanhThuTheoNgay(Date date) {
        String sql = """
            SELECT SUM(hdct.soLuong * hdct.donGia - hdct.giamGia)
            FROM HoaDon hd
            JOIN HoaDonChiTiet hdct ON hd.idHoaDon = hdct.idHoaDon
            WHERE CAST(hd.ngayTao AS DATE) = ? AND hd.trangThai = 1
        """;
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double tinhDoanhThuTheoThang(Date date) {
        String sql = """
            SELECT SUM(hdct.soLuong * hdct.donGia - hdct.giamGia)
            FROM HoaDon hd
            JOIN HoaDonChiTiet hdct ON hd.idHoaDon = hdct.idHoaDon
            WHERE MONTH(hd.ngayTao) = ? AND YEAR(hd.ngayTao) = ? AND hd.trangThai = 1
        """;
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, date.getMonth() + 1);
            ps.setInt(2, date.getYear() + 1900);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double tinhDoanhThuTheoNam(Date date) {
        String sql = """
            SELECT SUM(hdct.soLuong * hdct.donGia - hdct.giamGia)
            FROM HoaDon hd
            JOIN HoaDonChiTiet hdct ON hd.idHoaDon = hdct.idHoaDon
            WHERE YEAR(hd.ngayTao) = ? AND hd.trangThai = 1
        """;
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, date.getYear() + 1900);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<ThongKeDoanhThu> getDoanhThuTheoThang() {
        ArrayList<ThongKeDoanhThu> list = new ArrayList<>();
        String sql = """
            SELECT 
                MONTH(hd.ngayTao) AS Thang,
                SUM(hdct.soLuong) AS SoLuongBan,
                SUM(hdct.soLuong * hdct.donGia - hdct.giamGia) AS DoanhThu
            FROM HoaDon hd
            JOIN HoaDonChiTiet hdct ON hd.idHoaDon = hdct.idHoaDon
            WHERE YEAR(hd.ngayTao) = YEAR(GETDATE()) AND hd.trangThai = 1
            GROUP BY MONTH(hd.ngayTao)
            ORDER BY Thang
        """;

        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ThongKeDoanhThu tk = new ThongKeDoanhThu();
                tk.setThang(rs.getInt("Thang"));
                tk.setSoLuongBan(rs.getInt("SoLuongBan"));
                tk.setTongGiaBan(rs.getDouble("DoanhThu")); // nếu bạn phân tách thì tạo thêm cột riêng
                tk.setDoanhThu(rs.getDouble("DoanhThu"));
                list.add(tk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<ThongKeSanPham> getDoanhThuSanPhamTheoNgay(Date date) {
        ArrayList<ThongKeSanPham> list = new ArrayList<>();
        String sql = """
            SELECT sp.maSanPham, sp.tenSanPham, ms.tenMau, kc.kichThuoc, cl.tenChatLieu, th.tenThuongHieu, SUM(hdct.soLuong) AS soLuong
            FROM HoaDonChiTiet hdct
            JOIN HoaDon hd ON hd.idHoaDon = hdct.idHoaDon
            JOIN ChiTietSanPham ctsp ON ctsp.idSanPham = hdct.idSanPham
            JOIN SanPham sp ON sp.idSanPham = ctsp.idSanPham
            JOIN MauSac ms ON ms.idMauSac = sp.idMauSac
            JOIN KichCo kc ON kc.idKichCo = sp.idKichCo
            JOIN ChatLieu cl ON cl.idChatLieu = sp.idChatLieu
            JOIN ThuongHieu th ON th.idThuongHieu = sp.idThuongHieu
            WHERE CAST(hd.ngayTao AS DATE) = ? AND hd.trangThai = 1
            GROUP BY sp.maSanPham, sp.tenSanPham, ms.tenMau, kc.kichThuoc, cl.tenChatLieu, th.tenThuongHieu
        """;
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeSanPham sp = new ThongKeSanPham();
                sp.setMaSanPham(rs.getString("maSanPham"));
                sp.setTenSanPham(rs.getString("tenSanPham"));
                sp.setTenMau(rs.getString("tenMau"));
                sp.setKichThuoc(rs.getString("kichThuoc"));
                sp.setTenChatLieu(rs.getString("tenChatLieu"));
                sp.setTenThuongHieu(rs.getString("tenThuongHieu"));
                sp.setSoLuong(rs.getInt("soLuong"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}