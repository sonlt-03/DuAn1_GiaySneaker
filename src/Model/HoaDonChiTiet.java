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
    private int id;
    private int idHoaDon;
    private int idSanPham;
    private int soLuong;
    private double donGia;
    private double giamGia;
    private double thanhTien;
}   
