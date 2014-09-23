package com.mattring.smallevo.examples.travelingsalesman;

import com.mattring.smallevo.examples.RepeatableShuffler;
import com.mattring.smallevo.Candidate;
import com.mattring.smallevo.EvoContext;
import com.mattring.smallevo.examples.BestScore;
import static com.mattring.smallevo.examples.travelingsalesman.DestinationFileCreatorMain.TSP_FILE;
import com.mattring.smallevo.functions.PrepareNextGenerationFn;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import javafx.application.Application;

/**
 * Attempts to evolve minimum distance solution to the Traveling Salesman Problem (TSP).
 * Reads the tsp_destinations.txt file.
 * The TSP, as I have defined it, is to find the shortest possible path that:
 * 1) begins at the salesperson's home base (defined as destination[0])
 * 2) visits every other destination once
 * 3) returns to home base
 * Provides an javafx-based visual playback of the evolution. 
 * Intended as a simple example of using SmallEvo.
 *
 * @author mring
 */
public class TravelingSalesmanMain {

    public static void main(String[] args) {
        final List<List<Point>> routesForPlayback = new ArrayList<>();
        final List<Point> destinationsReadFromFile = 
                Collections.unmodifiableList(
                        new DestinationFileToPointsFn().apply(
                                new File(TSP_FILE)));
        routesForPlayback.add(destinationsReadFromFile);
        final int genomeLen = destinationsReadFromFile.size() - 1;
        final int generationSize = 100;
        final int maxGenerationsToRun = 1000;
        final boolean higherScoreIsBetter = false; // this is a minimization problem
        final EvoContext<Candidate> ctx = new EvoContext<>(generationSize, genomeLen, higherScoreIsBetter);
        final Candidate initialBestCandidate = new Candidate();
        initialBestCandidate.setScore(Double.MAX_VALUE); // minimization problem, start with highest possible score
        final BestScore<Candidate> bestScoreHolder = new BestScore<>(ctx.getScoreComparator(), initialBestCandidate);
        final PrepareNextGenerationFn<Candidate> nextGenerationFn = new PrepareNextGenerationFn<>(ctx, Candidate.BLANK_SUPPLIER);
        final RepeatableShuffler<Point> shuffleFn = new RepeatableShuffler<>();
        List<Candidate> currentPopulation = nextGenerationFn.apply(null);
        int generationCount = 1;
        while (true) {
            currentPopulation.parallelStream().forEach(candidate -> {
                final double[] currentGenome = candidate.getGenome();
                final Point homeBase = destinationsReadFromFile.get(0);
                // rawDestinations = everything but home base
                final List<Point> rawDestinations = 
                        new ArrayList<>(destinationsReadFromFile.subList(1, destinationsReadFromFile.size()));
                // apply the repeatable shuffle, dirven by the genome
                final List<Point> arrangedDestinations = 
                        new ArrayList<>(shuffleFn.apply(rawDestinations, currentGenome));
                arrangedDestinations.add(0, homeBase); // start at home base
                arrangedDestinations.add(homeBase); // end at home base
                //
                // evaluate the resulting route
                //
                double score = 
                    IntStream.range(1, arrangedDestinations.size())
                            .mapToDouble(i -> {
                                Point a = arrangedDestinations.get(i-1);
                                Point b = arrangedDestinations.get(i);
                                double intervalScore = a.distance(b);
                                return intervalScore;
                            }).sum();
                candidate.setScore(score);
                if (bestScoreHolder.addIfBest(candidate)) {
                    System.out.println(score + ": " + arrangedDestinations);
                    routesForPlayback.add(arrangedDestinations);
                }
            });

            final Candidate bestCandThisGen = bestScoreHolder.getBest();
            // simple halting criteria using generation count
            if (++generationCount > maxGenerationsToRun) {
                break;
            }
            currentPopulation = nextGenerationFn.apply(currentPopulation);
            if (ThreadLocalRandom.current().nextBoolean()) {
                // 50% chance of adding best prev gen candidate to next gen
                currentPopulation.add(bestCandThisGen);
            }
        }

        VisualPlayback.routes = routesForPlayback;
        Application.launch(VisualPlayback.class, new String[0]);
    }
}
