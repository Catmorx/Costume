package com.example.reto3ciclo3.Controller;

import com.example.reto3ciclo3.Model.Costume;
import com.example.reto3ciclo3.services.Disfracesservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Costume")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class DisfracesController {
    @Autowired

    private Disfracesservices disfracesServices;

    @GetMapping("/all")
    public List<Costume> getAll() {
        return disfracesServices.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Costume save(@RequestBody Costume c) {
        return disfracesServices.save(c);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteBoat(@PathVariable Integer id) {
        disfracesServices.delete(id);
        return true;
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Costume updateBoat(@RequestBody Costume costumeModel) {
        return disfracesServices.update(costumeModel);
    }
}
