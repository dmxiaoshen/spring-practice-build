package com.xyz.poistudys.util;

import com.xyz.poistudys.excels.Book;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/9/6.
 */
public class ExcelExportUtil {

    public static void main(String[] args) {
        try {
            long time = System.currentTimeMillis();
            export("d://data//jjj.xlsx", build());
            long end = System.currentTimeMillis();
            System.out.println("耗时"+(end-time)/1000+"s");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void export(String filiName, Workbook wb) throws Exception {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filiName));
        wb.write(out);
        out.flush();
        out.close();
        ((SXSSFWorkbook)wb).dispose();
    }

    public static Workbook build() {
        Workbook wb = new SXSSFWorkbook(2000);
        Sheet sh = wb.createSheet("sheet 1");
        String[] title = new String[]{"名称", "价格"};
        Row row = sh.createRow(0);
        for (int b = 0; b < title.length; b++) {
            row.createCell(b).setCellValue(title[b]);
        }
        int count = 1;
        for (int i = 0; i < 13; i++) {
            List<Book> dataList = dataList();
            for (Book book : dataList) {
                Row rowN = sh.createRow(count++);
                rowN.createCell(0).setCellValue(book.getName());
                rowN.createCell(1, Cell.CELL_TYPE_NUMERIC).setCellValue(book.getPrice().doubleValue());
            }
        }
        return wb;
    }

    public static List<Book> dataList() {
        List<Book> list = new ArrayList<>();
        for (int i = 0; i < 2500; i++) {
            list.add(new Book("java", new BigDecimal("34")));
            list.add(new Book("c++", new BigDecimal("94")));
        }
        return list;
    }
}
