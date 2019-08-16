package com.xyz.poistudys.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzhsg on 2018/6/8.
 */
public class ExcelUtils {

    public static List<String[][]> importExcel(File file) throws Exception {
        List<String[][]> result = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file);
        int sheetNumbers = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetNumbers; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            int rowNum = sheet.getPhysicalNumberOfRows();
            Row row = sheet.getRow(0);
            int cellNum = row.getPhysicalNumberOfCells();
            String[][] data = new String[rowNum][cellNum];
            int j = 0;
            String[] cellContent = new String[cellNum];
            for (Cell cell : row) {
                cellContent[j] = cell.getStringCellValue();
                j++;
            }
            int index = 0;
            data[index] = cellContent;
            for (Row temp : sheet) {
                if (index == 0) {
                    index++;
                    continue;
                }
                int k = 0;
                String[] cellData = new String[cellNum];
                for (Cell cell : temp) {
                    if (k < cellNum) {
                        cellData[k] = format(cell);
                    }
                    k++;
                }
                data[index] = cellData;
                index++;
            }
            result.add(data);
        }
        return result;
    }

    private static String format(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                }
                return NumberToTextConverter.toText(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            default:
                return cell.getStringCellValue();
        }
    }

    public static void main(String[] args) throws Exception {
        File file = new File("d://data//f.xlsx");
        List<String[][]> result = ExcelUtils.importExcel(file);
        System.out.println(result.size());
    }
}
