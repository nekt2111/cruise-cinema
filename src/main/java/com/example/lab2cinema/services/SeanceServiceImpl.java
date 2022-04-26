package com.example.lab2cinema.services;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.repo.SeanceRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeanceServiceImpl implements SeanceService {

    private SeanceRepo seanceRepo;

    public SeanceServiceImpl(SeanceRepo seanceRepo){
        this.seanceRepo = seanceRepo;
    }


    @Override
    public Seance findSeanceById(Integer id) {
        return seanceRepo.getSeanceById(id);
    }

    @Override
    public List<Seance> getAllSeance() {
        return seanceRepo.findAll();
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
