package com.tutorialsninja.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {


    public static String[] readFromExcel(int index) {
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\UserData.xlsx");

            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);

            int TotalNumberOfCols = sheet.getRow(0).getLastCellNum();
            String[] arrayExcelData = new String[TotalNumberOfCols];

            for (int j = 0; j < TotalNumberOfCols; j++) {
                XSSFRow row = sheet.getRow(index);
                arrayExcelData[j] = row.getCell(j).toString();
            }

            wb.close();
            return arrayExcelData;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized void writeToExcel(String[] arrayExcelData) {
        String path = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\UserData.xlsx";

        // try-with-resources
        // Opens resources (such as files or connections)
        // Ensures they are automatically closed even if an error occurs
        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = wb.getSheetAt(0);
            int nextRowNum = sheet.getLastRowNum() + 1;
            XSSFRow row = sheet.createRow(nextRowNum);

            for (int j = 0; j < arrayExcelData.length; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(arrayExcelData[j]);
            }

            try (FileOutputStream fos = new FileOutputStream(path)) {
                wb.write(fos);
                fos.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
