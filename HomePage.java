package se2203b.assignments.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import se2203b.assignments.service.FlightService;
import se2203b.assignments.service.ReservationService;

@Route("")
@RouteAlias("booking")
public class HomePage extends VerticalLayout {

    public HomePage(FlightService flightService, ReservationService reservationService) {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        add(buildHeader());
        add(new FlightView(flightService, reservationService));
    }

    private Component buildHeader() {
        H1 title = new H1("iSky Airline Portal");
        title.getStyle().set("margin", "0");
        title.addClassNames(LumoUtility.FontSize.XXXLARGE);

        RouterLink bookingLink = new RouterLink("Booking", HomePage.class);
        RouterLink flightStatusLink = new RouterLink("Query Flight Status", DummyView.class);
        RouterLink ticketStatusLink = new RouterLink("Query Ticket Status", DummyView.class);
        RouterLink confirmTicketLink = new RouterLink("Confirm Passenger Ticket", DummyView.class);

        HorizontalLayout nav = new HorizontalLayout(bookingLink, flightStatusLink, ticketStatusLink, confirmTicketLink);
        nav.setSpacing(true);

        HorizontalLayout topBar = new HorizontalLayout(title, new Span(), nav);
        topBar.expand(topBar.getComponentAt(1));
        topBar.setWidthFull();
        topBar.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.Background.CONTRAST_5);
        topBar.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        return topBar;
    }
}
