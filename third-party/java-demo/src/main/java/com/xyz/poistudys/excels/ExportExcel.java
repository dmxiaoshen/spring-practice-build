package com.xyz.poistudys.excels;

/**
 * Created by hzhsg on 2018/9/5.
 */

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 导出统计分析excel文件接口
 * 本文件使用了ｊｄｋ８的新特性，故最小运行版本为ｊｄｋ１。８
 * @author Wang Weiwei <email>weiwei02@vip.qq.com / weiwei.wang@100credit.com</email>
 * @version 1.0
 * @sine 2017/4/18
 */
public interface ExportExcel<T> {
    /**
     * 将字节数组写出到servlet输出流
     * @param response  http回应对象，为excel回应的目的地
     * @param list 要导出到 excel的数据集合
     *  @param titles excel的标题 通常取第一行作为excel的标题
     * */
//    default void exportExcel(HttpServletResponse response,List<T> list,String[] titles) throws IOException  {
//        byte[] bytes = selectExcel(list,titles);
//        response.setContentType("application/x-msdownload");
//        response.setHeader("Content-Disposition", "attachment;filename=" + UUIDUtils.getUUID() + ".xlsx");
//        response.setContentLength(bytes.length);
//        response.getOutputStream().write(bytes);
//        response.getOutputStream().flush();
//        response.getOutputStream().close();
//    }

    default void exportExcel(String filePath, List<T> list, String[] titles) throws IOException{
        byte[] bytes = selectExcel(list,titles);
        File file = new File(filePath);
        if(!file.exists()){
            file.createNewFile();
        }
       FileOutputStream out = new FileOutputStream(file);
        out.write(bytes);
        out.flush();
        out.close();
    }
    /**
     * 选择要导出的文件 导出的excel 属于office 2007格式的文件
     * @param list excel文件内容
     *  @param titles excels 文件的标题
     * @return 已经生成excel文件的字节数组
     * */
    default byte[] selectExcel(List<T> list, String[] titles) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        generateExcelTitle(titles,sheet);
        eachListAndCreateRow(list,sheet);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        return out.toByteArray();
    }

    /**
     * 遍历集合，并创建单元格行
     * @param list 数据集合
     *  @param sheet 工作簿
     * */
    default void eachListAndCreateRow(List<T> list, Sheet sheet){
        for (int i = 0,j = 1; i < list.size(); i ++,j++){
            T t = list.get(i);
            Row row = sheet.createRow(j);
            generateExcelForAs(t,row);
        }
    }

    /**
     * 生成excel文件的标题
     * */
    default void generateExcelTitle(String[] titles, Sheet sheet){
        Row row = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++){
            row.createCell(i,1).setCellValue(titles[i]);
        }
    }

    /**
     * 创建excel内容文件
     * @param t 组装excel 文件的内容
     * @param row 当前excel 工作行
     * */
    void generateExcelForAs(T t, Row row);

    /**
     * 当发生错误时如此回应信息
     * */
//    default void errorResponse(HttpServletResponse response){
//        byte[] message = JSONObject.toJSONString(HttpResultFactory.builderFailtureResult("导出excel文件错误,请重试!")).getBytes();
//        response.setContentType("text/json;charset=UTF-8");
//        response.setContentLength(message.length);
//        try {
//            response.getOutputStream().write(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
