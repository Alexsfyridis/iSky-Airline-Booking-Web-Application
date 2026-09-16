package se2203b.assignments.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se2203b.assignments.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
