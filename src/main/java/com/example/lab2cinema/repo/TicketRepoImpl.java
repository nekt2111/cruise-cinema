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
    private static final String UPDATE_PRICE = "UPDATE ticket SET price = ? WHERE seanceId = ?;";
    private static final String UPDATE_STATUS = "UPDATE ticket SET ticket_status = ? WHERE seanceId = ? AND number = ?;";

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
            },seanceRowMapper);

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
        try {
            jdbcTemplate.query((Connection c) -> {
                PreparedStatement preparedStatement = c.prepareStatement(UPDATE_PRICE);
                preparedStatement.setInt(1, newPrice);
                preparedStatement.setInt(2, seanceId);
                return preparedStatement;
            },seanceRowMapper);

            return findTicketsBySeanceId(seanceId);
        }
        catch (EmptyResultDataAccessException e) {
            System.out.println("Fuck you");
            return;
        }
    }

    @Override
    public Ticket changeTicketStatus(Integer seanceId, Integer ticketNumber, TicketStatus ticketStatus) {
        try {
            jdbcTemplate.query((Connection c) -> {
                PreparedStatement preparedStatement = c.prepareStatement(UPDATE_STATUS);
                preparedStatement.setInt(1, ticketStatus.ToInt());
                preparedStatement.setInt(2, seanceId);
                preparedStatement.setInt(3, ticketNumber);
                return preparedStatement;
            },seanceRowMapper);
        }
        catch (EmptyResultDataAccessException e) {
            System.out.println("Fuck you");
            return;
        }
    }

    @Override
    public Optional<Ticket> findTicketByNumber(Integer seanceId, Integer number) {
        return Optional.empty();
    }
}
