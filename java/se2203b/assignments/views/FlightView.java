package se2203b.assignments.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.theme.lumo.LumoUtility;
import se2203b.assignments.domain.Flight;
import se2203b.assignments.service.FlightService;
import se2203b.assignments.service.ReservationService;

import java.time.LocalDate;

public class FlightView extends VerticalLayout {

    private final FlightService flightService;
    private final ReservationService reservationService;
    private final Grid<Flight> grid = new Grid<>(Flight.class, false);
    private final TextField destinationFilter = new TextField("Destination");

    public FlightView(FlightService flightService, ReservationService reservationService) {
        this.flightService = flightService;
        this.reservationService = reservationService;

        setSpacing(true);
        setPadding(false);
        setSizeFull();
        addClassName(LumoUtility.Padding.MEDIUM);

        H2 title = new H2("Flights Management");
        title.getStyle().set("margin-top", "0");

        destinationFilter.setPlaceholder("Search");
        destinationFilter.setClearButtonVisible(true);
        destinationFilter.setValueChangeMode(ValueChangeMode.EAGER);
        destinationFilter.addValueChangeListener(event -> refreshGrid());
        destinationFilter.setWidth("190px");

        configureGrid();
        refreshGrid();

        add(title, destinationFilter, grid);
        expand(grid);
    }

    private void configureGrid() {
        grid.addColumn(Flight::getFlightNo).setHeader("Flight No").setAutoWidth(true);
        grid.addColumn(Flight::getOrigin).setHeader("Origin").setAutoWidth(true);
        grid.addColumn(Flight::getDestination).setHeader("Destination").setAutoWidth(true);
        grid.addColumn(Flight::getDeparture).setHeader("Departure").setAutoWidth(true);
        grid.addColumn(Flight::getArrival).setHeader("Arrival").setAutoWidth(true);
        grid.addColumn(flight -> flight.getLaunchDate() == null ? "" : flight.getLaunchDate().toString())
                .setHeader("Launch Date")
                .setAutoWidth(true)
                .setFlexGrow(1);
        grid.addComponentColumn(this::buildBookButton).setHeader("").setAutoWidth(true);

        grid.setWidthFull();
        grid.setMinHeight("420px");
    }

    private Button buildBookButton(Flight flight) {
        Button button = new Button("Book this flight");
        button.addClickListener(event -> openReservationDialog(flight));
        return button;
    }

    private void refreshGrid() {
        grid.setItems(flightService.searchByDestination(destinationFilter.getValue()));
    }

    private void openReservationDialog(Flight flight) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Create Reservation");

        TextField passengerName = new TextField("Passenger Name");
        passengerName.setWidthFull();

        EmailField email = new EmailField("Email address");
        email.setWidthFull();

        DatePicker travelDate = new DatePicker("Travel Date");
        travelDate.setWidthFull();
        travelDate.setValue(LocalDate.now());

        Button save = new Button("Save", event -> {
            String passengerValue = passengerName.getValue();
            String emailValue = email.getValue();

            if (passengerValue == null || passengerValue.isBlank()
                    || emailValue == null || emailValue.isBlank()
                    || travelDate.isEmpty()) {
                Notification notification = Notification.show("Please complete all fields.");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            reservationService.createReservation(
                    passengerValue.trim(),
                    emailValue.trim(),
                    travelDate.getValue(),
                    flight
            );

            Notification notification = Notification.show("Reservation saved successfully.");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            dialog.close();
        });

        Button cancel = new Button("Cancel", event -> dialog.close());
        HorizontalLayout actions = new HorizontalLayout(save, cancel);

        VerticalLayout content = new VerticalLayout(passengerName, email, travelDate, actions);
        content.setPadding(false);
        content.setSpacing(true);

        dialog.add(content);
        dialog.open();
    }
}
