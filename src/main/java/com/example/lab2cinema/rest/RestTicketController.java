package com.example.lab2cinema.rest;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;
import com.example.lab2cinema.services.SeanceService;
import com.example.lab2cinema.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/ticket")
public class RestTicketController {

    private final SeanceService seanceService;
    private final TicketService ticketService;

    public RestTicketController(SeanceService seanceService, TicketService ticketService){
        this.seanceService = seanceService;
        this.ticketService = ticketService;
    }

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Ticket> buyTicket(@RequestParam Integer seanceId,
                            @RequestParam Integer ticketNumber,
                            @RequestParam String userEmail) {
        Ticket ticket = ticketService.getTicketByNumber(seanceId, ticketNumber);

        System.out.println(ticket);

        if (!ticket.isBought()) {
            ticket.setUserEmail(userEmail);
            ticket = ticketService.buyTicket(seanceId, ticketNumber);
            return new ResponseEntity<Ticket>(ticket,HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @PostMapping("/admin/generate")
    public ResponseEntity<List<Ticket>> ticketsForSeance(@RequestParam int seanceId,
                                         @RequestParam int amountOfTickets,
                                         @RequestParam int priceForTickets) {;
        ticketService.generateTicketsForSeance(seanceId,amountOfTickets);
        List<Ticket> tickets = ticketService.updatePriceForAllTicketsOnSeance(seanceId,priceForTickets);
        return new ResponseEntity(tickets,HttpStatus.CREATED);
    }

    @GetMapping("/seance")
    @ResponseStatus(HttpStatus.OK)
    public List<Ticket> getTicketsForSeance(@RequestParam int seanceId) {;
        return ticketService.findTicketsBySeanceId(seanceId);
    }

    @GetMapping("/seat")
    @ResponseStatus(HttpStatus.OK)
    public Ticket getTicketForSeanceSeat(@RequestParam Integer seanceId, @RequestParam Integer ticketNumber) {
        return ticketService.getTicketByNumber(seanceId,ticketNumber);
    }
}
