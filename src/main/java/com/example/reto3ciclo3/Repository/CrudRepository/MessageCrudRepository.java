package com.example.reto3ciclo3.Repository.CrudRepository;


import com.example.reto3ciclo3.Model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCrudRepository extends CrudRepository<Message, Integer> {
}
