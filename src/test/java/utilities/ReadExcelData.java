package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ReadExcelData {

    public String[][] readSheet() throws IOException, InvalidFormatException {
        //File myFile = new File("D:\\WorkSpace\\rest_assured_BDD\\test_data\\Book1.xlsx");
        FileInputStream myFile = new FileInputStream(new File("D:\\WorkSpace\\rest_assured_BDD\\test_data\\Book1.xlsx"));
        DataFormatter formatter = new DataFormatter();
        XSSFWorkbook wb = new XSSFWorkbook(myFile);
        //XSSFSheet sheet = wb.getSheet("sheet1");
        XSSFSheet sheet = wb.getSheetAt(0);
        int rowNumber = sheet.getPhysicalNumberOfRows();
        int colNumber = sheet.getRow(0).getLastCellNum();
        String[][] arr = new String[rowNumber-1][colNumber];
        for (int i = 1; i<rowNumber; i++){
            for (int s=0; s<colNumber;s++){
                XSSFRow row = sheet.getRow(i);
                arr[i-1][s] = formatter.formatCellValue(row.getCell(s));
            }
        }
        wb.close();
        myFile.close();
        return arr;
    }
}
