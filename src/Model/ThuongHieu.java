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
public class ThuongHieu {
    private int idThuongHieu;
    private String maThuongHieu;
    private String tenThuongHieu;
    private String moTa;
    private int trangThai;
}
