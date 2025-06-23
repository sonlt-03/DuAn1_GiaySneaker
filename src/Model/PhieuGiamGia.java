package Model;

import java.math.BigDecimal;
import java.util.Date;
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
public class PhieuGiamGia {
    private int idPhieuGiamGia;
    private String maPhieu;
    private String tenPhieu;
    private String tenHinhThucGG;
    private BigDecimal giaTri;
    private int soLuong;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String dieuKienApDung;
    private Boolean trangThai;
    public boolean isTrangThai() { return trangThai; }
    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }
    
    @Override
    public String toString() {
    return this.maPhieu;
}
}
