package edu.spu.se411.model;

import edu.spu.se411.config.Config;
import edu.spu.se411.exception.InvalidArgumentException;
import edu.spu.se411.exception.MissingInformationException;
import java.time.LocalDate;

public class FlightBooking extends Booking {
    private double basePrice;
    private Double luggageWeight;

    public FlightBooking(String bookingId, String customerName, LocalDate travelDate, String destinationCity, double basePrice) {
        super(bookingId, customerName, travelDate, destinationCity);
        this.basePrice = basePrice;
    }

    public void setLuggageWeight(Double luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    @Override
    public double calculateTotalPrice() throws MissingInformationException, InvalidArgumentException {
        if (luggageWeight == null) {
            throw new MissingInformationException("Luggage weight is missing for booking ID: " + getBookingId());
        }
        if (luggageWeight < 0 || luggageWeight > Config.MAX_LUGGAGE_WEIGHT) {
            throw new InvalidArgumentException("Luggage weight must be between 0 and " + Config.MAX_LUGGAGE_WEIGHT + " kg.");
        }
        return basePrice + (luggageWeight * Config.EXTRA_LUGGAGE_RATE);
    }
}