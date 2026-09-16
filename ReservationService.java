package se2203b.assignments.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se2203b.assignments.domain.Flight;
import se2203b.assignments.domain.Passenger;
import se2203b.assignments.domain.Reservation;
import se2203b.assignments.repo.ReservationRepository;

import java.time.LocalDate;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final PassengerService passengerService;

    public ReservationService(ReservationRepository reservationRepository, PassengerService passengerService) {
        this.reservationRepository = reservationRepository;
        this.passengerService = passengerService;
    }

    @Transactional
    public Reservation createReservation(String passengerName, String email, LocalDate travelDate, Flight flight) {
        Passenger passenger = passengerService.findOrCreatePassenger(passengerName, email, travelDate);

        Reservation reservation = new Reservation();
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);
        reservation.setLaunchDate(travelDate);

        return reservationRepository.save(reservation);
    }
}
