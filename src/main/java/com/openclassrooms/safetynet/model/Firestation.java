package com.openclassrooms.safetynet.model;

import lombok.Data;

@Data
public class Firestation {
    private String address;
    private int station;

    Firestation() {}
    public Firestation(String address, int station) {
        this.address = address;
        this.station = station;
    }
}
