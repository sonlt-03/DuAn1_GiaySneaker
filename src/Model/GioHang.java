/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author nguye
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GioHang {
    private int id;
    private int idSanPham;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double donGia;
    private double giamGia;
    private double thanhTien;
}
