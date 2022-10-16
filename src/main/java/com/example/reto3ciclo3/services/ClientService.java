package com.example.reto3ciclo3.services;

import com.example.reto3ciclo3.Model.Client;
import com.example.reto3ciclo3.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getProduct(int id) {
        return clientRepository.getClient(id);
    }

    public Client save(Client p) {
        if (p.getIdClient() == null) {
            return clientRepository.save(p);
        } else {
            Optional<Client> e = clientRepository.getClient(p.getIdClient());
            if (e.isPresent()) {

                return p;
            } else {
                return clientRepository.save(p);
            }
        }
    }

    public Client update(Client clientModel) {
        if (clientModel.getIdClient() != null) {
            Optional<Client> client = clientRepository.getClient(clientModel.getIdClient());
            if (!client.isEmpty()) {
                if (clientModel.getEmail() != null) {
                    client.get().setEmail(clientModel.getEmail());
                }
                if (clientModel.getPassword() != null) {
                    client.get().setPassword(clientModel.getPassword());
                }
                if (clientModel.getName() != null) {
                    client.get().setName(clientModel.getName());
                }
                if (clientModel.getAge() != null) {
                    client.get().setAge(clientModel.getAge());
                }
                if (clientModel.getMessages() != null) {
                    client.get().setMessages(clientModel.getMessages());
                }
                if (clientModel.getReservations() != null) {
                    client.get().setReservations(clientModel.getReservations());
                }
                clientRepository.save(client.get());
                return client.get();
            } else {
                return clientModel;
            }
        } else {
            return clientModel;
        }
    }

    public boolean delete(int id) {
        boolean flag = false;
        Optional<Client> p = clientRepository.getClient(id);
        if (p.isPresent()) {
            clientRepository.delete(p.get());
            flag = true;
        }
        return flag;

    }
}
