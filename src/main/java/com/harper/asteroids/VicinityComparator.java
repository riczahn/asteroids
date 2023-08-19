package com.harper.asteroids;

import com.harper.asteroids.model.CloseApproachData;
import com.harper.asteroids.model.Distances;
import com.harper.asteroids.model.NearEarthObject;

import java.util.Comparator;
import java.util.Optional;

public class VicinityComparator implements Comparator<NearEarthObject> {

    public int compare(NearEarthObject neo1, NearEarthObject neo2) {
        Optional<Distances> neo1ClosestPass = neo1.getCloseApproachData().stream()
                .filter(this::isCloseApproachDateWithinThisWeek)
                .min(Comparator.comparing(CloseApproachData::getMissDistance))
                .map(CloseApproachData::getMissDistance);

        Optional<Distances> neo2ClosestPass = neo2.getCloseApproachData().stream()
                .filter(this::isCloseApproachDateWithinThisWeek)
                .min(Comparator.comparing(CloseApproachData::getMissDistance))
                .map(CloseApproachData::getMissDistance);

        if (neo1ClosestPass.isPresent()) {
            if (neo2ClosestPass.isPresent()) {
                return neo1ClosestPass.get().compareTo(neo2ClosestPass.get());
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }

    private boolean isCloseApproachDateWithinThisWeek(CloseApproachData closeApproachData) {
        var approachDate = DateUtils.convertMillisToLocalDate(closeApproachData.getCloseApproachEpochDate());
        return DateUtils.isDateWithinThisWeek(approachDate);
    }
}
