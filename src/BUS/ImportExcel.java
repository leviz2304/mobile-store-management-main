package BUS;

import DTO.SanPham;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ImportExcel {
    FileDialog fd = new FileDialog(new JFrame(), "Input from Excel",FileDialog.LOAD);

    private String getFile() {
        fd.setTitle("*.xls");
        fd.setVisible(true);
        String path = fd.getDirectory() + fd.getFile();
        if(path.equals("nullnull")) {
            return null;
        }
        return path;
    }

    public String getImagePath() {
        fd.setTitle("Chọn hình");
        String path = getFile();
        return path;
    }

    public ArrayList<SanPham> readProductFile() {
        ArrayList<SanPham> result = new ArrayList<>();
        fd.setTitle("Nhập dữ liệu từ Excel");
        String path = getFile();
        if (path == null) {
            return null;
        }
        FileInputStream is = null;
        try {
            is = new FileInputStream(new File(path));
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> row = sheet.iterator();

            Row row1 = row.next();
            int countAddSuccess = 0;
            int countOverride = 0;
            while (row.hasNext()) {
                Row cur_row = row.next();;
                Iterator<Cell> cell = cur_row.cellIterator();

                while (cell.hasNext()) {
                    String maSP = cell.next().getStringCellValue().trim();
                    System.out.println(maSP);
                    String tenSP = cell.next().getStringCellValue().trim();
                    System.out.println(tenSP);
                    String maLoai = cell.next().getStringCellValue().trim();
                    System.out.println(maLoai);
                    String maNSX = cell.next().getStringCellValue().trim();
                    System.out.println(maNSX);
                    int soLuong = (int) cell.next().getNumericCellValue();
                    System.out.println(soLuong);
                    int giaTien = (int) cell.next().getNumericCellValue();
                    System.out.println(giaTien);
                    
                    SanPham p = new SanPham(maSP,tenSP,maLoai,maNSX,soLuong,giaTien);
                    QuanLySanPhamBUS qlspBUS = new QuanLySanPhamBUS();
                    SanPham checkSP = qlspBUS.getSP(maSP);
                    
                    if(checkSP != null) {
                        qlspBUS.updateSoluong(maSP,soLuong);
                        countOverride++;
                    }
                    else {
                        qlspBUS.themSP(p);
                        countAddSuccess++;
                    }
                    result.add(p);


                }




            }
            JOptionPane.showMessageDialog(null, "Đọc file thành công. "
                                                            + "Số lượng sản phẩm mới: " + countAddSuccess
                                                            + "Số sản phẩm cập nhật số lượng: " + countOverride
                                                            + ". Làm mới để thấy kết quả.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }
}
