package com.example.reto3ciclo3.Controller;


import com.example.reto3ciclo3.Model.Score;
import com.example.reto3ciclo3.services.Scoreservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Score")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ScoreController {

    @Autowired
    private Scoreservices scoreservices;

    @GetMapping("/all")
    public List<Score> getAll() {
        return scoreservices.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Score save(@RequestBody Score p) {
        return scoreservices.save(p);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteReserveQualification(@PathVariable Integer id) {
        scoreservices.delete(id);
        return true;
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Score updateReserveQualification(@RequestBody Score scoreModel) {
        return scoreservices.update(scoreModel);
    }
}
