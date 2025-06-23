/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author 5than
 */
public class ThongKeDoanhThu {
      private int thang;
    private int soLuongBan;
    private double tongGiaBan;
    private double doanhThu;

    public ThongKeDoanhThu() {}

    public ThongKeDoanhThu(int thang, int soLuongBan, double tongGiaBan, double doanhThu) {
        this.thang = thang;
        this.soLuongBan = soLuongBan;
        this.tongGiaBan = tongGiaBan;
        this.doanhThu = doanhThu;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public double getTongGiaBan() {
        return tongGiaBan;
    }

    public void setTongGiaBan(double tongGiaBan) {
        this.tongGiaBan = tongGiaBan;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }
}
