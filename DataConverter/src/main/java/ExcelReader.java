import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.Iterator;

public class ExcelReader {
//    public static final String FILE_NAME = "E:\\RUSL\\Research\\Projects\\Data\\New\\Xcel\\Sentences\\Sentimented\\sentences2.xls";
    public static final String FILE_NAME = "E:\\RUSL\\Research\\Projects\\Data\\New\\Xcel\\Sentences\\sentencesAll.xls";

    public static void main(String[] args) {

        try{
            File file = new File(FILE_NAME);
            InputStream stream = new FileInputStream(file);
            Workbook workbook = new HSSFWorkbook(stream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();

//            System.out.println(sheet.getLastRowNum());
//            System.out.println(sheet.getPhysicalNumberOfRows());
//            System.out.println(sheet.getRow(500));

            for (int i = 1; i<sheet.getPhysicalNumberOfRows()-1; i++){
                System.out.print(i + "\t");

                Row row = sheet.getRow(i);
                String idSenttence = row.getCell(0).toString();
                String reviewsId = row.getCell(1).toString();
                String sentences = row.getCell(2).toString();
                String sentiment = row.getCell(3).toString();
                String sentiProb = row.getCell(4).toString();
                String keywords = row.getCell(5).toString();
                String keywordsProb = row.getCell(6).toString();

                String[] keywordsArry = keywords.split("/");
                String[] keywordsProbArry = keywordsProb.split("/");
                String keywordVal = "";
                String keywordValProb = "";

                for (int j = 0; j<keywordsArry.length; j++){
                    System.out.print(idSenttence + "\t");
                    System.out.print(reviewsId + "\t");
                    System.out.print(sentences + "\t");
                    System.out.print(sentiment + "\t");
                    System.out.print(sentiProb + "\t");
                    System.out.print(keywordsArry[j] + "\t");
                    if (keywordsProbArry.length>j) System.out.print(keywordsProbArry[j] + "\t");
                    keywordVal = keywordsArry[j];
                    if (keywordsProbArry.length>j) keywordValProb = keywordsProbArry[j];
                    System.out.println();
//                    DBClass.saveKeywords(idSenttence,keywordVal,keywordValProb);
                }

//                DBClass.saveSentences(idSenttence,reviewsId,sentences,sentiment,sentiProb);

                System.out.println("==============================================================================");
            }


            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }



//        try {
//
//            // Creating a Workbook from an Excel file (.xls or .xlsx)
//            File file = new File(FILE_NAME);
//            InputStream stream = new FileInputStream(file);
//            Workbook workbook = new HSSFWorkbook(stream);
//
//            // Retrieving the number of sheets in the Workbook
//            System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
//
//        /*
//           =============================================================
//           Iterating over all the sheets in the workbook (Multiple ways)
//           =============================================================
//        */
//
//            // 1. You can obtain a sheetIterator and iterate over it
//            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
//            System.out.println("Retrieving Sheets using Iterator");
//            while (sheetIterator.hasNext()) {
//                Sheet sheet = sheetIterator.next();
//                System.out.println("=> " + sheet.getSheetName());
//            }
//
//            // 2. Or you can use a for-each loop
//            System.out.println("Retrieving Sheets using for-each loop");
//            for (Sheet sheet : workbook) {
//                System.out.println("=> " + sheet.getSheetName());
//            }
//
//            // 3. Or you can use a Java 8 forEach with lambda
//            System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
//            workbook.forEach(sheet -> {
//                System.out.println("=> " + sheet.getSheetName());
//            });
//
//        /*
//           ==================================================================
//           Iterating over all the rows and columns in a Sheet (Multiple ways)
//           ==================================================================
//        */
//
//            // Getting the Sheet at index zero
//            Sheet sheet = workbook.getSheetAt(0);
//
//            // Create a DataFormatter to format and get each cell's value as String
//            DataFormatter dataFormatter = new DataFormatter();
//
//            // 1. You can obtain a rowIterator and columnIterator and iterate over them
//            System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
//            Iterator<Row> rowIterator = sheet.rowIterator();
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//
//                // Now let's iterate over the columns of the current row
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    String cellValue = dataFormatter.formatCellValue(cell);
//                    System.out.print(cellValue + "\t");
//                }
//                System.out.println();
//            }
//
//            // 2. Or you can use a for-each loop to iterate over the rows and columns
//            System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    String cellValue = dataFormatter.formatCellValue(cell);
//                    System.out.print(cellValue + "\t");
//                }
//                System.out.println();
//            }
//
//            // 3. Or you can use Java 8 forEach loop with lambda
//            System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
//            sheet.forEach(row -> {
//                row.forEach(cell -> {
//                    String cellValue = dataFormatter.formatCellValue(cell);
//                    System.out.print(cellValue + "\t");
//                });
//                System.out.println();
//            });
//
//            // Closing the workbook
//            workbook.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

/*
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }

                }
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

*/

    }
}