package com.example.lab2cinema.rest;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.repo.model.Filter;
import com.example.lab2cinema.repo.model.Page;
import com.example.lab2cinema.repo.model.SortWay;
import com.example.lab2cinema.services.SeanceService;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
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

    @GetMapping("/filter={filterField}")
    @ResponseStatus(HttpStatus.OK)
    public List<Seance> allFilteredSeances(@PathVariable String filterField) {
        SortWay sortWay = SortWay.ASC;

        return seanceService.getAllSeances(Filter.builder()
                .fieldName(filterField)
                .sortWay(sortWay)
                .build());
    }

    @GetMapping("/filter={filterField}/value={value}")
    @ResponseStatus(HttpStatus.OK)
    public List<Seance> allFilteredSeances(@PathVariable String filterField, @PathVariable String value) {
        SortWay sortWay = SortWay.ASC;

        return seanceService.getAllSeances(Filter.builder()
                .fieldName(filterField)
                .sortWay(sortWay)
                .value(value)
                .build());
    }

    @GetMapping("/page={pageNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<Seance> allPaginatedSeances(@PathVariable String pageNumber) {
        System.out.println(pageNumber);
        return seanceService.getAllSeances(Page.createFromStrings(pageNumber));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Seance seance(@PathVariable String id) {
        return seanceService.findSeanceById(Integer.parseInt(id));
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
