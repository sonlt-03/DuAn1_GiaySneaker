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
    private String ngayTao;
    private double tongTien;
    private String ghiChu;
}
