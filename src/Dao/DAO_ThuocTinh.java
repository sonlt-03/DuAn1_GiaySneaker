/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.ChatLieu;
import Model.KichCo;
import Model.LoaiGiay;
import Model.MauSac;
import Model.ThuongHieu;
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
public class DAO_ThuocTinh {

    public boolean insertMauSac(MauSac ms) {
        String sql = "INSERT INTO MauSac(maMau, tenMau, moTa) VALUES (?, ?, ?)";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ms.getMaMau());
            ps.setString(2, ms.getTenMau());
            ps.setString(3, ms.getMoTa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertKichCo(KichCo kc) {
        String sql = "INSERT INTO KichCo(maKichCo, kichThuoc, moTa) VALUES (?, ?, ?)";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kc.getMaKichCo());
            ps.setString(2, kc.getKichThuoc());
            ps.setString(3, kc.getMoTa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertThuongHieu(ThuongHieu th) {
        String sql = "INSERT INTO ThuongHieu(maThuongHieu, tenThuongHieu, moTa) VALUES (?, ?, ?)";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, th.getMaThuongHieu());
            ps.setString(2, th.getTenThuongHieu());
            ps.setString(3, th.getMoTa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertChatLieu(ChatLieu cl) {
        String sql = "INSERT INTO ChatLieu(maChatLieu, tenChatLieu, moTa) VALUES (?, ?, ?)";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cl.getMaChatLieu());
            ps.setString(2, cl.getTenChatLieu());
            ps.setString(3, cl.getMoTa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertLoaiGiay(LoaiGiay lg) {
        String sql = "INSERT INTO LoaiGiay(maLoaiGiay, tenLoaiGiay, moTa) VALUES (?, ?, ?)";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lg.getMaLoaiGiay());
            ps.setString(2, lg.getTenLoaiGiay());
            ps.setString(3, lg.getMoTa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMauSac(String maMau) {
        String sql = "DELETE FROM MauSac WHERE maMau = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maMau);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteKichCo(String maKichCo) {
        String sql = "DELETE FROM KichCo WHERE maKichCo = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maKichCo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteThuongHieu(String maThuongHieu) {
        String sql = "DELETE FROM ThuongHieu WHERE maThuongHieu = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maThuongHieu);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteChatLieu(String maChatLieu) {
        String sql = "DELETE FROM ChatLieu WHERE maChatLieu = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maChatLieu);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteLoaiGiay(String maLoaiGiay) {
        String sql = "DELETE FROM LoaiGiay WHERE maLoaiGiay = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maLoaiGiay);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMauSac(MauSac ms) {
        String sql = "UPDATE MauSac SET tenMau = ?, moTa = ? WHERE maMau = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ms.getTenMau());
            ps.setString(2, ms.getMoTa());
            ps.setString(3, ms.getMaMau());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateKichCo(KichCo kc) {
        String sql = "UPDATE KichCo SET kichThuoc = ?, moTa = ? WHERE maKichCo = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kc.getKichThuoc());
            ps.setString(2, kc.getMoTa());
            ps.setString(3, kc.getMaKichCo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateThuongHieu(ThuongHieu th) {
        String sql = "UPDATE ThuongHieu SET tenThuongHieu = ?, moTa = ? WHERE maThuongHieu = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, th.getTenThuongHieu());
            ps.setString(2, th.getMoTa());
            ps.setString(3, th.getMaThuongHieu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateChatLieu(ChatLieu cl) {
        String sql = "UPDATE ChatLieu SET tenChatLieu = ?, moTa = ? WHERE maChatLieu = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cl.getTenChatLieu());
            ps.setString(2, cl.getMoTa());
            ps.setString(3, cl.getMaChatLieu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateLoaiGiay(LoaiGiay lg) {
        String sql = "UPDATE LoaiGiay SET tenLoaiGiay = ?, moTa = ? WHERE maLoaiGiay = ?";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lg.getTenLoaiGiay());
            ps.setString(2, lg.getMoTa());
            ps.setString(3, lg.getMaLoaiGiay());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<MauSac> getAllMauSac() {
        ArrayList<MauSac> list = new ArrayList<>();
        String sql = "SELECT * FROM MauSac";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setIdMauSac(rs.getInt("idMauSac"));
                ms.setMaMau(rs.getString("maMau"));
                ms.setTenMau(rs.getString("tenMau"));
                ms.setMoTa(rs.getString("moTa"));
                list.add(ms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<KichCo> getAllKichCo() {
        ArrayList<KichCo> list = new ArrayList<>();
        String sql = "SELECT * FROM KichCo";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                KichCo kc = new KichCo();
                kc.setIdKichCo(rs.getInt("idKichCo"));
                kc.setMaKichCo(rs.getString("maKichCo"));
                kc.setKichThuoc(rs.getString("kichThuoc"));
                kc.setMoTa(rs.getString("moTa"));
                list.add(kc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ThuongHieu> getAllThuongHieu() {
        ArrayList<ThuongHieu> list = new ArrayList<>();
        String sql = "SELECT * FROM ThuongHieu";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setIdThuongHieu(rs.getInt("idThuongHieu"));
                th.setMaThuongHieu(rs.getString("maThuongHieu"));
                th.setTenThuongHieu(rs.getString("tenThuongHieu"));
                th.setMoTa(rs.getString("moTa"));
                list.add(th);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ChatLieu> getAllChatLieu() {
        ArrayList<ChatLieu> list = new ArrayList<>();
        String sql = "SELECT * FROM ChatLieu";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setIdChatLieu(rs.getInt("idChatLieu"));
                cl.setMaChatLieu(rs.getString("maChatLieu"));
                cl.setTenChatLieu(rs.getString("tenChatLieu"));
                cl.setMoTa(rs.getString("moTa"));
                list.add(cl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<LoaiGiay> getAllLoaiGiay() {
        ArrayList<LoaiGiay> list = new ArrayList<>();
        String sql = "SELECT * FROM LoaiGiay";
        try (Connection con = Dbconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LoaiGiay lg = new LoaiGiay();
                lg.setIdLoaiGiay(rs.getInt("idLoaiGiay"));
                lg.setMaLoaiGiay(rs.getString("maLoaiGiay"));
                lg.setTenLoaiGiay(rs.getString("tenLoaiGiay"));
                lg.setMoTa(rs.getString("moTa"));
                list.add(lg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean kiemTraTrungMauSac(String maMau, String tenMau) {
        String sql = "SELECT COUNT(*) FROM MauSac WHERE MaMau = ? OR TenMau = ?";
        try {
            Connection con = Dbconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maMau);
            ps.setString(2, tenMau);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean kiemTraTrungKichCo(String maKichCo, String kichThuoc) {
        String sql = "SELECT COUNT(*) FROM KichCo WHERE MaKichCo = ? OR KichThuoc = ?";
        try {
            Connection con = Dbconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKichCo);
            ps.setString(2, kichThuoc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean kiemTraTrungThuongHieu(String maThuongHieu, String tenThuongHieu) {
        String sql = "SELECT COUNT(*) FROM ThuongHieu WHERE MaThuongHieu = ? OR TenThuongHieu = ?";
        try {
            Connection con = Dbconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maThuongHieu);
            ps.setString(2, tenThuongHieu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean kiemTraTrungChatLieu(String maChatLieu, String tenChatLieu) {
        String sql = "SELECT COUNT(*) FROM ChatLieu WHERE MaChatLieu = ? OR TenChatLieu = ?";
        try {
            Connection con = Dbconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maChatLieu);
            ps.setString(2, tenChatLieu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean kiemTraTrungLoaiGiay(String maLoaiGiay, String tenLoaiGiay) {
        String sql = "SELECT COUNT(*) FROM LoaiGiay WHERE MaLoaiGiay = ? OR TenLoaiGiay = ?";
        try {
            Connection con = Dbconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maLoaiGiay);
            ps.setString(2, tenLoaiGiay);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
