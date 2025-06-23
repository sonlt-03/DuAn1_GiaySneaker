/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.MauSac;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sownj
 */
public class rp_MauSac {
    public List<MauSac> getAll(){
        List<MauSac> list = new ArrayList<>();
        list.add(new MauSac(1, "MS001", "Đen", "Màu đen truyền thống", 0));
        list.add(new MauSac(2, "MS002", "Trắng", "Màu trắng sáng", 0));
        list.add(new MauSac(3, "MS003", "Xám", "Màu xám trung tính", 0));
        list.add(new MauSac(4, "MS004", "Xanh", "Màu xanh trẻ trung", 0));
        list.add(new MauSac(5, "MS005", "Đỏ", "Màu đỏ nổi bật", 0));
        list.add(new MauSac(6, "MS006", "Vàng", "Màu vàng năng động", 0));
        return  list;
    }
}
