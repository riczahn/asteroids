package com.harper.asteroids.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Velocities {
    @JsonProperty("kilometers_per_second")
    double kilometersPerSecond;

    @JsonProperty("kilometers_per_hour")
    double kilometersPerHour;

    @JsonProperty("miles_per_hour")
    double milesPerHour;

}
