package se2203b.assignments.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se2203b.assignments.domain.Flight;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAllByOrderByLaunchDateAsc();

    List<Flight> findByDestinationContainingIgnoreCaseOrderByLaunchDateAsc(String destination);
}
