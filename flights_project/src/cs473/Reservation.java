package cs473;

import org.mongodb.morphia.annotations.Id;

import java.util.Date;

/**
 * Created by harrison on 3/26/17.
 *
 * Schema being used:
 * Reservation {
 *      id:             int,
 *      flight:         String,
 *      seat:           int,
 *      travelerId:     int,
 *      travelerName:   string
 * }
 *
 */
public class Reservation {
    @Id
    private int id;
    private String flight;
    private int DoW;
    private Date date;
    private int travelerId;
    private String travelerName;

    public Reservation(int id, int travelerId, String travelerName, String flight, int day, Date date) {
        this.id = id;
        this.travelerId = travelerId;
        this.travelerName = travelerName;
        this.flight = flight;
        this.DoW = day;
        this.date = date;
    }
}
