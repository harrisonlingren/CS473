package cs473;

import org.mongodb.morphia.Datastore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// This is the class that you will need to modify in order to make our application work.
public class ProjectFunctions {
    private final Datastore datastore;

    private final Map<String, String> airlines = new HashMap<>();
    private final Map<String, Airport> airports = new HashMap<>();
    private final Map<String, Integer> planes = new HashMap<>();
    private final Map<String, Flight> flights = new HashMap<>();
    private final Map<Integer, String> travelers = new HashMap<>();
    private final Map<Integer, Reservation> reservations = new HashMap<>();

    public ProjectFunctions(Datastore datastore) {
        this.datastore = datastore;
    }

    public void addAirline(String airlineCode, String name) {
        System.out.println(String.format("Adding airline %s\t%s", airlineCode, name));
        airlines.put(airlineCode, name);
    }

    public void addAirport(String airportCode, String state, String city) {
        System.out.println(String.format("Adding airport %s\t%s\t%s", airportCode, state, city));

        Airport thisAirport = new Airport(airportCode, city, state);
        //airports.put(airportCode, thisAirport);

        datastore.save(thisAirport);
    }

    public void addPlane(String planeType, int seats) {
        System.out.println(String.format("Adding plane %s\t%d seats", planeType, seats));
        planes.put(planeType, seats);
    }

    public void addFlight(String airlineCode, String flightCode, int dayOfWeek, String origAirportCode, String destAirportCode, String planeType) {
        System.out.println(String.format("Adding flight %s\tfrom %s to %s on %d\tplane %s", flightCode, origAirportCode, destAirportCode, dayOfWeek, planeType));

        Flight thisFlight;
        int planeSeats = planes.get(planeType);
        String airlineName = airlines.get(airlineCode);

        if (flights.containsKey(flightCode)) {
            thisFlight = flights.get(flightCode);
        } else {
            thisFlight = new Flight();
            thisFlight.setFlightDetails(airlineCode, airlineName, flightCode, origAirportCode, destAirportCode, planeType, planeSeats);
        } thisFlight.addDay(dayOfWeek);

        flights.put(flightCode, thisFlight);

        datastore.save(thisFlight);
    }

    public void addTraveler(int travelerId, String name) {
        System.out.println(String.format("Adding traveller %d\t%s", travelerId, name));
        travelers.put(travelerId, name);
    }

    public void makeReservation(int reservationId, int travelerId, String flightCode, int dayOfWeek, Date date) {
        System.out.println(String.format("Making reservation %d for traveller %d on flight %s for the date %s", reservationId, travelerId, flightCode, date.toString()));

        String travelerName = travelers.get(travelerId);
        Reservation thisReserve = new Reservation(reservationId, travelerId, travelerName, flightCode, dayOfWeek, date);

        //reservations.put(reservationId, thisReserve);
        datastore.save(thisReserve);
    }
}
