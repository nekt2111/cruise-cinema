package com.example.lab2cinema.repo.model;

import com.example.lab2cinema.model.Place;
import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;
import com.example.lab2cinema.model.TicketStatus;
import com.example.lab2cinema.repo.SeanceRepo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TicketMapper implements RowMapper<Ticket> {

    private SeanceRepo seanceRepo;
    public TicketMapper(SeanceRepo seanceRepo){
        this.seanceRepo = seanceRepo;
    }

    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setPlace(Place.fromNumber(rs.getInt("number"),10));
        Seance seance = seanceRepo.getSeanceById(rs.getInt("seance_id"));
        ticket.setSeance(seance);
        ticket.setPrice(rs.getInt("price"));
        ticket.setStatus(TicketStatus.getFromNumber(rs.getInt("ticket_status")));
        ticket.setUserEmail(rs.getString("user_email"));
        return ticket;
    }
}
