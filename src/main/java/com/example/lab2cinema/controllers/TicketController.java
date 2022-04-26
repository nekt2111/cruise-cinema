package com.example.lab2cinema.controllers;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.repo.SeanceRepo;
import com.example.lab2cinema.repo.TicketRepo;
import com.example.lab2cinema.services.SeanceService;
import com.example.lab2cinema.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private final SeanceService seanceService;
    private final TicketService ticketService;

    public TicketController(SeanceService seanceService, TicketService ticketService){
        this.seanceService = seanceService;
        this.ticketService = ticketService;
    }

    @GetMapping("/select")
    public String setTicketToBuy(@RequestParam Integer seanceId, Model model){
        Seance seance = seanceService.findSeanceById(seanceId);
        model.addAttribute("seanceName",seance.getName());
        model.addAttribute("tickets",ticketService.findTicketsBySeanceId(seanceId));
        model.addAttribute("seanceId",seanceId);

        return "ticket";
    }

    @GetMapping("/confirm")
    public String confirmBuyingTicket(@RequestParam Integer seanceId,
                                      @RequestParam Integer ticketNumber,
                                      Model model){
        model.addAttribute("seance",seanceService.findSeanceById(seanceId));
        model.addAttribute("ticket",ticketService.getTicketByNumber(seanceId,ticketNumber));
        return "confirmBuyTicket";
    }


    @PostMapping("/buy")
    public String buyTicket(@RequestParam Integer seanceId,@RequestParam Integer ticketNumber){
        ticketService.buyTicket(seanceId,ticketNumber);
        return "buyPage";
    }

    @GetMapping("/admin/manage/{seanceId}")
    public String manageTicketsForSeance(@PathVariable int seanceId, Model model){
        model.addAttribute("seanceId",seanceId);
        return "manageTickets";
    }

    @PostMapping("/admin/generateTickets")
    public String generateTicketsForSeance(@RequestParam int seanceId,
                                           @RequestParam int amountOfTickets) {;
        ticketService.generateTicketsForSeance(seanceId,amountOfTickets);
        return "redirect:/";
    }
}
