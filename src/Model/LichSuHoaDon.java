/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguye
 */
import java.util.Date;
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
public class LichSuHoaDon {
    private int idHoaDon;
    private String maHoaDon;
    private int idNhanVien;
    private int idKhachHang;
    private int idPhieuGiamGia;
    private int idPTTT;
    private Date ngayTao;
    private float tongTien;
    private String ghiChu;
    private Boolean trangThai;
}
