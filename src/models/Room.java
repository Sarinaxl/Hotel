package models;

public class Room {

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    private String reservedBy;

    public String getRoomNumber() {
        return String.valueOf(roomNumber);
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }


    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }


    public int getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(int isReserved) {
        this.isReserved = isReserved;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getRoomDetail() {
        return this.name + "    " + this.roomNumber;
    }

    public  Room () {

    }

    public Room(String reservedBy, String id, int roomNumber, int numberOfBeds, int isReserved, int hotelId, String name, double price) {
        this.reservedBy = reservedBy;
        this.id = id;
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.isReserved = isReserved;
        this.hotelId = hotelId;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    private String id;
    private int roomNumber;
    private int numberOfBeds;
    private int isReserved;
    private int hotelId;
    private String name;
    private double price;


}
