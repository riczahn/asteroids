package com.harper.asteroids.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiameterEstimation {
    @JsonProperty("estimated_diameter_min")
    private Double min;

    @JsonProperty("estimated_diameter_max")
    private Double max;
}
