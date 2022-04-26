package com.example.lab2cinema.repo;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Repository
public class FakeSeanceRepo implements SeanceRepo{

    private static final Map<Integer,Seance> seances = new HashMap<>();
    private static int idSequence = 6;

    private TicketRepo ticketRepo;
    public FakeSeanceRepo(TicketRepo ticketRepo){
        this.ticketRepo = ticketRepo;
        this.generateSeances();
    }

    @Override
    public List<Seance> findAll() {
        return new ArrayList<>(seances.values());
    }

    @Override
    public Seance getSeanceById(int id) {
        return seances.get(id);
    }

    @Override
    public Seance addSeance(Seance seance) {
        seance.setId(++idSequence);
        seances.put(idSequence,seance);
        return seance;
    }

    @Override
    public Seance updateSeance(Seance seance) {
        seances.remove(seance.getId());
        seances.put(seance.getId(),seance);
        return seance;
    }

    @Override
    public void deleteSeance(int seanceId) {
        seances.remove(seanceId);
    }

    private void generateSeances(){
        List<Seance> seanceList = List.of(
                new Seance(1,"Batman","File about superhero",
                        LocalDate.of(2022,Calendar.APRIL,8), LocalTime.of(17,30)),
                new Seance(2,"Sonic the Hedgehog 2","At the end of the first movie, he really had come into his own in terms of his powers and into the role of a hero",
                        LocalDate.of(2022,Calendar.APRIL,14), LocalTime.of(17,30)),
                new Seance(3,"Ambulance","Let’s get one thing clear about Michael Bay’s new movie Ambulance. It’s not a medical drama.",
                        LocalDate.of(2022,Calendar.MAY,5), LocalTime.of(17,30)),
                new Seance(4,"Fantastic Beasts: The Secrets of Dumbledore","Given the Fantastic Beasts movies boast original screenplays – and Warner Bros always cast a Protego Maxima charm over the productions to erect an impenetrable defense shield – all we have to go on regards the plot of this newest chapter is the studio’s official release",
                        LocalDate.of(2022,Calendar.MAY,10), LocalTime.of(17,30)),
                new Seance(5,"The Northman","\"Simply put, it’s Viking Hamlet,\" writer-director Robert Eggers (The Witch, The Lighthouse) tells Total Film of The Northman, a raw-boned thriller that sees Alexander Skarsgård’s prince Amleth pursue vengeance for his murdered father. \"The trope of wanting to take time in your revenge to torment the person is honored here. But he’s not, like Shakespeare’s Hamlet, deliberating about whether or not violence is the answer. Not at all.\"",
                        LocalDate.of(2022,Calendar.MAY,13), LocalTime.of(17,30)),
                new Seance(6,"Doctor Strange and the Multiverse of Madness","Five months after his appearance in Spider-Man: No Way Home, Doctor Strange returns in the Multiverse of Madness. Having unleashed the multiverse, the former Sourceror Supreme (that's now Wong) must deal with the consequences of his actions.",
                        LocalDate.of(2022,Calendar.MAY,15), LocalTime.of(17,30))
        );

        for (Seance seance:seanceList) {
            seances.put(seance.getId(),seance);
            ticketRepo.generateTickets(seance.getId(), 40); // Dirty impl. Just to make fake data consistent
        }
    }
}
