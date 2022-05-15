package com.example.lab2cinema.services;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.repo.SeanceRepo;
import com.example.lab2cinema.repo.model.Filter;
import com.example.lab2cinema.repo.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeanceServiceImpl implements SeanceService {

    @Autowired
    private SeanceRepo seanceRepo;

    @Override
    public Seance findSeanceById(Integer id) {
        return seanceRepo.getSeanceById(id);
    }

    @Override
    public List<Seance> getAllSeances() {
        return seanceRepo.findAll();
    }

    @Override
    public List<Seance> getAllSeances(Page page) {
        return seanceRepo.findAll(page);
    }

    @Override
    public List<Seance> getAllSeances(Filter filter) {
        return seanceRepo.findAll(filter);
    }

    @Override
    public Seance addSeance(Seance seance){
        return seanceRepo.addSeance(seance);
    }

    @Override
    public Seance updateSeance(Seance seance) {
        return seanceRepo.updateSeance(seance);
    }

    @Override
    public void deleteSeance(int seanceId) {
         seanceRepo.deleteSeance(seanceId);
    }
}
