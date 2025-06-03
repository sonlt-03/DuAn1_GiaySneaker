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
public class MauSac {
    private int idMauSac;
    private String maMau;
    private String tenMau;
    private String moTa;
    private int trangThai;
}
