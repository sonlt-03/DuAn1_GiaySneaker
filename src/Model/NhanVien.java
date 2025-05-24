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
public class NhanVien {
    private int idNhanVien;
    private String maNhanVien;
    private String hoTen;
    private String gioiTinh;
    private String soDienThoai;
    private String email;
    private String matKhau;
    private int idChucVu;
    private int vaiTro;    
    private int trangThai;  
}
