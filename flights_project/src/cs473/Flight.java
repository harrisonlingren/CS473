package cs473;

import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by harrison on 3/26/17.
 *
 * Schema being used:
 * Flight {
 *     number:      int,
 *     airlineCode: string,
 *     airlineName: string,
 *     origin:      string,
 *     dest:        string,
 *     planeCode:   string,
 *     planeSeats:  int,
 *     distance:    int,
 *     departure:   time,
 *     arrival:     time,
 *     days: [
 *          <list days as strings>
 *     ]
 * }
 *
 */
public class Flight {
    @Id
    // flight details
    private String code;
    private String origin;
    private String dest;
    private ArrayList<Integer> days;

    // airline info
    private String airlineCode;
    private String airlineName;

    // plane info
    private String planeCode;
    private int planeSeats;

    public Flight() {
        this.code = "";
        this.airlineCode = "";
        this.airlineName = "";
        this.origin = "";
        this.dest = "";
        this.planeCode = "";
        this.planeSeats = 0;
        this.days = new ArrayList<>();
    }

    public void addDay(int day) {
        this.days.add(day);
    }

    public void setFlightDetails(String airlineCode, String airlineName, String code, String origin, String dest, String planeCode, int planeSeats) {
        this.code = code;
        this.origin = origin;
        this.dest = dest;
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.planeCode = planeCode;
        this.planeSeats = planeSeats;
    }
}
