/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.LoaiGiay;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sownj
 */
public class rp_LoaiGiay {
    public List<LoaiGiay> getAll(){
        List<LoaiGiay> list = new ArrayList<>();
        list.add(new LoaiGiay(1, "LG001", "Sneaker", "Giày thời trang", 0));
        list.add(new LoaiGiay(2, "LG002", "Running", "Giày chạy bộ", 0));
        list.add(new LoaiGiay(3, "LG003", "Training", "Tập gym", 0));
        list.add(new LoaiGiay(4, "LG004", "Casual", "Đi học, đi làm", 0));
        list.add(new LoaiGiay(5, "LG005", "Basketball", "Giày bóng rổ", 0));
        return  list;
    }
}
