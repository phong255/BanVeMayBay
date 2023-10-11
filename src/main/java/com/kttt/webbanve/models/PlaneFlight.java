package com.kttt.webbanve.models;

import jakarta.persistence.*;

@Entity
@Table(name = "plane_flight")
public class PlaneFlight {

    @JoinColumn(name = "flightID")
    private int flightID;

    @Id
    @JoinColumn(name = "planeID")
    private int planeID;

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getPlaneID() {
        return planeID;
    }

    public void setPlaneID(int planeID) {
        this.planeID = planeID;
    }
}
