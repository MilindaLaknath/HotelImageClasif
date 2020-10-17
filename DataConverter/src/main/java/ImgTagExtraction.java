import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ImgTagExtraction {

    public static final String FILE_NAME = "/home/milinda/Documents/RUSL/Research/imagelinks.xls";

    public static void main(String[] args) {
        try {
            File file = new File(FILE_NAME);
            InputStream stream = new FileInputStream(file);
            Workbook workbook = new HSSFWorkbook(stream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();

//            System.out.println(sheet.getLastRowNum());
//            System.out.println(sheet.getPhysicalNumberOfRows());
//            System.out.println(sheet.getRow(500));

            for (int i = 1; i < sheet.getPhysicalNumberOfRows() - 1; i++) {
                System.out.print(i + "\t");

                Row row = sheet.getRow(i);
                String idreviews = row.getCell(0).toString();
                String img_name = row.getCell(1).toString();
                String hotel_name = row.getCell(2).toString();
                String img_url = row.getCell(3).toString();

                System.out.print(idreviews + "\t");
                System.out.print(img_name + "\t");
                System.out.print(hotel_name + "\t");
                System.out.print(img_url + "\t");
                System.out.println("");

//                DBClass.saveSentences(idreviews,img_name,hotel_name,img_url,sentiProb);

                System.out.println("==============================================================================");
            }


            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
