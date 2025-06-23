package Utils;

import Model.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumUtil {
    private static final Map<Integer, String> MAU_SAC = new HashMap<>();
    private static final Map<Integer, String> KICH_CO = new HashMap<>();
    private static final Map<Integer, String> THUONG_HIEU = new HashMap<>();
    private static final Map<Integer, String> LOAI_GIAY = new HashMap<>();
    private static final Map<Integer, String> CHAT_LIEU = new HashMap<>();

    public static void setDanhSachMauSac(List<MauSac> list) {
        MAU_SAC.clear();
        for (MauSac item : list) {
            MAU_SAC.put(item.getIdMauSac(), item.getTenMau());
        }
    }

    public static void setDanhSachKichCo(List<KichCo> list) {
        KICH_CO.clear();
        for (KichCo item : list) {
            KICH_CO.put(item.getIdKichCo(), item.getKichThuoc());
        }
    }

    public static void setDanhSachThuongHieu(List<ThuongHieu> list) {
        THUONG_HIEU.clear();
        for (ThuongHieu item : list) {
            THUONG_HIEU.put(item.getIdThuongHieu(), item.getTenThuongHieu());
        }
    }

    public static void setDanhSachLoaiGiay(List<LoaiGiay> list) {
        LOAI_GIAY.clear();
        for (LoaiGiay item : list) {
            LOAI_GIAY.put(item.getIdLoaiGiay(), item.getTenLoaiGiay());
        }
    }

    public static void setDanhSachChatLieu(List<ChatLieu> list) {
        CHAT_LIEU.clear();
        for (ChatLieu item : list) {
            CHAT_LIEU.put(item.getIdChatLieu(), item.getTenChatLieu());
        }
    }

    public static String getTenMauSacById(int id) {
        return MAU_SAC.getOrDefault(id, "Không rõ");
    }

    public static String getTenKichCoById(int id) {
        return KICH_CO.getOrDefault(id, "Không rõ");
    }

    public static String getTenThuongHieuById(int id) {
        return THUONG_HIEU.getOrDefault(id, "Không rõ");
    }

    public static String getTenLoaiGiayById(int id) {
        return LOAI_GIAY.getOrDefault(id, "Không rõ");
    }

    public static String getTenChatLieuById(int id) {
        return CHAT_LIEU.getOrDefault(id, "Không rõ");
    }
}
