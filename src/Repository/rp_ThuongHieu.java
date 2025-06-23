/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.ThuongHieu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sownj
 */
public class rp_ThuongHieu {
    public List<ThuongHieu> getAll(){
        List<ThuongHieu> list = new ArrayList<>();
        list.add(new ThuongHieu(1, "TH001", "Nike", "Thương hiệu nổi tiếng thế giớií", 0));
        list.add(new ThuongHieu(2, "TH002", "Adidas", "Thiết kế thể thao hiện đại", 0));
        list.add(new ThuongHieu(3, "TH003", "Puma", "Trẻ trung và năng động", 0));
        list.add(new ThuongHieu(4, "TH004", "Converse", "Cổ điển và cá tính", 0));
        list.add(new ThuongHieu(5, "TH005", "Vans", "Phong cách đường phố", 0));
        return  list;
    }
}
