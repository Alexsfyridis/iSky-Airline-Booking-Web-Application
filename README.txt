Files included in this web project:
- domain: Flight, Passenger, Reservation
- repo: FlightRepository, PassengerRepository, ReservationRepository
- service: FlightService, PassengerService, ReservationService
- views: HomePage, FlightView, DummyView
- iSkyApplication main class
- application.properties

Before running:
1. Make sure your JavaFX iSky app has already created and filled the same H2 database file.
2. Update spring.datasource.url so it points to the exact same physical database file.
3. Run with Java 17.
4. If IntelliJ shows Maven issues, reload Maven after opening the project.
