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
public class PhuongThucThanhToan {
    private int idPTTT;
    private String maPTTT;
    private String tenPTTT;
    private String moTa;
    private int trangThai;
}
