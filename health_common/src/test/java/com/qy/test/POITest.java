package com.qy.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class POITest {

    // 使用POI读取Excel文件中的数据
    @Test
    public void test1() throws Exception{

        // 加载指定文件，创建一个Excel对象(工作簿)
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("D:\\poi.xlsx")));
        // 读取Excel文件中第一个sheet标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        // 遍历Sheet标签页，获取每一行数据
        for (Row row : sheet){
            // 遍历行，获取每个单元格对象
            for (Cell cell : row){
                System.out.println(cell.getStringCellValue());
            }
        }

        // 关闭资源
        excel.close();

    }

    // 使用POI读取Excel文件中的数据
    @Test
    public void test2() throws Exception{

        // 加载指定文件，创建一个Excel对象(工作簿)
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("D:\\poi.xlsx")));
        // 读取Excel文件中第一个sheet标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        // 获取当前工作表中最后一个行号，需要主要：行号从0开始
        int lastRowNum = sheet.getLastRowNum();
        for(int i=0;i<=lastRowNum;i++){
            //根据行号获取行对象
            XSSFRow row = sheet.getRow(i);
            // 获得当前行最后一个单元格索引
            short lastCellNum = row.getLastCellNum();
            for(short j=0;j<lastCellNum;j++){
                String value = row.getCell(j).getStringCellValue();
                System.out.println(value);
            }
        }

        // 关闭资源
        excel.close();
    }

    // 使用POI创建和修改Excel文件中的数据
    @Test
    public void test3() throws Exception{
        //在内存中创建一个Excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表，指定工作表名称
        XSSFSheet sheet = workbook.createSheet("青屿");

        //创建行，0表示第一行
        XSSFRow row = sheet.createRow(0);
        //创建单元格，0表示第一个单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("名称");
        row.createCell(2).setCellValue("年龄");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("小明");
        row1.createCell(2).setCellValue("10");

        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("2");
        row2.createCell(1).setCellValue("小王");
        row2.createCell(2).setCellValue("20");

        //通过输出流将workbook对象下载到磁盘
        FileOutputStream out = new FileOutputStream("D:\\qy.xlsx");
        workbook.write(out);
        out.flush();
        out.close();
        workbook.close();

    }

}
