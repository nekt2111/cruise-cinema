package com.example.lab2cinema.services;

import com.example.lab2cinema.model.Ticket;
import com.example.lab2cinema.model.TicketStatus;
import com.example.lab2cinema.repo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepo ticketRepo;

    public TicketRepo getTicketRepo() {
        return ticketRepo;
    }

    @Autowired
    public void setTicketRepo(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Override
    public List<Ticket> findTicketsBySeanceId(Integer seanceId) {
        return ticketRepo.findTicketsBySeanceId(seanceId);
    }

    @Override
    @Transactional
    public Ticket buyTicket(Integer seanceId, Integer ticketNumber,String email) {
        ticketRepo.updateTicketBuyerEmail(seanceId,ticketNumber,email);
        return ticketRepo.changeTicketStatus(seanceId,ticketNumber, TicketStatus.Bought);
    }

    @Override
    public Ticket getTicketByNumber(Integer seanceId, Integer ticketNumber) {
        return ticketRepo.findTicketByNumber(seanceId,ticketNumber).get();
    }

    @Override
    public List<Ticket> updatePriceForAllTicketsOnSeance(int seanceId, int newPrice) {
        return ticketRepo.changePriceForAllTickets(seanceId,newPrice);
    }

    @Override
    public void generateTicketsForSeance(int seanceId, int amountOfTickets) {
        this.ticketRepo.generateTickets(seanceId,amountOfTickets);
    }


}
