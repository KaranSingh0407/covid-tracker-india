package com.covid.cti.controller;

import com.covid.cti.models.CovidData;
import com.covid.cti.services.CovidTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CovidTrackerController {

    @Autowired
    CovidTrackerService covidTrackerService;

    @GetMapping("/")
    public String home(Model model){

        List<CovidData> allStats = covidTrackerService.getAllStats();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases", covidTrackerService.getTotalCases());
        return "home";
    }

}
