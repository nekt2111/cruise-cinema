package com.example.lab2cinema.controllers;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;
import com.example.lab2cinema.model.TicketStatus;
import com.example.lab2cinema.services.SeanceService;
import com.example.lab2cinema.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seance")
public class SeanceController {

    private final SeanceService seanceService;
    private final TicketService ticketService;
    public SeanceController(SeanceService seanceService, TicketService ticketService){
        this.seanceService = seanceService;
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public String getSeance(@PathVariable int id, Model model){
        Seance seance = seanceService.findSeanceById(id);
        List<Ticket> tickets = ticketService.findTicketsBySeanceId(id);
        int amountOfBoughtTickets = countAmountOfBoughtTickets(tickets);

        model.addAttribute("seance",seance);
        model.addAttribute("tickets",tickets);
        model.addAttribute("amountOfBoughtTickets",amountOfBoughtTickets);

        return "seance";
    }

    @GetMapping("/admin/add")
    public String addSeance(Model model,@RequestParam(required = false) Integer seanceId) {
        Seance seance = new Seance();
        boolean doesSeanceAlreadyExists = false;
        if(seanceId != null){
            seance = this.seanceService.findSeanceById(seanceId);
            doesSeanceAlreadyExists = true;
        }
        model.addAttribute("seance",seance);
        model.addAttribute("doesSeanceAlreadyExists",doesSeanceAlreadyExists);
        return "addNewSeance";
    }

    @GetMapping("/admin/manage")
    public String manageSeances(Model model){
        model.addAttribute("seances",seanceService.getAllSeances());
        return "manageSeances";
    }

    @GetMapping("/admin/manage/{seanceId}")
    public String manageSeances(Model model,@PathVariable int seanceId){
        model.addAttribute("seance",seanceService.findSeanceById(seanceId));
        List<Ticket> tickets = ticketService.findTicketsBySeanceId(seanceId);
        model.addAttribute("tickets",tickets);
        model.addAttribute("amountOfBoughtTickets",countAmountOfBoughtTickets(tickets));
        return "manageSeance";
    }

    @PostMapping("/admin/addSeance")
    public String addSeance(Seance seance, Model model) {
        int id = seanceService.addSeance(seance).getId();
        List<Seance> seances = this.seanceService.getAllSeances();
        model.addAttribute("seanceId",id);
        return String.format("redirect:/ticket/admin/manage/%s",seance.getId());
    }


    @PostMapping("/admin/update")
    public String updateSeance(Seance seance, Model model) {;
        this.seanceService.updateSeance(seance);
        return "redirect:/";
    }

    @PostMapping("/admin/delete")
    public String deleteSeance(@RequestParam int seanceId, Model model) {
        this.seanceService.deleteSeance(seanceId);
        return "redirect:/";
    }

    private int countAmountOfBoughtTickets(List<Ticket> tickets){
        int amountOfBoughtTickets = 0;
        for (Ticket ticket:tickets) {
            if(ticket.getStatus() == TicketStatus.Bought){
                amountOfBoughtTickets++;
            }
        }
        return amountOfBoughtTickets;
    }

}
