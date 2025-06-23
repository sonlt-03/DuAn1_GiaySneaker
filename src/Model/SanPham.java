package Model;
public class SanPham {
    private int idSanPham;
    private String maSanPham;
    private String tenSanPham;
    private int idMauSac;
    private int idKichCo;
    private int idThuongHieu;
    private int idLoaiGiay;
    private int idChatLieu;
    private double giaBan;
    private int soLuong;
    private String imgPath;
    private int IdKieuDang;
    private String moTa;
    private int trangThai;

    public SanPham() {
    }

    public SanPham(int idSanPham, String maSanPham, String tenSanPham, int idMauSac, int idKichCo, int idThuongHieu, int idLoaiGiay, int idChatLieu, double giaBan, int soLuong, String imgPath, int IdKieuDang, String moTa, int trangThai) {
        this.idSanPham = idSanPham;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.idMauSac = idMauSac;
        this.idKichCo = idKichCo;
        this.idThuongHieu = idThuongHieu;
        this.idLoaiGiay = idLoaiGiay;
        this.idChatLieu = idChatLieu;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.imgPath = imgPath;
        this.IdKieuDang = IdKieuDang;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public int getIdMauSac() {
        return idMauSac;
    }

    public int getIdKichCo() {
        return idKichCo;
    }

    public int getIdThuongHieu() {
        return idThuongHieu;
    }

    public int getIdLoaiGiay() {
        return idLoaiGiay;
    }

    public int getIdChatLieu() {
        return idChatLieu;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getImgPath() {
        return imgPath;
    }

    public int getIdKieuDang() {
        return IdKieuDang;
    }

    public String getMoTa() {
        return moTa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setIdMauSac(int idMauSac) {
        this.idMauSac = idMauSac;
    }

    public void setIdKichCo(int idKichCo) {
        this.idKichCo = idKichCo;
    }

    public void setIdThuongHieu(int idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public void setIdLoaiGiay(int idLoaiGiay) {
        this.idLoaiGiay = idLoaiGiay;
    }

    public void setIdChatLieu(int idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setIdKieuDang(int IdKieuDang) {
        this.IdKieuDang = IdKieuDang;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }


    
  
}
