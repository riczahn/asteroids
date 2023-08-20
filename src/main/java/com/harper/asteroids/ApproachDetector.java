package com.harper.asteroids;

import com.harper.asteroids.model.NearEarthObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Receives a set of neo ids and rates them after earth proximity.
 * Retrieves the approach data for them and sorts to the n closest.
 * https://api.nasa.gov/neo/rest/v1/neo/
 * Alerts if someone is possibly hazardous.
 */
public class ApproachDetector {
    private final List<String> nearEarthObjectIds;
    private final NeoRestApiClient neoRestApiClient;

    public ApproachDetector(List<String> ids) {
        this(ids, new NeoRestApiClient());
    }

    public ApproachDetector(List<String> nearEarthObjectIds, NeoRestApiClient neoRestApiClient) {
        this.nearEarthObjectIds = nearEarthObjectIds;
        this.neoRestApiClient = neoRestApiClient;
    }

    /**
     * Get the n closest approaches in this period
     *
     * @param limit - n
     */
    public List<NearEarthObject> getClosestApproaches(int limit) {
        var nThreads = nearEarthObjectIds.size();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        List<Future<NearEarthObject>> futures = new ArrayList<>(nThreads);
        for (int i = 0; i < nThreads; i++) {
            var neoId = nearEarthObjectIds.get(i);
            Callable<NearEarthObject> task = () -> getNeoWithId(neoId);
            futures.add(executorService.submit(task));
        }

        // Wait for all tasks to complete and store the neo
        List<NearEarthObject> neos = new ArrayList<>(limit);
        for (int i = 0; i < nThreads; i++) {
            try {
                var nearEarthObject = futures.get(i).get();
                neos.add(nearEarthObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        System.out.println("Received " + neos.size() + " neos, now sorting");

        return getClosest(neos, limit);
    }

    private NearEarthObject getNeoWithId(String id) {
        try {
            System.out.println("Check passing of object " + id);
            return neoRestApiClient.getNeoWithId(id);
        } catch (IOException e) {
            System.err.println("Failed scanning for asteroids: " + e);
            return null;
        }
    }

    /**
     * Get the closest passing.
     *
     * @param neos  the NearEarthObjects
     * @param limit
     * @return
     */
    public static List<NearEarthObject> getClosest(List<NearEarthObject> neos, int limit) {
        return neos.stream()
                .filter(neo -> neo.getCloseApproachData() != null && !neo.getCloseApproachData().isEmpty())
                .sorted(new VicinityComparator())
                .limit(limit)
                .collect(Collectors.toList());
    }

}
