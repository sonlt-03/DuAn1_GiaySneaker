/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.ChatLieu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sownj
 */
public class rp_ChatLieu {
     public List<ChatLieu> getAll(){
        List<ChatLieu> list = new ArrayList<>();
        list.add(new ChatLieu(1, "CL001", "Vải", "Nhẹ và thoáng khí", 0));
        list.add(new ChatLieu(2, "CL002", "Da tổng hợp", "Bền và đẹp", 0));
        list.add(new ChatLieu(3, "CL003", "Cao su", "Đế đàn hồi tốt", 0));
        list.add(new ChatLieu(4, "CL004", "Canvas", "Chất liệu vải dày", 0));
        list.add(new ChatLieu(5, "CL005", "Lưới", "Thông thoáng", 0));
        return  list;
    }
}
