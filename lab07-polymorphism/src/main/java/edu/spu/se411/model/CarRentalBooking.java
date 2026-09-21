package edu.spu.se411.model;

import edu.spu.se411.config.Config;
import edu.spu.se411.exception.InvalidArgumentException;
import edu.spu.se411.exception.MissingInformationException;
import java.time.LocalDate;

public class CarRentalBooking extends Booking {
    private double dailyRate;
    private Integer rentalDays;

    public CarRentalBooking(String bookingId, String customerName, LocalDate travelDate, String destinationCity, double dailyRate) {
        super(bookingId, customerName, travelDate, destinationCity);
        this.dailyRate = dailyRate;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    @Override
    public double calculateTotalPrice() throws MissingInformationException, InvalidArgumentException {
        if (rentalDays == null) {
            throw new MissingInformationException("Rental days are missing for booking ID: " + getBookingId());
        }
        if (rentalDays < 1 || rentalDays > Config.MAX_RENTAL_DAYS) {
            throw new InvalidArgumentException("Rental days must be between 1 and " + Config.MAX_RENTAL_DAYS + " days.");
        }
        return dailyRate * rentalDays;
    }
}