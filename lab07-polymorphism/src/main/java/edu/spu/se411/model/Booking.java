package edu.spu.se411.model;

import edu.spu.se411.exception.InvalidArgumentException;
import edu.spu.se411.exception.MissingInformationException;
import java.time.LocalDate;

public abstract class Booking {
    private String bookingId;
    private String customerName;
    private LocalDate travelDate;
    private String destinationCity;

    public Booking(String bookingId, String customerName, LocalDate travelDate, String destinationCity) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.travelDate = travelDate;
        this.destinationCity = destinationCity;
    }

    public abstract double calculateTotalPrice() throws MissingInformationException, InvalidArgumentException;

    public String getBookingId() { return bookingId; }
    public String getCustomerName() { return customerName; }
    public LocalDate getTravelDate() { return travelDate; }
    public String getDestinationCity() { return destinationCity; }
}