package se2203b.assignments.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se2203b.assignments.domain.Passenger;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByEmailIgnoreCase(String email);
}
