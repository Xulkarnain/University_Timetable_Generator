package models;
public class Classroom {
    String roomNumber;
    int capacity;

    public Classroom(String room, int capacity) {
        this.roomNumber = room;
        this.capacity = capacity;
    }
    public String getRoomNumber() {
    return roomNumber;
    }

}
