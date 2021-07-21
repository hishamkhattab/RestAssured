package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.apache.poi.ss.usermodel.DataFormatter;

import org.apache.poi.xssf.usermodel.*;

public class ReadExcelData {

    public String[][] readSheet() throws IOException, InvalidFormatException {
        //File myFile = new File("D:\\WorkSpace\\rest_assured_BDD\\test_data\\Book1.xlsx");
        FileInputStream myFile = new FileInputStream("D:\\WorkSpace\\rest_assured_BDD\\src\\test\\resources\\Book1.xlsx");
        DataFormatter formatter = new DataFormatter();
        XSSFWorkbook wb = new XSSFWorkbook(myFile);
        XSSFSheet sheet = wb.getSheetAt(0);
        //int rowNumber = sheet.getPhysicalNumberOfRows();
        int rowNumber = sheet.getLastRowNum(); //<--- to get number of rows
        int colNumber = sheet.getRow(0).getLastCellNum();
        String[][] arr = new String[rowNumber-1][colNumber];

        for (int i = 1; i<rowNumber; i++){
            XSSFRow row = sheet.getRow(i);
            for (int s=0; s<colNumber;s++){
                XSSFCell cell = row.getCell(s);
                arr[i-1][s] = formatter.formatCellValue(cell);
            }
        }
        wb.close();
        myFile.close();
        return arr;
    }
}
