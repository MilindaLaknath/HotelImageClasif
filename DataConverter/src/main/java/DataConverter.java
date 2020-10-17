import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataConverter {

    public static void main(String[] args) {
        try {
            Connection con = DBClass.getConnction();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT distinct eng_review,review,eng_title,src_language,hotel_name FROM hotel_review.reviews ORDER BY hotel_name");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("reviews_eng");
            XSSFRow row = spreadsheet.createRow(0);
            XSSFCell cell;
            cell = row.createCell(0);
            cell.setCellValue("reviewsId");
            cell = row.createCell(1);
            cell.setCellValue("title");
            cell = row.createCell(2);
            cell.setCellValue("review");
            cell = row.createCell(3);
            cell.setCellValue("review_eng");
            cell = row.createCell(4);
            cell.setCellValue("srcLang");
            cell = row.createCell(5);
            cell.setCellValue("hotel");
            int i = 1;
            while (resultSet.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(i);
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getString("eng_title"));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString("review"));
                cell = row.createCell(3);
                cell.setCellValue(resultSet.getString("eng_review"));
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString("src_language"));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString("hotel_name"));
                i++;
            }
            FileOutputStream out = new FileOutputStream(new File("allReviewEng.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("File Successfully created");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}