package edu.spu.se411.model;

import edu.spu.se411.config.Config;
import edu.spu.se411.exception.InvalidArgumentException;
import edu.spu.se411.exception.MissingInformationException;
import java.time.LocalDate;

public class TrainBooking extends Booking {
    public enum SeatClass { STANDARD, FIRST_CLASS }

    private SeatClass seatClass;
    private Double distanceKm;

    public TrainBooking(String bookingId, String customerName, LocalDate travelDate, String destinationCity, SeatClass seatClass) {
        super(bookingId, customerName, travelDate, destinationCity);
        this.seatClass = seatClass;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    @Override
    public double calculateTotalPrice() throws MissingInformationException, InvalidArgumentException {
        if (distanceKm == null) {
            throw new MissingInformationException("Train distance is missing for booking ID: " + getBookingId());
        }
        if (distanceKm < 1 || distanceKm > Config.MAX_TRAIN_DISTANCE) {
            throw new InvalidArgumentException("Train distance must be between 1 and " + Config.MAX_TRAIN_DISTANCE + " km.");
        }
        double rate = (seatClass == SeatClass.FIRST_CLASS) ? Config.TRAIN_FIRST_CLASS_RATE : Config.TRAIN_STANDARD_RATE;
        return distanceKm * rate;
    }
}