package com.example.lab2cinema.repo;

import com.example.lab2cinema.model.Place;
import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;
import com.example.lab2cinema.model.TicketStatus;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class FakeTicketRepo implements TicketRepo{

    private static final Map<Integer,List<Ticket>> ticketsForAllSeances = new HashMap<>();

    @Override
    public List<Ticket> findTicketsBySeanceId(Integer seanceId) {
        System.out.println(seanceId);
        System.out.println(ticketsForAllSeances);
        return ticketsForAllSeances.get(seanceId);
    }

    @Override
    public void generateTickets(int seanceId,int amountOfTickets){
        int maxRow = 5;
        int maxColumn = 10;

        int maxAmountOfTickets = maxColumn * maxRow;

        if(maxAmountOfTickets < amountOfTickets){
            throw new IllegalArgumentException("Amount of tickets is bigger than we can afford");
        }

        ArrayList<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < amountOfTickets ; i++) {
            tickets.add(new Ticket(i + 1,new Place(i + 1,i%5 + 1,i%10 + 1),seanceId, 100,TicketStatus.NotBought));
        }

        ticketsForAllSeances.put(seanceId,tickets);
    }

    @Override
    public Ticket changeTicketStatus(Integer seanceId, Integer number,TicketStatus status) {
        Optional<Ticket> ticketOptional = findTicketByNumber(seanceId,number);
        ticketOptional.ifPresent(ticket -> ticket.setStatus(status));
        return ticketOptional.get();
    }

    @Override
    public Optional<Ticket> findTicketByNumber(Integer seanceId, Integer number) {
        List<Ticket> ticketsForSeance = ticketsForAllSeances.get(seanceId);
        return ticketsForSeance.stream()
                .filter(ticket -> ticket.getNumber() == number)
                .findFirst();
    }
}
