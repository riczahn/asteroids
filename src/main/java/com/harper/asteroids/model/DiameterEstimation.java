package com.harper.asteroids.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiameterEstimation {
    @JsonProperty("estimated_diameter_min")
    private double min;

    @JsonProperty("estimated_diameter_max")
    private double max;
}
