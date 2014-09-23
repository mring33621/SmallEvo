package com.mattring.smallevo.examples.travelingsalesman;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * Creates the tsp_destinations.txt for a 500 x 500 grid with 20 randomly chosen destinations.
 * args[0] may be used to override the default of 20 destinations.
 * 
 * @author mring
 */
public class DestinationFileCreatorMain {

    public static final String TSP_FILE = "tsp_destinations.txt";
    public static final double GRID_SIZE = 500.0d;
    public static final int DEFAULT_NUM_DESTINATIONS = 20;

    public static void main(String[] args) {
        int numDests = DEFAULT_NUM_DESTINATIONS;
        if (args.length > 0) {
            numDests = Integer.parseInt(args[0]);
        }
        final Charset charset = Charset.forName("US-ASCII");
        try (BufferedWriter writer = Files.newBufferedWriter(new File(TSP_FILE).toPath(),
                charset,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            for (int i = 0; i < numDests; i++) {
                int x = (int) (Math.random() * GRID_SIZE);
                int y = (int) (Math.random() * GRID_SIZE);
                String line = String.format("%s,%s\n", x, y);
                writer.write(line, 0, line.length());
            }
            writer.flush();
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

}
