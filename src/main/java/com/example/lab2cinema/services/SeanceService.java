package com.example.lab2cinema.services;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.repo.model.Filter;
import com.example.lab2cinema.repo.model.Page;

import java.util.List;

public interface SeanceService {

    Seance findSeanceById(Integer id);
    List<Seance> getAllSeances();
    List<Seance> getAllSeances(Page page);
    List<Seance> getAllSeances(Filter filter);
    Seance addSeance(Seance seance);
    Seance updateSeance(Seance seance);
    void deleteSeance(int seanceId);

}
