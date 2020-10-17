public class ImgUrl{
    private String idreviews;
    private String imgName;
    private String hotelName;
    private String imgUrl;

    public String getImgName() {return imgName;}
    public void setImgName(String imgName) {this.imgName = imgName;}
    public String getHotelName() {return hotelName;}
    public void setHotelName(String hotelName) {this.hotelName = hotelName;}
    public String getImgUrl() {return imgUrl;}
    public void setImgUrl(String imgUrl) {this.imgUrl = imgUrl;}
    public String getIdreviews() {return idreviews;}
    public void setIdreviews(String idreviews) {this.idreviews = idreviews;}

    @Override
    public String toString() {
        return idreviews+" "+imgName+" "+imgUrl+" "+hotelName;
    }
}