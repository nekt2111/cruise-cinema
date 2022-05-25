package com.example.lab2cinema.repo;

import com.example.lab2cinema.model.Ticket;
import com.example.lab2cinema.model.TicketStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class TicketRepoImpl implements TicketRepo {
    
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_BY_SEANCE_ID = "SELECT * FROM ticket WHERE seanceId = ?;";

    public SeanceRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Ticket> findTicketsBySeanceId(Integer seanceId) {
        try {
             return jdbcTemplate.query((Connection c) -> {
                PreparedStatement preparedStatement = c.prepareStatement(SELECT_BY_SEANCE_ID);
                preparedStatement.setInt(1, seanceId);
                return preparedStatement;
            },seanceRowMapper).get(0);

        }
        catch (EmptyResultDataAccessException e) {
            System.out.println("We don't have such tickets");
            return;
        }
    }

    @Override
    public void generateTickets(int seanceId, int amountOfTickets) {

    }

    @Override
    public List<Ticket> changePriceForAllTickets(int seanceId, int newPrice) {
        return null;
    }

    @Override
    public Ticket changeTicketStatus(Integer seanceId, Integer ticketNumber, TicketStatus ticketStatus) {
        return null;
    }

    @Override
    public Optional<Ticket> findTicketByNumber(Integer seanceId, Integer number) {
        return Optional.empty();
    }
}
