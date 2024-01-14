package com.example.three.booking;


import com.example.three.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<booking, Long> {


    Optional<booking> findByBookingid(Integer bookingid);


    List<booking> findBookingsByUserEmail(String userEmail);

    void deleteByBookingidAndUserEmail(Long bookingId, String userEmail);
}
