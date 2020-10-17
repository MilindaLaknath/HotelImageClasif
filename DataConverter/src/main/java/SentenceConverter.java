import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SentenceConverter {

    public static void main(String[] args) {
        try {
            Connection con =DBClass.getConnction();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM hotel_review.sentences");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("reviews_eng");
            XSSFRow row = spreadsheet.createRow(0);
            XSSFCell cell;
            cell = row.createCell(0);
            cell.setCellValue("idsent");
            cell = row.createCell(1);
            cell.setCellValue("reviewsId");
            cell = row.createCell(2);
            cell.setCellValue("sentences");
            int i = 1;
            while (resultSet.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(resultSet.getString("idsent"));
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString("reviewsId"));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString("sentences"));
                i++;
            }
            FileOutputStream out = new FileOutputStream(new File("sentences.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("File Successfully created");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}