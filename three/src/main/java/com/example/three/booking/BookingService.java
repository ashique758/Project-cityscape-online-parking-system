package com.example.three.booking;

import com.example.three.user.User;
import com.example.three.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository ;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<booking> getBookings(){
        return bookingRepository.findAll();
    }


    public void addNewBooking (booking booking){
        System.out.println("add booking method in servie");
        //Optional<booking> bookingbyId = bookingRepository.findByBookingid(booking.bookingid);

        System.out.println("printing User details: "+ booking);

        bookingRepository.save(booking);
    }

    public List<booking> getBookingsByUserEmail(String userEmail) {
        return bookingRepository.findBookingsByUserEmail(userEmail);
    }



//    public void cancelBookingById(int bookingId) {
//        // Implement your logic to cancel a booking by ID
//        Long bookingIdlong= Long.valueOf(bookingId);
//        Optional<booking> bookingOptional = bookingRepository.findById(bookingIdlong);
//
//        if (bookingOptional.isPresent()) {
//            booking booking = bookingOptional.get();
//            // Implement any necessary logic before deleting the booking
//            bookingRepository.delete(booking); // Delete the booking
//        } else {
//            // Handle case where booking ID is not found
//            throw new NoSuchElementException("Booking with ID " + bookingId + " not found");
//        }
//    }

    public void deletebookingbyId(Long id) {
        bookingRepository.deleteById(id);
    }

    @Transactional
    public void deleteBookingByIdAndEmail(Long bookingId, String userEmail) {
        bookingRepository.deleteByBookingidAndUserEmail(bookingId, userEmail);
    }
}