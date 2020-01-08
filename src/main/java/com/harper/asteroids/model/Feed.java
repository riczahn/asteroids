package com.harper.asteroids.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Response for a feed query of Neos.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feed {
    @JsonProperty("element_count")
    private int elementCount;

    @JsonProperty("near_earth_objects")
    private Map<String, List<NearEarthObjectIds>> nearEarthObjects;

}
