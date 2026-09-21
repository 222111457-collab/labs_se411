package edu.spu.se411;

import edu.spu.se411.exception.InvalidArgumentException;
import edu.spu.se411.exception.MissingInformationException;
import edu.spu.se411.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

   
    public static double computeTotalPrice(Booking booking) throws MissingInformationException, InvalidArgumentException {
        return booking.calculateTotalPrice();
    }

    public static void main(String[] args) {
        logger.info("Application is starting...");

        List<Booking> bookings = new ArrayList<>();

       
        FlightBooking flight1 = new FlightBooking("FL-101", "Ahmad Ali", LocalDate.now(), "Riyadh", 300.0);
        flight1.setLuggageWeight(20.0);
        bookings.add(flight1);

       
        TrainBooking train1 = new TrainBooking("TR-202", "Sara Hassan", LocalDate.now(), "Jeddah", TrainBooking.SeatClass.FIRST_CLASS);
        train1.setDistanceKm(450.0);
        bookings.add(train1);

       
        CarRentalBooking car1 = new CarRentalBooking("CR-303", "Omar Khaled", LocalDate.now(), "Dammam", 50.0);
        car1.setRentalDays(5);
        bookings.add(car1);

      
        FlightBooking flight2 = new FlightBooking("FL-102", "Mona Youssef", LocalDate.now(), "Madinah", 200.0);
        bookings.add(flight2);

     
        CarRentalBooking car2 = new CarRentalBooking("CR-304", "Fahad Salem", LocalDate.now(), "Abha", 40.0);
        car2.setRentalDays(45);
        bookings.add(car2);

        for (Booking booking : bookings) {
            try {
                double total = computeTotalPrice(booking);
                logger.info("Successfully calculated price for Booking ID [{}]: ${}", booking.getBookingId(), total);
            } catch (MissingInformationException e) {
                logger.error("Missing Information Error for Booking ID [{}]: {}", booking.getBookingId(), e.getMessage());
            } catch (InvalidArgumentException e) {
                logger.error("Invalid Argument Error for Booking ID [{}]: {}", booking.getBookingId(), e.getMessage());
            }
        }

        logger.info("Application is stopping...");
    }
}