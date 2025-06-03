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
public class ChucVu {
    private int idChucVu;
    private String maChucVu;
    private String tenChucVu;
    private String moTa;
    private int trangThai;
}
