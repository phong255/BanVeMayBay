package com.kttt.webbanve.Configs;

import com.kttt.webbanve.models.Seat;
import com.kttt.webbanve.models.Ticket;
import com.kttt.webbanve.services.SeatManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
@Component
public class updateSeatTaskTimer extends TimerTask {
    final static Logger logger = LoggerFactory.getLogger(updateSeatTaskTimer.class);
    @Autowired
    SeatManagerService seatManagerService;
    @Scheduled(fixedDelay = 300L)
    @Override
    public void run() {
        ArrayList<Seat> seats = seatManagerService.getAllSeats();
        try {
            seatManagerService.updateSeat(seats);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
    }
}
