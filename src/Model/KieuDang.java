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
public class KieuDang {
    private int idKieuDang;
    private String maKieuDang;
    private String tenKieuDang;
    private String moTa;
    private int trangThai;
}
