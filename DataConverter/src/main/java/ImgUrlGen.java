import java.sql.*;
import java.util.Vector;

public class ImgUrlGen {

    public static void main(String[] args) {

        try {
            Connection con = DBClass.getConnction();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idreviews,img_name,hotel_name FROM hotel_review.reviews");
            Vector<ImgUrl> v = new Vector();

            while (rs.next()){
                ImgUrl imgUrlCls = new ImgUrl();
                String imgname = rs.getString("img_name");
                String hotelName = rs.getString("hotel_name");
                String imgUrl = "";
                imgUrlCls.setIdreviews(rs.getString("idreviews"));
                imgUrlCls.setImgName(imgname);
                imgUrlCls.setHotelName(hotelName);
                imgUrl = "https://storage.googleapis.com/ruslimgbkt2/"+imgname+".jpg";
//                switch (hotelName){
//                    case "CASA Colombo": imgUrl = "https://storage.googleapis.com/ruslimgbkt/CASA/"+imgname+".jpg"; break;
//                    case "Galle Fort Hotel": imgUrl = "https://storage.googleapis.com/ruslimgbkt/GalleFortHotel/"+imgname+".jpg"; break;
//                    case "Jetwing Jaffna": imgUrl = "https://storage.googleapis.com/ruslimgbkt/JetwingJaffna/"+imgname+".jpg"; break;
//                    case "Mahaweli Reach": imgUrl = "https://storage.googleapis.com/ruslimgbkt/MahaweliReach/"+imgname+".jpg"; break;
//                    case "Nilaveli Beach Hotel": imgUrl = "https://storage.googleapis.com/ruslimgbkt/NilaveliBeachHotel/"+imgname+".jpg"; break;
//                    default:imgUrl = "";
//                }
                imgUrlCls.setImgUrl(imgUrl);
                v.add(imgUrlCls);
            }

            PreparedStatement ps = null;
            for (ImgUrl iu : v) {
                System.out.println(iu);

                ps = DBClass.getConnction().prepareStatement("INSERT INTO `hotel_review`.`images` (`idreviews`, `img_name`, `hotel_name`, `img_url`) VALUES (?, ?, ?, ?)");

                // set the preparedstatement parameters
                ps.setString(1, iu.getIdreviews());
                ps.setString(2, iu.getImgName());
                ps.setString(3, iu.getHotelName());
                ps.setString(4, iu.getImgUrl());

                ps.executeUpdate();
            }
            if(ps!=null) ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

