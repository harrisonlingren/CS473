package cs473;

import org.mongodb.morphia.annotations.Id;

/**
 * This class is an example of a mapping class for Morphia. Your design may or may not have an Airline collection.
 * This is intended only as an example and in no way implies that your application should have an Airline collection.
 */
public class Airline {
    @Id
    private String airlineCode;
    private String name;

    public Airline(String airlineCode, String name) {
        this.airlineCode = airlineCode;
        this.name        = name;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
