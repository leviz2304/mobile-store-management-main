package BUS;

import com.mysql.cj.result.Row;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportExcel extends JFrame {
    FileDialog fd = new FileDialog(new JFrame(), "Xuất File Excel", FileDialog.SAVE);
    JFileChooser chooser = new JFileChooser();

    public String getPath() {
        fd.setTitle("untitled.xls");
        fd.setVisible(true);
        String path = fd.getDirectory() + fd.getFile();
        if(path.equals("nullnull")) {
            return null;
        }
        return path;
    }
    public void Export(JTable table) {
        int i = chooser.showSaveDialog(chooser);



        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Excel");

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // ten Cot
            int rownum = 0;
            HSSFRow row = sheet.createRow(rownum);
            for (int j = 0; j < table.getColumnCount(); j++) {
                row.createCell(j, CellType.STRING).setCellValue(model.getColumnName(j) + "\t");
            }
            // Lay du lieu dong
            for (int j = 0; j < table.getRowCount(); j++) {
                rownum++;
                row = sheet.createRow(rownum);
                for (int k = 0; k < table.getColumnCount(); k++) {
                    row.createCell(k,CellType.STRING).setCellValue(model.getValueAt(j,k) + "\t");
                }
            }
            for (int h = 0; h < rownum; h++) {
                sheet.autoSizeColumn(h);
            }

            if (i == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                file.getParentFile().mkdirs();
                outFile = new FileOutputStream(file + ".xls");
                workbook.write(outFile);

                JOptionPane.showMessageDialog(null, "Lưu file thành công" );
            }



        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }




}
