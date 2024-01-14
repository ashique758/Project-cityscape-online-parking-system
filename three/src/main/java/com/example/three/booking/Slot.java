package com.example.three.booking;


import jakarta.persistence.*;

@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean slotAvailable;
    private int slotPrice;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private Lot lot;

    public Slot() {
    }

    public Slot(Long id, boolean slotAvailable, int slotPrice, Lot lot) {
        this.id = id;
        this.slotAvailable = slotAvailable;// is this neccesary here
        this.slotPrice = slotPrice;
        this.lot = lot;
    }

    public Slot(boolean slotAvailable, int slotPrice, Lot lot) {
        this.slotAvailable = slotAvailable;
        this.slotPrice = slotPrice;
        this.lot = lot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSlotAvailable() {
        return slotAvailable;
    }

    public void setSlotAvailable(boolean slotAvailable) {
        this.slotAvailable = slotAvailable;
    }

    public int getSlotPrice() {
        return slotPrice;
    }

    public void setSlotPrice(int slotPrice) {
        this.slotPrice = slotPrice;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }
}
