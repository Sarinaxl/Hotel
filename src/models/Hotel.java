package models;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private Manager manager;
    private List<Employee> employees = new ArrayList<>();
    private List<Reservation> reservationQueue = new ArrayList<>();
    private List<Guest> guests = new ArrayList<>();
    private int availableRooms;
    private List<Room> rooms = new ArrayList<>();

}
