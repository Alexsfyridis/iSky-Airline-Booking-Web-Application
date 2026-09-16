package se2203b.assignments.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se2203b.assignments.domain.Passenger;
import se2203b.assignments.repo.PassengerRepository;

import java.time.LocalDate;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Transactional
    public Passenger findOrCreatePassenger(String name, String email, LocalDate registerDate) {
        return passengerRepository.findByEmailIgnoreCase(email.trim())
                .map(existing -> {
                    existing.setName(name.trim());
                    return passengerRepository.save(existing);
                })
                .orElseGet(() -> {
                    Passenger passenger = new Passenger();
                    passenger.setName(name.trim());
                    passenger.setEmail(email.trim().toLowerCase());
                    passenger.setRegisterDate(registerDate);
                    return passengerRepository.save(passenger);
                });
    }
}
