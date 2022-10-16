package com.example.reto3ciclo3.Controller;

import com.example.reto3ciclo3.Model.Reservation;
import com.example.reto3ciclo3.Model.reports.ReportClient;
import com.example.reto3ciclo3.Model.reports.ReportStatus;
import com.example.reto3ciclo3.services.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {

    @Autowired
    private ReservationServices reservationServices;

    @GetMapping("/all")
    public List<Reservation> getAll() {
        return reservationServices.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation p) {
        return reservationServices.save(p);
    }

    @DeleteMapping("/{idReservation}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteReservation(@PathVariable Integer idReservation) {
        reservationServices.delete(idReservation);
        return true;
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation updateReservation(@RequestBody Reservation reservationModel) {
        return reservationServices.update(reservationModel);
    }

    @GetMapping("/report-dates/{startDate}/{finishDate}")
    public List<Reservation> getReservationPeriod(@PathVariable String startDate, @PathVariable String finishDate) {
        return reservationServices.getReservationPeriod(startDate, finishDate);
    }

    @GetMapping("/report-clients")
    public List<ReportClient> getTopClient() {
        return reservationServices.getTopClient();
    }

    @GetMapping("/report-status")
    public ReportStatus getReportStatus() {
        return reservationServices.getReportStatus();
    }
}
