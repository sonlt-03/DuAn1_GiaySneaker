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
public class LoaiGiay {
    private int idLoaiGiay;
    private String maLoaiGiay;
    private String tenLoaiGiay;
    private String moTa;
    private int trangThai;
}
