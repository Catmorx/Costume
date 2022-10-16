package com.example.reto3ciclo3.Repository.CrudRepository;


import com.example.reto3ciclo3.Model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
    @Query("SELECT c.client, COUNT (c.client)  FROM Reservation AS c group by c.client order by COUNT(c.client) DESC")
    public List<Object[]> countReservationModelByClient();

    //query methods,        primer para la fecha de inicio que se filtra y el segundo en el que termina agarrando los startDate
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date startDate, Date startFinish);

    public List<Reservation> findAllByStatus(String status);
}
