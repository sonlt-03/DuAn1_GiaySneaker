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
public class PhieuGiamGia {
    private int idPhieuGiamGia;
    private String maPhieu;
    private String tenPhieu;
    private int idHinhThucGG;
    private double giaTri;
    private int soLuong;
    private String ngayBatDau;
    private String ngayKetThuc;
    private String dieuKienApDung;
    private int trangThai;
}
