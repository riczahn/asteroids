package com.harper.asteroids.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Distances {
    @JsonProperty("astronomical")
    private double astronomical;

    @JsonProperty("lunar")
    private double lunar;

    @JsonProperty("kilometers")
    private double kilometers;

    @JsonProperty("miles")
    private double miles;
}
