package se2203b.assignments.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_NO")
    private Long ticketNo;

    @Column(name = "LAUNCH_DATE", nullable = false)
    private LocalDate launchDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "FLIGHT_ID", nullable = false)
    private Flight flight;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PASSENGER_ID", nullable = false)
    private Passenger passenger;

    public Reservation() {
    }

    public Long getTicketNo() {
        return ticketNo;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
