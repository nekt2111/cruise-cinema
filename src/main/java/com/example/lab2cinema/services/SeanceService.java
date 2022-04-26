package com.example.lab2cinema.services;

import com.example.lab2cinema.model.Seance;

import java.util.List;

public interface SeanceService {

    Seance findSeanceById(Integer id);
    List<Seance> getAllSeance();
    Seance addSeance(Seance seance);
    Seance updateSeance(Seance seance);
    void deleteSeance(int seanceId);

}
