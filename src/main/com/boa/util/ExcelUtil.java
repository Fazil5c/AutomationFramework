package com.boa.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.*;

public class ExcelUtil {
    XSSFWorkbook workbook;

    public static void main(String[] args) {
        ExcelUtil util=new ExcelUtil();
        util.readExcelSheet("Data.xlsx","Player Details","DOB");
    }

    public  void readExcelSheet(String fileName, String sheetName, String keyColumnList) {
        ArrayList<String> headers = new ArrayList<>();
        LinkedHashMap<String, String> map = new LinkedHashMap();
        try {
            XSSFSheet sheet = this.readExcelFile(fileName, sheetName);
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                    if (sheet.getRow(0).getCell(j).toString().contains(keyColumnList.trim())) {
                        map.put(sheet.getRow(i).getCell(0).toString(), sheet.getRow(i).getCell(j).toString());
                    }
                }
            }
            for (Map.Entry<String, String> maps : map.entrySet()) {
                System.out.println(maps.getKey() + "  " + maps.getValue());
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    private XSSFSheet readExcelFile(String fileName, String sheetName) {
        try {
            workbook = new XSSFWorkbook(new FileInputStream(new File(".\\src\\resources\\exceldata\\" + fileName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook.getSheet(sheetName.trim());
    }
}
