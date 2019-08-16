package com.xyz.poistudys.excels;


import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Book> list = new ArrayList<>();
        list.add(new Book("java",new BigDecimal("33")));
        list.add(new Book("c++",new BigDecimal("99")));
        list.add(new Book("python",new BigDecimal("66")));
        ((ExportExcel<Book>) (obj, row) -> {
            row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(obj.getName());
            row.createCell(1,Cell.CELL_TYPE_NUMERIC).setCellValue(obj.getPrice().doubleValue());
        }).exportExcel("d://data//f.xlsx", list, new String[]{"名称", "价格"});
    }
}
