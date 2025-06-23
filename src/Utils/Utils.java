/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author 5than
 */
public class Utils {
    public static void main(String[] args) {
        try {
            JTable tblDoanhThu = mockDoanhThuTable();
            JTable tblSanPham = mockSanPhamTable();
            File file = xuatThongKeTongHop(tblDoanhThu, tblSanPham);
            sendMailWithAttachment("24tcttht5@gmail.com", file);
            System.out.println("📤 Gửi email thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File xuatThongKeTongHop(JTable tblThang, JTable tblSanPham) throws Exception {
        Workbook wb = new XSSFWorkbook();

        Sheet sheetThang = wb.createSheet("DoanhThu_Thang");
        writeTableToSheet(tblThang, sheetThang);

        Sheet sheetSP = wb.createSheet("SanPham_TrongNgay");
        writeTableToSheet(tblSanPham, sheetSP);

        File file = new File("ThongKeTongHop.xlsx");
        try (FileOutputStream out = new FileOutputStream(file)) {
            wb.write(out);
        }
        wb.close();
        return file;
    }

    private static void writeTableToSheet(JTable table, Sheet sheet) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Row header = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            header.createCell(i).setCellValue(model.getColumnName(i));
        }

        for (int r = 0; r < model.getRowCount(); r++) {
            Row row = sheet.createRow(r + 1);
            for (int c = 0; c < model.getColumnCount(); c++) {
                Object value = model.getValueAt(r, c);
                row.createCell(c).setCellValue(value != null ? value.toString() : "");
            }
        }
    }

public static void sendMailWithAttachment(String toEmail, File attachment) throws Exception {
    final String from = "24tcttht5@gmail.com"; 
    final String appPassword = "nupb asmx kuge vjja"; 

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, appPassword);
        }
    });

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
    message.setSubject("Thống kê doanh thu từ GiaySneaker");

    // Nội dung dạng văn bản
    MimeBodyPart textPart = new MimeBodyPart();
    textPart.setText("Xin chào,\n\nFile thống kê doanh thu được đính kèm.\n\nTrân trọng.");

    // Phần đính kèm file Excel
    MimeBodyPart filePart = new MimeBodyPart();
    filePart.attachFile(attachment); // Nếu NetBeans báo lỗi attachFile(), dùng cách dưới
    // filePart.setDataHandler(new DataHandler(new FileDataSource(attachment)));
    // filePart.setFileName(attachment.getName());

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(textPart);
    multipart.addBodyPart(filePart);

    message.setContent(multipart);

   Transport.send(message);
}

    // Tạo giả bảng để test
    private static JTable mockDoanhThuTable() {
        String[] cols = {"Tháng", "Số lượng bán", "Doanh thu"};
        Object[][] data = {
            {"1", "40", "12.000.000"},
            {"2", "33", "9.800.000"}
        };
        return new JTable(new DefaultTableModel(data, cols));
    }

    private static JTable mockSanPhamTable() {
        String[] cols = {"Mã SP", "Tên SP", "Số lượng"};
        Object[][] data = {
            {"SP01", "Nike Air", "10"},
            {"SP02", "Adidas Ultra", "5"}
        };
        return new JTable(new DefaultTableModel(data, cols));
    }
}
