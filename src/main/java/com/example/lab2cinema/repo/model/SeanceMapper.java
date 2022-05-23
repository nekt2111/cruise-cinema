package com.example.lab2cinema.repo.model;

import com.example.lab2cinema.model.Seance;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class SeanceMapper implements RowMapper<Seance> {

    @Override
    public Seance mapRow(ResultSet rs, int rowNum) throws SQLException {
        Seance seance = new Seance();
        seance.setId(rs.getInt("id"));
        seance.setName(rs.getString("name"));
        seance.setDescription(rs.getString("description"));
        seance.setDate(rs.getDate("date").toLocalDate());
        seance.setTime(rs.getTime("time").toLocalTime());
        return seance;
    }
}
