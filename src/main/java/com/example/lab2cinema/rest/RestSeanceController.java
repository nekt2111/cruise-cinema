package com.example.lab2cinema.rest;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.repo.model.Filter;
import com.example.lab2cinema.repo.model.Page;
import com.example.lab2cinema.repo.model.SortWay;
import com.example.lab2cinema.services.SeanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/seance")
public class RestSeanceController {

    private SeanceService seanceService;

    public RestSeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Seance> allSeances() {
        return seanceService.getAllSeances();
    }

    @GetMapping("/sort={sortField}")
    @ResponseStatus(HttpStatus.OK)
    public List<Seance> allFilteredSeances(@PathVariable String sortField) {
        SortWay sortWay = SortWay.ASC;

        return seanceService.getAllSeances(Filter.builder()
                .fieldName(sortField)
                .sortWay(sortWay)
                .build());
    }

    @GetMapping("/filter={filterField}/{value}")
    @ResponseStatus(HttpStatus.OK)
    public List<Seance> allFilteredSeances(@PathVariable String filterField, @PathVariable String value) {
        SortWay sortWay = SortWay.ASC;

        return seanceService.getAllSeances(Filter.builder()
                .fieldName(filterField)
                .sortWay(sortWay)
                .value(value)
                .build());
    }

    @GetMapping("/page/{pageNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<Seance> allPaginatedSeances(@PathVariable String pageNumber) {
        System.out.println(pageNumber);
        return seanceService.getAllSeances(Page.createFromStrings(pageNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seance> seance(@PathVariable String id) {
        Seance seance = seanceService.findSeanceById(Integer.parseInt(id));
        if(seance != null) {
            return ResponseEntity.ok(seance);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Seance seance(@RequestBody Seance seance) {
        return seanceService.addSeance(seance);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Seance updateSeance(@RequestBody Seance seance){
       return seanceService.updateSeance(seance);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSeance(@PathVariable String id) {
        seanceService.deleteSeance(Integer.parseInt(id));
    }

}
