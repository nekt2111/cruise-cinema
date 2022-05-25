package com.example.lab2cinema.repo;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.repo.model.Filter;
import com.example.lab2cinema.repo.model.Page;
import com.example.lab2cinema.repo.model.SeanceMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@Primary
public class SeanceRepoImpl implements SeanceRepo {

    private JdbcTemplate jdbcTemplate;

    private SeanceMapper seanceRowMapper = new SeanceMapper();

    private static final String SELECT_BY_ID = "SELECT * FROM seance WHERE id = ?;";
    private static final String SELECT_BY_PAGE = "SELECT * FROM seance LIMIT ? OFFSET ?";
    private static final String INSERT = "INSERT INTO seance(name, description,date,time) values (?,?,?,?);";
    private static final String DELETE = "DELETE FROM seance WHERE id = ?;";

    private static final String UPDATE_BY_ID = "UPDATE seance SET name = ?, description = ?, date = ?, time = ? where id = ";

    private static final String SELECT_ALL = "SELECT * FROM seance ";

    public SeanceRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Seance> findAll() {
        return jdbcTemplate.query("SELECT * FROM seance;", seanceRowMapper);
    }

    @Override
    public Seance getSeanceById(int id) {
        try {

             return jdbcTemplate.query((Connection c) -> {
                PreparedStatement preparedStatement = c.prepareStatement(SELECT_BY_ID);
                preparedStatement.setInt(1, id);
                return preparedStatement;
            },seanceRowMapper).get(0);

        }
        catch (EmptyResultDataAccessException e) {
            System.out.println("We don't have this seance");
            return null;
        }
    }

    @Override
    public Seance addSeance(Seance seance) {
        return updateSeanceTable(seance,INSERT,true);
    }

    @Override
    public Seance updateSeance(Seance seance) {
        return updateSeanceTable(seance,UPDATE_BY_ID + seance.getId(),false);
    }

    @Override
    public void deleteSeance(int seanceId) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection c) -> {
            PreparedStatement preparedStatement = c.prepareStatement(DELETE,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, seanceId);
            return preparedStatement;
        }, generatedKeyHolder);
    }

    @Override
    public List<Seance> findAll(Page page) {

        return jdbcTemplate.query((Connection c) -> {
            PreparedStatement preparedStatement = c.prepareStatement(SELECT_BY_PAGE);
            preparedStatement.setInt(1, page.getPageSize());
            preparedStatement.setInt(2, (page.getPageNumber()) * page.getPageSize());
            return preparedStatement;
        },seanceRowMapper);
    }

    @Override
    public List<Seance> findAll(Filter filter) {
        return jdbcTemplate.query((Connection c) -> {
            PreparedStatement preparedStatement;

            switch (filter.getFieldName()) {
                case "name":
                    preparedStatement = c.prepareStatement(SELECT_ALL + " where name = ?");
                    preparedStatement.setNString(1,filter.getValue());
                    break;
                case "date":
                    preparedStatement = c.prepareStatement(SELECT_ALL + " where date = ?");
                    preparedStatement.setDate(1,Date.valueOf(filter.getValue()));
                    break;
                case "time":
                    preparedStatement = c.prepareStatement(SELECT_ALL + " where time = ?");
                    preparedStatement.setTime(1,Time.valueOf(filter.getValue()));
                    break;
                default:
                    preparedStatement = c.prepareStatement(SELECT_ALL);
            }
            return preparedStatement;
        },seanceRowMapper);
    }

    private Seance updateSeanceTable(Seance seance, String SQL, boolean isKeyGenerated){
         GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
         jdbcTemplate.update((Connection c) -> {
            PreparedStatement preparedStatement = c.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,seance.getName());
            preparedStatement.setString(2,seance.getDescription());
            preparedStatement.setDate(3, Date.valueOf(seance.getDate()));
            preparedStatement.setTime(4, Time.valueOf(seance.getTime()));
            return preparedStatement;
        },generatedKeyHolder);

        if (isKeyGenerated) {
            return getSeanceById(generatedKeyHolder.getKey().intValue());
        }
        return getSeanceById(seance.getId());

    }
}
