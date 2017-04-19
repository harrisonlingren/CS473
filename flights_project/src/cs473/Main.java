package cs473;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.stream.Stream;

public class Main {


    private static void loadFile(ProjectFunctions pf, String fileName) {
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> {
                String[] splits = line.split(",");
                switch(splits[0]) {
                    case "AIRLINE":
                        pf.addAirline(splits[1].trim(), splits[2].trim());
                        break;
                    case "AIRPORT":
                        pf.addAirport(splits[1].trim(), splits[2].trim(), splits[3].trim());
                        break;
                    case "PLANE":
                        pf.addPlane(splits[1].trim(), Integer.parseInt(splits[2].trim()));
                        break;
                    case "FLIGHT":
                        pf.addFlight(splits[1].trim(), splits[2].trim(), Integer.parseInt(splits[3].trim()), splits[4].trim(), splits[5].trim(), splits[6].trim());
                        break;
                    case "TRAVELER":
                        pf.addTraveler(Integer.parseInt(splits[1].trim()), splits[2].trim());
                        break;
                    case "RESERVATION":
                        pf.makeReservation(Integer.parseInt(splits[1].trim()), Integer.parseInt(splits[2].trim()), splits[3].trim(), Integer.parseInt(splits[4].trim()), Date.valueOf(splits[5].trim()));
                        break;
                }
            });

            println("Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger( "org.mongodb" );
        mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.

        // Create the Morphia instance through which all access to Mongo is going to occur
        final Morphia morphia = new Morphia();

        // Tell Morphia where to find the mapping classes
        morphia.mapPackage("cs473");

        // Create the datastore, providing the actual connetion to the Mongo database instance. Every project team
        // should use a different dbName instead of travel473. This line of code will change slightly when we start
        // connecting to a replica set, but it will be   the only code that will have to change. For now get your app
        // running connecting to a single instance of mongo.
        String uriString    = args[0];
        String databaseName = args[1];
        String dataFile     = args[2];

        String actualURI = String.format(uriString);
        MongoClientURI uri = new MongoClientURI(actualURI);
        final Datastore datastore = morphia.createDatastore(new MongoClient(uri), databaseName);

        ProjectFunctions pf = new ProjectFunctions(datastore);

        // Create the ProjectFunctions class
        final ProjectFunctions projectFunctions = new ProjectFunctions(datastore);
        final QueryFunctions queryFunctions = new QueryFunctions(datastore);

        // Run this function to load data, comment it out if you are querying data
        //loadFile(pf, dataFile);


        String cmd; Scanner input = new Scanner(System.in);
        do {
            print("Select query to run (type 'q' to quit): ");
            cmd = input.next().toLowerCase();

            java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = Date.from(Instant.now());
            String dateString;
            switch (cmd) {
                // query 1
                case "1":
                    print("Enter origin airport: "); String o = input.next().toUpperCase();
                    print("Enter destination airport: "); String d = input.next().toUpperCase();
                    List<Flight> availableFlights = queryFunctions.flightAvailability(o, d, new java.util.Date());
                    println("Results:");
                    for(Flight x: availableFlights) {
                        print("Flight " + x.getCode() + ", ");
                    } println("");
                    break;

                // query 2
                case "2":
                    print("Origin or Destination: "); boolean checkOriginationCity = false;
                    if (input.next().toUpperCase().charAt(0) == 'O') checkOriginationCity = true;
                    print("Enter airport: "); String airport = input.next().toUpperCase();
                    print("Enter date to search (yyyy-mm-dd): ");  dateString = input.next();
                    try {
                        date = df.parse(dateString);
                    } catch (Exception e) {
                        System.out.println("Cannot parse date.");
                    }
                    Iterator<Flight> overbookedFlights = queryFunctions.flightOverbooked(checkOriginationCity, airport, date).iterator();
                    if (overbookedFlights.hasNext())
                        System.out.println("Overbooked flights:");
                    else
                        System.out.println("No overbooked flights found.");
                    while (overbookedFlights.hasNext())
                        System.out.println(overbookedFlights.next().getCode());
                    break;

                case "3":
                    print("Enter date to search (yyyy-mm-dd): "); dateString = input.next();
                    try {
                        date = df.parse(dateString);
                    } catch (Exception e) {
                        System.out.println("Cannot parse date.");
                    }
                    println("Airport with highest demand on " + date.toString() + ": " + queryFunctions.highestDemand(date));
                    break;

                case "4":
                    print("Enter day of week to search: "); dateString = input.next().toLowerCase();
                    int dow = getDay(dateString);
                    String lowestDemand = queryFunctions.lowestDemand(dow);
                    println("Airport with lowest demand on " + dateString + ": " + lowestDemand);
                    break;

                case "5":
                    print("Enter date to search (yyyy-mm-dd): "); dateString = input.next();
                    try {
                        date = df.parse(dateString);
                    } catch (Exception e) {
                        System.out.println("Cannot parse date.");
                    }
                    println("Airport with most availability on " + date.toString() + ": " + queryFunctions.mostAvailableSeats(date));
                    break;

                case "q": break;
                default:
                    println("Invalid command. Please enter 'q' if you want to quit.");
            }
        } while(!cmd.equals("q"));
    }

    private static int getDay(String d) {
        int a;
        d = d.toLowerCase();
        switch (d) {
            case "sat":case "saturday":
                a = 6; break;
            case "fri":case "friday":
                a = 5; break;
            case "thu":case "thursday":
                a = 4; break;
            case "wed":case "wednesday":
                a = 3; break;
            case "tue":case "tuesday":
                a = 2; break;
            case "mon":case "monday":
                a = 1; break;
            case "sun":case "sunday": default:
                a = 0; break;
        } return a;
    }

    private static void print(Object in) {
        System.out.print(in.toString());
    }

    private static void println(Object in) {
        System.out.println(in.toString());
    }
}
