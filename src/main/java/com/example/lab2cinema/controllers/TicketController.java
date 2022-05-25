package com.example.lab2cinema.controllers;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;
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
        System.out.println(this.ticketService.findTicketsBySeanceId(seanceId));

        return "ticket";
    }

    @GetMapping("/confirm")
    public String confirmBuyingTicket(@RequestParam Integer seanceId,
                                      @RequestParam Integer ticketNumber,
                                      Model model){
        var ticket = ticketService.getTicketByNumber(seanceId,ticketNumber);
        System.out.println(ticket);
        model.addAttribute("seance", seanceService.findSeanceById(seanceId));
        model.addAttribute("ticket", ticket);

        if (Ticket.isBought(ticket)) {
            return "denyBuyTicket";
        } else {
            return "confirmBuyTicket";
        }
    }


    @PostMapping("/buy")
    public String buyTicket(@RequestParam Integer seanceId,
                            @RequestParam Integer ticketNumber,
                            @RequestParam String userEmail,
                            Model model){


        Ticket ticket = ticketService.getTicketByNumber(seanceId,ticketNumber);
        boolean tickedWasBought = false;
        if(!Ticket.isBought(ticket)) {
            ticketService.buyTicket(seanceId, ticketNumber,userEmail);
            Seance seance = seanceService.findSeanceById(seanceId);

            model.addAttribute("seance", seance);
            model.addAttribute("ticket", ticket);
            tickedWasBought = true;
        }

        model.addAttribute("ticketWasBought",tickedWasBought);

        return "buyPage";
    }

    @GetMapping("/admin/manage/{seanceId}")
    public String manageTicketsForSeance(@PathVariable int seanceId, Model model){
        Seance seance = seanceService.findSeanceById(seanceId);
        if(seance == null){
            model.addAttribute("wrongRequest",true);
        }
        else {
            model.addAttribute("seanceId", seanceId);
            model.addAttribute("seanceName", seance.getName());
            model.addAttribute("wrongRequest",false);
        }
        return "manageTickets";
    }

    @PostMapping("/admin/generateTickets")
    public String generateTicketsForSeance(@RequestParam int seanceId,
                                           @RequestParam int amountOfTickets,
                                           @RequestParam int priceForTickets) {;
        ticketService.generateTicketsForSeance(seanceId,amountOfTickets);
        ticketService.updatePriceForAllTicketsOnSeance(seanceId,priceForTickets);
        return "redirect:/";
    }
}
