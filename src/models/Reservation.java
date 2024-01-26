package models;

import java.time.LocalDate;

public class Reservation {
    public Reservation(int id, LocalDate date, String guestNationalCode, double paymentAmount, int durationOfStay, int roomNumber, String status, int roomId) {
        this.id = id;
        this.date = date;
        this.guestNationalCode = guestNationalCode;
        this.paymentAmount = paymentAmount;
        this.durationOfStay = durationOfStay;
        this.roomNumber = roomNumber;
        this.status = status;
        this.roomId = roomId;
    }

    public  Reservation() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getGuestNationalCode() {
        return guestNationalCode;
    }

    public void setGuestNationalCode(String guestNationalCode) {
        this.guestNationalCode = guestNationalCode;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(int durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    private int id;
    private LocalDate date;
    private String guestNationalCode;
    private double paymentAmount;
    private int durationOfStay;
    private int roomNumber;
    private String status;
    private int roomId;


    // toString() method for easy debugging
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", date=" + date +
                ", guestNationalCode='" + guestNationalCode + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", durationOfStay=" + durationOfStay +
                ", roomNumber=" + roomNumber +
                ", status='" + status + '\'' +
                ", roomId=" + roomId +
                '}';
    }
}
