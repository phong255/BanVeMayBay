package com.kttt.webbanve.controllers.client;

import com.kttt.webbanve.models.Flight;
import com.kttt.webbanve.models.Plane;
import com.kttt.webbanve.models.PlaneFlight;
import com.kttt.webbanve.services.FlightServiceImpl;
import com.kttt.webbanve.services.PlaneFlightServiceImpl;
import com.kttt.webbanve.services.PlaneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("pageTitle","Homepage");
        return "index";
    }
}
