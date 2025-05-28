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
public class HinhThucGiamGia {
    private int idHinhThucGG;
    private String maHinhThucGG;
    private String tenHinhThucGG;
    private String moTa;
    private int trangThai;
}
