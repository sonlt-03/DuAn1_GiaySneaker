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
public class ChiTietSanPham {
    private int idChiTietSP;
    private String maChiTietSP;
    private int idSanPham;
    private int idMauSac;
    private int idKichCo;
    private int soLuongTon;
    private double giaBan;
    private String moTa;
}
