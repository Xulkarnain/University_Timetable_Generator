package models;
public class TimeSlot {
    String day;
    String time;

    public TimeSlot(String day, String time) {
        this.day = day;
        this.time = time;
    }
    public String getDay() { return day; }
    public String getTime() { return time; }

}
