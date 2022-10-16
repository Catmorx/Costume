package com.example.reto3ciclo3.services;

import com.example.reto3ciclo3.Model.Reservation;
import com.example.reto3ciclo3.Model.reports.ReportClient;
import com.example.reto3ciclo3.Model.reports.ReportStatus;
import com.example.reto3ciclo3.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class ReservationServices {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getProduct(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation p) {
        if (p.getIdReservation() == null) {
            return reservationRepository.save(p);
        } else {
            Optional<Reservation> e = reservationRepository.getReservation(p.getIdReservation());
            if (e.isPresent()) {
                return p;
            } else {
                return reservationRepository.save(p);
            }
        }
    }

    public Reservation update(Reservation reservationModel) {
        if (reservationModel.getIdReservation() != null) {
            Optional<Reservation> reservation = reservationRepository.getReservation(reservationModel.getIdReservation());
            if (!reservation.isEmpty()) {
                if (reservationModel.getStartDate() != null) {
                    reservation.get().setStartDate(reservationModel.getStartDate());
                }
                if (reservationModel.getDevolutionDate() != null) {
                    reservation.get().setDevolutionDate(reservationModel.getDevolutionDate());
                }
                if (reservationModel.getStatus() != null) {
                    reservation.get().setStatus(reservationModel.getStatus());
                }
                if (reservationModel.getCostume() != null) {
                    reservation.get().setCostume(reservationModel.getCostume());
                }
                if (reservationModel.getClient() != null) {
                    reservation.get().setClient(reservationModel.getClient());
                }
                if (reservationModel.getScore() != null) {
                    reservation.get().setScore(reservationModel.getScore());
                }
                reservationRepository.save(reservation.get());
                return reservation.get();
            } else {
                return reservationModel;
            }
        } else {
            return reservationModel;
        }

    }

    public boolean delete(int id) {
        boolean flag = false;
        Optional<Reservation> p = reservationRepository.getReservation(id);
        if (p.isPresent()) {
            reservationRepository.delete(p.get());
            flag = true;
        }
        return flag;

    }

    public List<ReportClient> getTopClient() {
        return reservationRepository.getTopClient();
    }

    public ReportStatus getReportStatus() {
        return reservationRepository.getReportStatus();
    }

    public List<Reservation> getReservationPeriod(String date1, String date2) {
        SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        Date finishDate = new Date();
        try {
            startDate = parseDate.parse(date1);
            finishDate = parseDate.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (startDate.before(finishDate)) {
            return reservationRepository.getReservationPeriod(startDate, finishDate);
        } else {
            return new ArrayList<>();
        }
    }
}
