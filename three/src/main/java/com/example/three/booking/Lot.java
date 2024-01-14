package com.example.three.booking;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lotName;
    private String lotAddress;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lot")
//    private List<Slot> availableSlots;

    @OneToOne(cascade = CascadeType.ALL)
    private Slot slot;


    public Lot() {
    }

    public Lot(Long id, String lotName, String lotAddress, Slot slot) {
        this.id = id;
        this.lotName = lotName;
        this.lotAddress = lotAddress;
        this.slot=slot;

    }

    public Lot(String lotName, String lotAddress, Slot slot) {
        this.lotName = lotName;
        this.lotAddress = lotAddress;
        this.slot=slot;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public String getLotAddress() {
        return lotAddress;
    }

    public void setLotAddress(String lotAddress) {
        this.lotAddress = lotAddress;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
