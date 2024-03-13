package koutsuhi.dto;

public class TransInfoDto {

    private String id;
    private String date;
    private String d_station;
    private String a_station;
    private int fare;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getD_station() {
        return d_station;
    }

    public void setD_station(String d_station) {
        this.d_station = d_station;
    }

    public String getA_station() {
        return a_station;
    }

    public void setA_station(String a_station) {
        this.a_station = a_station;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }
}
