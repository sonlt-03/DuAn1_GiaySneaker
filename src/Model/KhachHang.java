
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
public class KhachHang {
        private int idKhachHang;
        private String maKhachHang;
        private String hoTen;
        private String gioiTinh;
        private String diaChi;
        private String soDienThoai;
        private String email;
        private String ghiChu;
        private boolean trangThai;  // giữ kiểu boolean
        public boolean isTrangThai() { return trangThai; }
        public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }
    }

