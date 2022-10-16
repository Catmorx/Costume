package com.example.reto3ciclo3.Controller;

import com.example.reto3ciclo3.Model.Message;
import com.example.reto3ciclo3.services.Messageservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Message")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MessageController {

    @Autowired
    private Messageservices messageservices;

    @GetMapping("/all")
    public List<Message> getAll() {
        return messageservices.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message save(@RequestBody Message p) {
        return messageservices.save(p);
    }

    @DeleteMapping("/{idMessage}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteMessage(@PathVariable Integer idMessage) {
        return messageservices.delete(idMessage);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Message updateMessage(@RequestBody Message messageModel) {
        return messageservices.update(messageModel);
    }
}
