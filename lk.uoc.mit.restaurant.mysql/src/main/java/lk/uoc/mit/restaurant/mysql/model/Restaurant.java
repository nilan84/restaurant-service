package lk.uoc.mit.restaurant.mysql.model;

/**
 * Created by nilan on 9/20/15.
 */
public class Restaurant {
    private int resturantId;
    private String ResturantName;
    private String latitude;
    private String longitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getResturantName() {
        return ResturantName;
    }

    public void setResturantName(String resturantName) {
        ResturantName = resturantName;
    }

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }




}
