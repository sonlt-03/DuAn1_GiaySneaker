package Repository;

import Dao.DAO_ThongKe;
import Model.ThongKeDoanhThu;
import Model.ThongKeSanPham;
import java.util.ArrayList;
import java.util.Date;

public class rp_ThongKe {

    private DAO_ThongKe dao = new DAO_ThongKe();

    // Thống kê doanh thu theo tháng trong năm hiện tại
    public ArrayList<ThongKeDoanhThu> getDoanhThuTheoThang() {
        return dao.getDoanhThuTheoThang();
    }

    // Đếm số lượng đơn hàng trong ngày cụ thể
    public int demTongDonHangTrongNgay(Date date) {
        return dao.demTongDonHangTrongNgay(date);
    }

    // Tính doanh thu theo ngày (tổng chi tiết hóa đơn)
    public double tinhDoanhThuTheoNgay(Date date) {
        return dao.tinhDoanhThuTheoNgay(date);
    }

    // Tính doanh thu theo tháng (tổng chi tiết hóa đơn)
    public double tinhDoanhThuTheoThang(Date date) {
        return dao.tinhDoanhThuTheoThang(date);
    }

    // Tính doanh thu theo năm (tổng chi tiết hóa đơn)
    public double tinhDoanhThuTheoNam(Date date) {
        return dao.tinhDoanhThuTheoNam(date);
    }

    // Lấy danh sách sản phẩm bán trong ngày (theo hóa đơn chi tiết)
    public ArrayList<ThongKeSanPham> getDoanhThuSanPhamTheoNgay(Date date) {
        return dao.getDoanhThuSanPhamTheoNgay(date);
    }
}