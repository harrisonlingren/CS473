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
    private int number;
    private String origin;
    private String dest;
    private int distance;

    // time info
    private Date departure;
    private Date arrival;
    private ArrayList days;

    // airline info
    private String airlineCode;
    private String airlineName;

    // plane info
    private String planeCode;
    private int planeSeats;

    public Flight() {
        this.number = 0;
        this.distance = 0;
        this.airlineCode = "";
        this.airlineName = "";
        this.origin = "";
        this.dest = "";
        this.planeCode = "";
        this.planeSeats = 0;
        this.departure = new Date();
        this.arrival = new Date();
        this.days = new ArrayList();
    }

}
