package com.kttt.webbanve.services;

import com.kttt.webbanve.TimeUtil;
import com.kttt.webbanve.models.Flight;
import com.kttt.webbanve.models.Seat;
import com.kttt.webbanve.repositories.SeatRepositories;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SeatManagerServiceImpl implements SeatManagerService{
    @Autowired
    SeatRepositories seatRepositories;
    @Autowired
    FlightService flightService;
    @Override
    public ArrayList<Seat> getAllSeats() {
        return seatRepositories.getAllSeats();
    }

    @Transactional
    @Override
    public synchronized void updateSeat(ArrayList<Seat> seats) throws ParseException {
        for (Seat seat:
             seats) {
            int busy = 0;
            List<Flight> flights = flightService.getFlightsByPlane(seat.getPlane().getPlaneID());
            for(Flight flight:flights){
                Date dateFlight = TimeUtil.stringToDate(flight.getDateFlight());
                int min = TimeUtil.stringToMinute(flight.getDepartureTime());
                if(TimeUtil.compareTime(dateFlight,min)<=0){
                    busy++;
                }
            }
            if(busy == flights.size()){
                seat.setStatus(0);
                seatRepositories.save(seat);
            }
        }
    }
}
