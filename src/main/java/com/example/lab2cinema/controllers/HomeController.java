package com.example.lab2cinema.controllers;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.repo.SeanceRepo;
import com.example.lab2cinema.repo.TicketRepo;
import com.example.lab2cinema.services.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    private SeanceService seanceService;
    public HomeController(SeanceService seanceService){
        this.seanceService = seanceService;
    }


    @GetMapping("/")
    public String home(Model model){

        List<Seance> seances = seanceService.getAllSeance();

       model.addAttribute("seances",seances);

        return "index";
    }

}
