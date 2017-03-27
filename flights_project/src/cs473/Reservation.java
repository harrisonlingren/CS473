package cs473;

import org.mongodb.morphia.annotations.Id;

/**
 * Created by harrison on 3/26/17.
 *
 * Schema being used:
 * Reservation {
 *      id:             int,
 *      flight:         int,
 *      seat:           int,
 *      travelerId:     int,
 *      travelerName:   string
 * }
 *
 */
public class Reservation {
    @Id
    private String number;
}
