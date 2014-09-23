package com.mattring.smallevo.examples.travelingsalesman;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author mring
 */
public class DestinationFileToPointsFn implements Function<File, List<Point>> {

    @Override
    public List<Point> apply(File f) {
        final Path path = f.toPath();
        List<Point> points = Collections.emptyList();
        try (Stream<String> lines = Files.lines(path)) {
            points
                    = lines
                    .filter(line -> line.contains(","))
                    .map(line -> line.split(","))
                    .map(xy -> new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return points;
    }

}
