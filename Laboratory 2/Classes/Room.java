package Classes;

public class Room {
    private String number;
    private String type;
    private byte floor;

    public Room(String number, String type, byte floor) {
        this.number = number;
        this.type = type;
        this.floor = floor;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFloor(byte floor) {
        this.floor = floor;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public byte getFloor() {
        return floor;
    }

    public String getRoomData() {
        return this.number + " " + this.type + " " + this.floor;
    }
}
