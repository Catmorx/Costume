package com.example.reto3ciclo3.services;

import com.example.reto3ciclo3.Model.Admin;
import com.example.reto3ciclo3.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class Adminservices {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll() {
        return (List<Admin>) adminRepository.getAll();
    }

    public Optional<Admin> getAdmin(int id) {
        return adminRepository.getAdmin(id);
    }

    public Admin save(Admin admin) {
        if (admin.getId() == null) {
            return adminRepository.save(admin);
        } else {
            Optional<Admin> adminEncontrado = adminRepository.getAdmin(admin.getId());
            if (!adminEncontrado.isPresent()) {
                return adminRepository.save(admin);

            } else {
                return admin;
            }
        }
    }

    public Admin update(Admin adminModel) {
        if (adminModel.getId() != null) {
            Optional<Admin> admin = adminRepository.getAdmin(adminModel.getId());
            if (!admin.isEmpty()) {
                if (adminModel.getName() != null) {
                    admin.get().setName(adminModel.getName());
                }
                if (adminModel.getEmail() != null) {
                    admin.get().setEmail(adminModel.getEmail());
                }
                if (adminModel.getPassword() != null) {
                    admin.get().setPassword(adminModel.getPassword());
                }
                adminRepository.save(admin.get());
                return admin.get();
            } else {
                return adminModel;
            }
        } else {
            return adminModel;
        }
    }

    public boolean deleteAdmin(int adminId) {
        boolean resultado = getAdmin(adminId).map(adminPorEliminar -> {
            adminRepository.delete(adminPorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }
}

