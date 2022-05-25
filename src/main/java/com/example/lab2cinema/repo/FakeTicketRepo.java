package com.example.lab2cinema.repo;

import com.example.lab2cinema.model.Place;
import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;
import com.example.lab2cinema.model.TicketStatus;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakeTicketRepo implements TicketRepo{

    public static final int DEFAULT_PRICE = 100;
    private static final Map<Integer,List<Ticket>> ticketsForAllSeances = new HashMap<>();

    @Override
    public List<Ticket> findTicketsBySeanceId(Integer seanceId) {
        return ticketsForAllSeances.get(seanceId);
    }

    @Override
    public void generateTickets(int seanceId, int amountOfTickets) {
        final int maxRow = 5;
        final int maxColumn = 10;

        final int maxAmountOfTickets = maxColumn * maxRow;

        if (amountOfTickets > maxAmountOfTickets) {
            throw new IllegalArgumentException("Amount of tickets is bigger than we can afford");
        }

        var tickets = new ArrayList<Ticket>();

        for (int i = 1; i <= amountOfTickets; i++) {


            final var place = Place.fromNumber(i, maxColumn);

            //todo: refactor
            final var ticket = new Ticket(i, place, new Seance(), DEFAULT_PRICE, TicketStatus.NotBought,null);

            tickets.add(ticket);
        }

        ticketsForAllSeances.put(seanceId, tickets);
    }

    @Override
    public List<Ticket> changePriceForAllTickets(int seanceId, int newPrice) {
        List<Ticket> tickets = ticketsForAllSeances.get(seanceId);
        tickets.forEach(ticket -> ticket.setPrice(newPrice));
        return tickets;
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
