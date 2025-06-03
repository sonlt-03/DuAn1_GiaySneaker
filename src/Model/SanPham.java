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
public class SanPham {
    private int idSanPham;
    private String maSanPham;
    private String tenSanPham;
    private int idChatLieu;
    private int idKieuDang;
    private int idLoaiGiay;
    private int idThuongHieu;
    private String moTa;
    private int trangThai;
}
