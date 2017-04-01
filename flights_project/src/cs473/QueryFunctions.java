package cs473;

import org.mongodb.morphia.Datastore;

import java.util.*;

public class QueryFunctions {
    private Datastore datastore;

    public QueryFunctions(Datastore datastore) {
        this.datastore = datastore;
    }

    /**
     * Returns all flights between the two airports on a given date. Note that I have simplified this from the
     * original requirements that specified a date range.  This function returns a generic Object so you can return
     * any object type that makes sense for your data model. The class your return should override the toString() method
     * and print something useful. Look at the sample Airline object for an example of this.
     */
    public List<Object> flightAvailability(String originationAirportCode, String destinationAirportCode, Date date) {
        return null;
    }

    /**
     * Returns all flights that are overbooked on the given day at the airport in question. If the boolean
     * checkOriginationCity is true, then you should check flights leaving the airport on the day. If the value is
     * false then you should check flight arriving at the airport that day. I have simplified this to a single date
     * instead of a date range.
     */
    public List<Object> flightOverbooked(boolean checkOriginationCity, String airportCode, Date date) {
        return null;
    }

    /**
     * Returns the airport code of the airport with the highest demand on the given date. Note that this clarifies
     * the original requirements and should return an individual airport and not a city, which may have multiple
     * airports. This also limits the require to only a date and not a day or week.
     *
     * Demand is to be calculated as the percentage of possible seats originating at the airport that are sold.
     */
    public String highestDemand(Date date) {
        return null;
    }

    /**
     * Same as above with two changes. First this provides a day of week instead of a single date. This means you have
     * to look at all flights on that day of week, not just on an individual date. This is thus covering many more
     * flights. This is also looking for the lowest demand, computed with the same definition as above.
     */
    public String lowestDemand(int dayOfWeek) {
        return null;
    }

    /**
     * Returns the airport code for the airport with the most available seats on a given date. Once again I have
     * simplified to a single date from a range and are requesting a single airport instead of a city. This may be a
     * different airport than the one with the lowest demand.
     */
    public String mostAvailableSeats(Date date) {
        return null;
    }

    /**
     * For this query you are going to return a map of maps. The outmost map will have as keys the airline codes. Each
     * entry in this map will map a day of week (integers 0 - 6) to the number of miles flown.
     */
    public Map<String, Map<Integer, Integer>> totalMilesAnalysis() {
        return null;
    }

    /**
     * This is a simplified version of the query in the original documentation. You simply need to give the
     * average miles flown by the 100 travellers with the most miles flown all time.
     */
    public int top100TravellerMiles() {
        return 0;
    }

    /**
     * This function will get the day of week for a date. It has to subtract one from the value returned from c.get(...)
     * because Java calendar uses 1 for Sunday, 2 for Monday and our data starts at 0 for Sunday.
     */
    private int dayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }


}
