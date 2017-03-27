package cs473;

import org.mongodb.morphia.annotations.Id;

/**
 * This class is an example of a mapping class for Morphia. Your design may or may not have an Airport collection.
 * This is intended only as an example and in no way implies that your application should have an Airport collection.
 */
public class Airport {
    @Id
    private String code;
    private String state;
    private String city;

    public Airport(String code, String city, String state) {
        this.code = code;
        this.city = city;
        this.state = state;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }
}
