package com.example.three.booking;

import jakarta.persistence.*;


@Entity
@Table
public class booking {

    @Id
    @SequenceGenerator(
            name = "booking_sequence",
            sequenceName = "booking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "booking_sequence",
            strategy = GenerationType.SEQUENCE)
    int bookingid;

    String parkingslotplaceid;
    String parkinglotaddress;
    String parkingslotnumber;

    Boolean slotavailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id") // Adjust this to your database schema
    private Lot lot;



    String vehiclenumber;
    String checkindate;
    String checkoutdate;
    String Checkintime;
    String checkouttime;

    String userEmail;



    public booking() {
    }

    public booking(int bookingid, Lot lot,Boolean slotavailable, String userEmail, String parkinglotaddress, String parkingslotnumber, String parkingslotplaceid,String vehiclenumber, String checkindate, String checkoutdate, String checkintime, String checkouttime) {
        this.bookingid = bookingid;
        this.parkinglotaddress = parkinglotaddress;
        this.parkingslotnumber = parkingslotnumber;
        this.parkingslotplaceid =parkingslotplaceid;
        this.vehiclenumber = vehiclenumber;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
        Checkintime = checkintime;
        this.checkouttime = checkouttime;
        this.userEmail=userEmail;
        this.slotavailable=slotavailable;
        this.lot=lot;
    }

    public booking(Boolean slotavailable,Lot lot,String userEmail, String parkinglotaddress, String parkingslotnumber, String parkingslotplaceid,String vehiclenumber, String checkindate, String checkoutdate, String checkintime, String checkouttime) {
        this.parkinglotaddress = parkinglotaddress;
        this.parkingslotnumber = parkingslotnumber;
        this.parkingslotplaceid =parkingslotplaceid;
        this.vehiclenumber = vehiclenumber;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
        Checkintime = checkintime;
        this.checkouttime = checkouttime;
        this.userEmail=userEmail;
        this.slotavailable=slotavailable;
        this.lot=lot;

    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Boolean getSlotavailable() {
        return slotavailable;
    }

    public void setSlotavailable(Boolean slotavailable) {
        this.slotavailable = slotavailable;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getParkingslotplaceid() {
        return parkingslotplaceid;
    }

    public void setParkingslotplaceid(String parkingslotplaceid) {
        this.parkingslotplaceid = parkingslotplaceid;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public String getParkinglotaddress() {
        return parkinglotaddress;
    }

    public void setParkinglotaddress(String parkinglotaddress) {
        this.parkinglotaddress = parkinglotaddress;
    }

    public String getParkingslotnumber() {
        return parkingslotnumber;
    }

    public void setParkingslotnumber(String parkingslotnumber) {
        this.parkingslotnumber = parkingslotnumber;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public String getCheckintime() {
        return Checkintime;
    }

    public void setCheckintime(String checkintime) {
        Checkintime = checkintime;
    }

    public String getCheckouttime() {
        return checkouttime;
    }

    public void setCheckouttime(String checkouttime) {
        this.checkouttime = checkouttime;
    }

    @Override
    public String toString() {
        return "booking{" +
                "bookingid=" + bookingid +
                ", parkinglotaddress='" + parkinglotaddress + '\'' +
                ", parkingslotnumber='" + parkingslotnumber + '\'' +
                ", parkingslotplaceid='" + parkingslotplaceid + '\'' +
                ", vehiclenumber='" + vehiclenumber + '\'' +
                ", checkindate='" + checkindate + '\'' +
                ", checkoutdate='" + checkoutdate + '\'' +
                ", Checkintime='" + Checkintime + '\'' +
                ", checkouttime='" + checkouttime + '\'' +
                '}';
    }
}
