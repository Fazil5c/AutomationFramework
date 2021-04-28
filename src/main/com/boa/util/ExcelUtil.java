package com.boa.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ExcelUtil {
    XSSFWorkbook workbook;
    int keyColumnIndex;
    ArrayList<String> columnHeaders;

    public LinkedHashMap<String, String> readExcelSheet(String fileName, String sheetName, String keyColumnName, String valueColumnName) {
        LinkedHashMap<String, String> map = new LinkedHashMap();
        try {
            if (DataUtil.isEmpty(keyColumnName)) {
                System.err.println("Key column name is Empty or Null: " + keyColumnName);
            }
            if (DataUtil.isEmpty(valueColumnName)) {
                System.err.println("Value column name is Empty or Null: " + valueColumnName);
            }
            XSSFSheet sheet = this.readExcelFile(fileName, sheetName);
            if (DataUtil.isNull(sheet)) {
                if (workbook.getNumberOfSheets() == 1) {
                    sheet = workbook.getSheetAt(0);
                } else {
                    throw new AutomationException("Attempt to read workbook " + fileName + " ,does not contain the expected sheet :" + sheetName);
                }
            }

            this.readColumnHeaders(sheet, keyColumnName);

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                    if (i > 0) {
                        if (getData(sheet.getRow(0).getCell(j)).contains(valueColumnName.trim())) {
                            map.put(getData(sheet.getRow(i).getCell(keyColumnIndex)), getData(sheet.getRow(i).getCell(j)));
                        }
                    }
                }
            }
          /*  for (Map.Entry<String, String> maps : map.entrySet()) {
                System.out.println(maps.getKey() + "  " + maps.getValue());
            }*/
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private String getData(XSSFCell cell) {
        switch (cell.getCellTypeEnum()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return DataUtil.doubleToString(cell.getNumericCellValue());
            default:
                return null;
        }
    }


    private void readColumnHeaders(XSSFSheet sheet, String keyColumnName) {
        columnHeaders = new ArrayList<>();
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            if (getData(sheet.getRow(0).getCell(i)).contains(keyColumnName.trim())) {
                keyColumnIndex = sheet.getRow(0).getCell(i).getColumnIndex();
                columnHeaders.add(String.valueOf(sheet.getRow(0).getCell(i)));
            }
        }
        if (!columnHeaders.contains(keyColumnName)) {
            System.err.println("keyColumn name " + keyColumnName + " does not available");
        }
    }

    private XSSFSheet readExcelFile(String fileName, String sheetName) {
        try {
            if (DataUtil.isEmpty(fileName)) {
                throw new AutomationException("File name is Empty or Null");
            }
            if (!fileName.endsWith(".xlsx")) {
                throw new AutomationException("Unsupported file : " + fileName);
            }
            workbook = new XSSFWorkbook(new FileInputStream(new File(".\\src\\resources\\exceldata\\" + fileName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook.getSheet(sheetName.trim());
    }
}
