/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.KichCo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sownj
 */
public class rp_KichCo {
        public List<KichCo> getAll(){
        List<KichCo> list = new ArrayList<>();
        list.add(new KichCo(1, "KC001", "38", "Size phổ biến", 0));
        list.add(new KichCo(2, "KC002", "39", "Size trung bình", 0));
        list.add(new KichCo(3, "KC003", "40", "Size lớn hơn", 0));
        list.add(new KichCo(4, "KC004", "41", "Size phổ biến cho nam", 0));
        list.add(new KichCo(5, "KC005", "42", "Size lớn", 0));
        list.add(new KichCo(6, "KC006", "43", "Size rất lớn", 0));
        return  list;
    }
}
