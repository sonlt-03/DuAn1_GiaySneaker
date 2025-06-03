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
public class HoaDonChiTiet {
    private int idHoaDon;
    private int idChiTietSP;
    private int soLuong;
    private double donGia;
    private String ngayMua;
    private String ghiChu;
}
