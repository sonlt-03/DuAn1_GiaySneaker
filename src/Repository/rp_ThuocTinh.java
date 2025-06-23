/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Dao.DAO_SanPham;
import Dao.DAO_ThuocTinh;
import Model.ChatLieu;
import Model.KichCo;
import Model.LoaiGiay;
import Model.MauSac;
import Model.ThuongHieu;
import java.util.ArrayList;

/**
 *
 * @author sownj
 */
public class rp_ThuocTinh {

    private Dao.DAO_ThuocTinh dao = new DAO_ThuocTinh();

    public boolean themMauSac(MauSac ms) {
        return dao.insertMauSac(ms);
    }

    public boolean themKichCo(KichCo kc) {
        return dao.insertKichCo(kc);
    }

    public boolean themThuongHieu(ThuongHieu th) {
        return dao.insertThuongHieu(th);
    }

    public boolean themChatLieu(ChatLieu cl) {
        return dao.insertChatLieu(cl);
    }

    public boolean themLoaiGiay(LoaiGiay lg) {
        return dao.insertLoaiGiay(lg);
    }

    public boolean xoaMauSac(String maMau) {
        return dao.deleteMauSac(maMau);
    }

    public boolean xoaKichCo(String maKichCo) {
        return dao.deleteKichCo(maKichCo);
    }

    public boolean xoaThuongHieu(String maThuongHieu) {
        return dao.deleteThuongHieu(maThuongHieu);
    }

    public boolean xoaChatLieu(String maChatLieu) {
        return dao.deleteChatLieu(maChatLieu);
    }

    public boolean xoaLoaiGiay(String maLoaiGiay) {
        return dao.deleteLoaiGiay(maLoaiGiay);
    }

    public boolean capNhatMauSac(MauSac ms) {
        return dao.updateMauSac(ms);
    }

    public boolean capNhatKichCo(KichCo kc) {
        return dao.updateKichCo(kc);
    }

    public boolean capNhatThuongHieu(ThuongHieu th) {
        return dao.updateThuongHieu(th);
    }

    public boolean capNhatChatLieu(ChatLieu cl) {
        return dao.updateChatLieu(cl);
    }

    public boolean capNhatLoaiGiay(LoaiGiay lg) {
        return dao.updateLoaiGiay(lg);
    }

    public ArrayList<MauSac> getAllMauSac() {
        return dao.getAllMauSac();
    }

    public ArrayList<KichCo> getAllKichCo() {
        return dao.getAllKichCo();
    }

    public ArrayList<ThuongHieu> getAllThuongHieu() {
        return dao.getAllThuongHieu();
    }

    public ArrayList<ChatLieu> getAllChatLieu() {
        return dao.getAllChatLieu();
    }

    public ArrayList<LoaiGiay> getAllLoaiGiay() {
        return dao.getAllLoaiGiay();
    }

    public boolean kiemTraTrungMauSac(String maMau, String tenMau) {
        return dao.kiemTraTrungMauSac(maMau, tenMau);
    }

    public boolean kiemTraTrungKichCo(String maKichCo, String kichThuoc) {
        return dao.kiemTraTrungKichCo(maKichCo, kichThuoc);
    }

    public boolean kiemTraTrungThuongHieu(String maThuongHieu, String tenThuongHieu) {
        return dao.kiemTraTrungThuongHieu(maThuongHieu, tenThuongHieu);
    }

    public boolean kiemTraTrungChatLieu(String maChatLieu, String tenChatLieu) {
        return dao.kiemTraTrungChatLieu(maChatLieu, tenChatLieu);
    }

    public boolean kiemTraTrungLoaiGiay(String maLoaiGiay, String tenLoaiGiay) {
        return dao.kiemTraTrungLoaiGiay(maLoaiGiay, tenLoaiGiay);
    }

}
