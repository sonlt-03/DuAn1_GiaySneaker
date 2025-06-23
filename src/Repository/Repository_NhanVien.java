package Repository;
import Dao.Dao_NhanVien;
import java.util.ArrayList;
import model.NhanVien;

public class Repository_NhanVien {



    private Dao_NhanVien dao = new Dao_NhanVien();
    
    public ArrayList<NhanVien> getAll() {
        return dao.getAllNhanVien();
    }

    public int add(NhanVien nv) {
        return dao.add(nv);
    }

    public int update(NhanVien nv) {
        return dao.update(nv);
    }

    public boolean delete(String maNhanVien) {
        return dao.delete(maNhanVien);
    }

    public ArrayList<NhanVien> searchByPhone(String keyword) {
        return dao.search(keyword);
    }
    
    public ArrayList<NhanVien> getByStatus(int status) {
    return dao.getByStatus(status);
}
    public NhanVien login(String email, String matKhau) {
        return dao.checkLogin(email, matKhau);
    }

}
