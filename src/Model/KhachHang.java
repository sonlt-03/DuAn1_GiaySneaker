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
public class KhachHang {
    private int idKhachHang;
    private String maKhachHang;
    private String hoTen;
    private String gioiTinh;
    private String soDienThoai;
    private String email;
    private String ghiChu;
    private int trangThai;
}
