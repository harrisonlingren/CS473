package cs473;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // Create the Morphia instance through which all access to Mongo is going to occur
        final Morphia morphia = new Morphia();

        // Tell Morphia where to find the mapping classes
        morphia.mapPackage("cs473");

        // Create the datastore, providing the actual connetion to the Mongo database instance. Every project team
        // should use a different dbName instead of travel473. This line of code will change slightly when we start
        // connecting to a replica set, but it will be the only code that will have to change. For now get your app
        // running connecting to a single instance of mongo.
        final Datastore datastore = morphia.createDatastore(new MongoClient(args[0], 27017), "kl-project473");

        // Create the ProjectFunctions class
        final ProjectFunctions projectFunctions = new ProjectFunctions(datastore);

        // Now load the file
        String fileName = args[1];

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> {
                String[] splits = line.split(",");
                switch(splits[0]) {
                    case "AIRLINE":
                        projectFunctions.addAirline(splits[1].trim(), splits[2].trim());
                        break;
                    case "AIRPORT":
                        projectFunctions.addAirport(splits[1].trim(), splits[2].trim(), splits[3].trim());
                        break;
                    case "PLANE":
                        projectFunctions.addPlane(splits[1].trim(), Integer.parseInt(splits[2].trim()));
                        break;
                    case "FLIGHT":
                        projectFunctions.addFlight(splits[1].trim(), splits[2].trim(), Integer.parseInt(splits[3].trim()), splits[4].trim(), splits[5].trim(), splits[6].trim());
                        break;
                    case "TRAVELER":
                        projectFunctions.addTraveler(Integer.parseInt(splits[1].trim()), splits[2].trim());
                        break;
                    case "RESERVATION":
                        projectFunctions.makeReservation(Integer.parseInt(splits[1].trim()), Integer.parseInt(splits[2].trim()), splits[3].trim(), Integer.parseInt(splits[4].trim()), Date.valueOf(splits[5].trim()));
                        break;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
