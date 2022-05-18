package com.example.lab2cinema.rest;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;
import com.example.lab2cinema.services.SeanceService;
import com.example.lab2cinema.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public Ticket buyTicket(@RequestParam Integer seanceId,
                            @RequestParam Integer ticketNumber,
                            @RequestParam String userEmail) {
        Ticket ticket = ticketService.getTicketByNumber(seanceId, ticketNumber);

        if (!ticket.isBought()) {
            ticket.setUserEmail(userEmail);
            ticketService.buyTicket(seanceId, ticketNumber);
        }

        return ticket;
    }

    @PostMapping("/admin/generate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void generateTicketsForSeance(@RequestParam int seanceId,
                                         @RequestParam int amountOfTickets,
                                         @RequestParam int priceForTickets) {;
        ticketService.generateTicketsForSeance(seanceId,amountOfTickets);
        ticketService.updatePriceForAllTicketsOnSeance(seanceId,priceForTickets);
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
