package com.example.lab2cinema.repo;

import com.example.lab2cinema.model.Place;
import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;
import com.example.lab2cinema.model.TicketStatus;
import com.example.lab2cinema.repo.model.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.lab2cinema.model.Ticket.DEFAULT_PRICE;

@Repository
@Primary
public class TicketRepoImpl implements TicketRepo {
    
    private JdbcTemplate jdbcTemplate;

    private TicketMapper ticketMapper;

    private static final String SELECT_BY_SEANCE_ID = "SELECT * FROM ticket WHERE seance_id = ?;";
    private static final String SELECT_BY_SEANCE_ID_AND_NUMBER = "SELECT * FROM ticket WHERE seance_id = ? AND number = ?;";
    private static final String UPDATE_PRICE = "UPDATE ticket SET price = ? WHERE seanceId = ?;";
    private static final String DELETE_BY_SEANCE_ID = "DELETE from ticket where seance_id = ?";
    private static final String INSERT_TICKET = "INSERT INTO ticket(number,seance_id,price,ticket_status,user_email) values (?,?,?,?,?)";
    private static final String UPDATE_STATUS = "UPDATE ticket SET ticket_status = ? WHERE seanceId = ? AND number = ?;";

    public TicketRepoImpl(JdbcTemplate jdbcTemplate, TicketMapper ticketMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public List<Ticket> findTicketsBySeanceId(Integer seanceId) {
        return jdbcTemplate.query((Connection c) -> {
            PreparedStatement preparedStatement = c.prepareStatement(SELECT_BY_SEANCE_ID);
            preparedStatement.setInt(1, seanceId);
            return preparedStatement;
        },ticketMapper);
    }

    @Override
    public void generateTickets(int seanceId, int amountOfTickets) {
        deleteAllTicketWithSeanceId(seanceId);
        final int maxRow = 5;
        final int maxColumn = 10;
        final int maxAmountOfTickets = maxColumn * maxRow;
        if (amountOfTickets > maxAmountOfTickets) {
            throw new IllegalArgumentException("Amount of tickets is bigger than we can afford");
        }
        for (int i = 1; i <= amountOfTickets; i++) {
            final var place = Place.fromNumber(i, maxColumn);
            System.out.println(seanceId);
            jdbcTemplate.update((Connection c) -> {
                PreparedStatement preparedStatement = c.prepareStatement(INSERT_TICKET);
                preparedStatement.setInt(1,place.getNumber());
                preparedStatement.setInt(2,seanceId);
                preparedStatement.setInt(3,DEFAULT_PRICE);
                preparedStatement.setInt(4, 1);
                preparedStatement.setString(5,null);
                return preparedStatement;
            });
        }
    }

    @Override
    public List<Ticket> changePriceForAllTickets(int seanceId, int newPrice) {
            jdbcTemplate.update((Connection c) -> {
                PreparedStatement preparedStatement = c.prepareStatement(UPDATE_PRICE);
                preparedStatement.setInt(1, newPrice);
                preparedStatement.setInt(2, seanceId);
                return preparedStatement;
            });

            return findTicketsBySeanceId(seanceId);
    }

    @Override
    public Ticket changeTicketStatus(Integer seanceId, Integer ticketNumber, TicketStatus ticketStatus) {
            jdbcTemplate.update((Connection c) -> {
                PreparedStatement preparedStatement = c.prepareStatement(UPDATE_STATUS);
                preparedStatement.setInt(1, ticketStatus.toInt());
                preparedStatement.setInt(2, seanceId);
                preparedStatement.setInt(3, ticketNumber);
                return preparedStatement;
            });

            return findTicketByNumber(seanceId,ticketNumber).get();
    }

    @Override
    public Optional<Tikcet> findTicketByNumber(Integer seanceId, Integer number) {
        return jdbcTemplate.query((Connection c) -> {
            PreparedStatement preparedStatement = c.prepareStatement(SELECT_BY_SEANCE_ID_AND_NUMBER);
            preparedStatement.setInt(1, seanceId);
            preparedStatement.setInt(2, number);
            return preparedStatement;
        },ticketMapper);
    }

    private void deleteAllTicketWithSeanceId(Integer seanceId) {
        jdbcTemplate.update((Connection c) -> {
            PreparedStatement preparedStatement = c.prepareStatement(DELETE_BY_SEANCE_ID);
            preparedStatement.setInt(1,seanceId);
            return preparedStatement;
        });
    }
}