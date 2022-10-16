package com.example.reto3ciclo3.Repository;


import com.example.reto3ciclo3.Model.Client;
import com.example.reto3ciclo3.Model.Reservation;
import com.example.reto3ciclo3.Model.reports.ReportClient;
import com.example.reto3ciclo3.Model.reports.ReportStatus;
import com.example.reto3ciclo3.Repository.CrudRepository.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public void delete(Reservation reservation) {
        reservationCrudRepository.delete(reservation);
    }

    public List<Reservation> getReservationPeriod(Date startDate, Date finishDate) {
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(startDate, finishDate);
    }

    public List<ReportClient> getTopClient() {
        List<ReportClient> res = new ArrayList<>();
        List<Object[]> report = reservationCrudRepository.countReservationModelByClient();
        for (Object[] i : report) {
            res.add(new ReportClient((Long) i[1], (Client) i[0]));
        }
        return res;
    }

    public ReportStatus getReportStatus() {
        List<Reservation> completed = reservationCrudRepository.findAllByStatus("completed");
        List<Reservation> cancelled = reservationCrudRepository.findAllByStatus("cancelled");
//        ReportStatus num= new ReportStatus(completed.size(),cancelled.size());
        return new ReportStatus(completed.size(), cancelled.size());

    }
}
