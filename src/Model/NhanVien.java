package model;

public class NhanVien {

    private int idNhanVien;
    private String maNhanVien;
    private String hoTen;
    private String gioiTinh;
    private String soDienThoai;
    private String email;
    private String matKhau;
    private int idChucVu;
    private int vaiTro;
    private int trangThai;

    public NhanVien() {
    }

    public NhanVien(int idNhanVien, String maNhanVien, String hoTen, String gioiTinh,
            String soDienThoai, String email, String matKhau, int idChucVu,
            int vaiTro, int trangThai) {
        this.idNhanVien = idNhanVien;
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.matKhau = matKhau;
        this.idChucVu = idChucVu;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getIdChucVu() {
        return idChucVu;
    }

    public void setIdChucVu(int idChucVu) {
        this.idChucVu = idChucVu;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.getMaNhanVien(),
            this.getHoTen(),
            this.getSoDienThoai(),
            this.getEmail(),
            this.getGioiTinh(),
            (this.getVaiTro() == 0 ? "Quản lý" : "Nhân viên"),
            (this.getTrangThai() == 0 ? "Đang làm" : "Ngừng làm")
        };
    }
}
