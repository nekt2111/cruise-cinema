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

    private static final String SELECT_BY_ID = "SELECT * FROM seance where id = ";
    private static final String INSERT = "INSERT INTO seance(name, description,date,time) values (?,?,?,?)";

    private static final String UPDATE_BY_ID = "UPDATE seance SET name = ?, description = ?, date = ?, time = ? where id = ";

    private static final String SELECT_ALL = "SELECT * FROM seance ";

    public SeanceRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Seance> findAll() {
        return jdbcTemplate.query("SELECT * FROM seance", seanceRowMapper);
    }

    @Override
    public Seance getSeanceById(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID + id, seanceRowMapper);
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

    }

    @Override
    public List<Seance> findAll(Page page) {
        return null;
    }

    @Override
    public List<Seance> findAll(Filter filter) {
       // String filetSql = SELECT_ALL + " WHERE " + filter.getFieldName() + " = " + filter.getValue();
        return null;
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
