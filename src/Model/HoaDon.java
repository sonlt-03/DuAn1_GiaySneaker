package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HoaDon {

    private int idHoaDon;
    private String maHoaDon;
    private int idNhanVien;
    private int idKhachHang;
    private int idPhieuGiamGia;
    private int idPTTT;
    private String tenNhanVien;
    private String tenKhachHang;
    private String tenPhieuGiamGia;
    private String tenPTTT;
    private String ngayTao;
    private double tongTien;
    private String ghiChu;
    private Boolean trangThai;

    public HoaDon(int idHoaDon, String maHoaDon, int idNhanVien, int idKhachHang,
            int idPhieuGiamGia, int idPTTT, String ngayTao,
            double tongTien, String ghiChu, Boolean trangThai) {
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.idPhieuGiamGia = idPhieuGiamGia;
        this.idPTTT = idPTTT;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }
}
