package com.harper.asteroids.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Definition for Neo - Near Earth Object
 *
 * TODO: why the h*** must I add this annotation to ignore unknown properties when I set it on ObjectMapper?
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NearEarthObject {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("nasa_jpl_url")
    private String nplUrl;

    @JsonProperty("absolute_magnitude_h")
    private double absoluteMagnitude;

    @JsonProperty("is_potentially_hazardous_asteroid")
    private boolean potentiallyHazardous;

    @JsonProperty("close_approach_data")
    private CloseApproachData[] closeApproachData;

    @JsonProperty("is_sentry_object")
    private boolean isSentryObject;

}
