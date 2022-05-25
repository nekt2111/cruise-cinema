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
    private static final String FILTER = "SELECT * FROM seance WHERE ? = ?;";
    private static final String SELECT_BY_PAGE = "SELECT * FROM seance LIMIT ? OFFSET ?";
    private static final String INSERT = "INSERT INTO seance(name, description,date,time) values (?,?,?,?);";
    private static final String DELETE = "DELETE FROM seance WHERE id = ?;";

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
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

            var result = jdbcTemplate.update((Connection c) -> {
                PreparedStatement preparedStatement = c.prepareStatement(SELECT_BY_ID,Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, id);
                return preparedStatement;
            }, generatedKeyHolder);

            return result;
        }
        catch (EmptyResultDataAccessException e) {
            System.out.println("We don't have this seance");
            return null;
        }
    }

    @Override
    public Seance addSeance(Seance seance) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection c) -> {
            PreparedStatement preparedStatement = c.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,seance.getName());
            preparedStatement.setString(2,seance.getDescription());
            preparedStatement.setDate(3, Date.valueOf(seance.getDate()));
            preparedStatement.setTime(4, Time.valueOf(seance.getTime()));
            return preparedStatement;
        }, generatedKeyHolder);

        return getSeanceById(generatedKeyHolder.getKey().intValue());
    }

    @Override
    public Seance updateSeance(Seance seance) {

        return null;
    }

    @Override
    public void deleteSeance(int seanceId) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection c) -> {
            PreparedStatement preparedStatement = c.prepareStatement(DELETE,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, seanceId);
            return preparedStatement;
        }, generatedKeyHolder);
    }

    @Override
    public List<Seance> findAll(Page page) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        
        var result = jdbcTemplate.update((Connection c) -> {
            PreparedStatement preparedStatement = c.prepareStatement(SELECT_BY_PAGE,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, page.getPageSize());
            preparedStatement.setString(2, page.getPageNumber() * page.getPageSize());
            return preparedStatement;
        }, generatedKeyHolder);

        return result;
    }

    @Override
    public List<Seance> findAll(Filter filter) {
        return null;
    }

    public List<Seance> findAllSmart(Filter filter) {
        return jdbcTemplate.query((Connection c) -> {
            PreparedStatement preparedStatement = c.prepareStatement(FILTER,Statement.RETURN_GENERATED_KEYS);

            String field = filter.getFieldName();

            preparedStatement.setString(1, field);

            String value = seance.getValue();

            if (field.Equals("name") || field.Equals("description")) {
                value = '\'' + value + '\'';
            }

            preparedStatement.setString(2,);
            return preparedStatement;
        }, seanceRowMapper);
    }
}
