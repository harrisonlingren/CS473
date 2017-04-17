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

    // flight dates
    private int sun;
    private int mon;
    private int tue;
    private int wed;

    public String getCode() {
        return code;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDest() {
        return dest;
    }

    public ArrayList<Integer> getDays() {
        return days;
    }

    public int getSun() {
        return sun;
    }

    public int getMon() {
        return mon;
    }

    public int getTue() {
        return tue;
    }

    public int getWed() {
        return wed;
    }

    public int getThu() {
        return thu;
    }

    public int getFri() {
        return fri;
    }

    public int getSat() {
        return sat;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public String getPlaneCode() {
        return planeCode;
    }

    public int getPlaneSeats() {
        return planeSeats;
    }

    private int thu;
    private int fri;
    private int sat;

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
        // this.days = new ArrayList<>();

        this.sun = 0;
        this.mon = 0;
        this.tue = 0;
        this.wed = 0;
        this.thu = 0;
        this.fri = 0;
        this.sat = 0;
    }

    public void addDay(int day) {
        switch (day) {
            case 0: this.sun = 1; break;
            case 1: this.mon = 1; break;
            case 2: this.tue = 1; break;
            case 3: this.wed = 1; break;
            case 4: this.thu = 1; break;
            case 5: this.fri = 1; break;
            case 6: this.sat = 1; break;
            default: break;
        }
    }

    /*public void addDay(int day) {
        this.days.add(day);
    }*/

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
