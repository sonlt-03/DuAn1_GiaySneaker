/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Dao.DAO_SanPham;
import Model.SanPham;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sownj
 */
public class rp_SanPham {
    
    private DAO_SanPham dao = new DAO_SanPham();
    
    public ArrayList<SanPham> getAll(){
        return dao.getAll();
    }
    
    public boolean addSanPham(SanPham sp){
        return dao.addSanPham(sp);
    }
    
    public boolean updateSanPham(String maSanPhamCu, SanPham sp) {
        return dao.updateSanPham(maSanPhamCu, sp);
    }
    
    public boolean deleteSanPham(String maSanPham){
        return dao.deleteSanPhamByMa(maSanPham);
    }
    
}
