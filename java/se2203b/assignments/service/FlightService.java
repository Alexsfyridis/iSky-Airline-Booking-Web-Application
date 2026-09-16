package se2203b.assignments.service;

import org.springframework.stereotype.Service;
import se2203b.assignments.domain.Flight;
import se2203b.assignments.repo.FlightRepository;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAllByOrderByLaunchDateAsc();
    }

    public List<Flight> searchByDestination(String destination) {
        if (destination == null || destination.isBlank()) {
            return getAllFlights();
        }
        return flightRepository.findByDestinationContainingIgnoreCaseOrderByLaunchDateAsc(destination.trim());
    }
}
