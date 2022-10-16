package com.example.reto3ciclo3.Repository;


import com.example.reto3ciclo3.Model.Costume;
import com.example.reto3ciclo3.Repository.CrudRepository.DisfracesCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class DisfracesRepository {

    @Autowired
    private DisfracesCrudRepository disfracesCrudRepository;

    public List<Costume> getAll() {
        return (List<Costume>) disfracesCrudRepository.findAll();
    }

    public Optional<Costume> getDisfraces(int id) {
        return disfracesCrudRepository.findById(id);
    }

    public Costume save(Costume c) {
        return disfracesCrudRepository.save(c);
    }

    public void delete(Costume c) {
        disfracesCrudRepository.delete(c);
    }
}
