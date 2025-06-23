package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
public class KichCo {
    private int idKichCo;
    private String maKichCo;
    private String kichThuoc;
    private String moTa;
    private int trangThai;

    public KichCo() {
    }

    public KichCo(int idKichCo, String maKichCo, String kichThuoc, String moTa, int trangThai) {
        this.idKichCo = idKichCo;
        this.maKichCo = maKichCo;
        this.kichThuoc = kichThuoc;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public int getIdKichCo() {
        return idKichCo;
    }

    public String getMaKichCo() {
        return maKichCo;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setIdKichCo(int idKichCo) {
        this.idKichCo = idKichCo;
    }

    public void setMaKichCo(String maKichCo) {
        this.maKichCo = maKichCo;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "KichCo{" + "idKichCo=" + idKichCo + ", maKichCo=" + maKichCo + ", kichThuoc=" + kichThuoc + ", moTa=" + moTa + ", trangThai=" + trangThai + '}';
    }
    
    
    
}
