package com.example.lab2cinema.repo;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;

import java.util.List;

public interface SeanceRepo {
    List<Seance> findAll();
    Seance getSeanceById(int id);
    Seance addSeance(Seance seance);
    Seance updateSeance(Seance seance);
    void deleteSeance(int seanceId);
}
