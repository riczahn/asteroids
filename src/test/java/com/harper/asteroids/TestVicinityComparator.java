package com.harper.asteroids;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harper.asteroids.model.NearEarthObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class TestVicinityComparator {

    private final ObjectMapper mapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    private NearEarthObject neo1, neo2;

    @Before
    public void setUp() throws IOException {
        neo1 = mapper.readValue(getClass().getResource("/neo_example.json"), NearEarthObject.class);
        neo2 = mapper.readValue(getClass().getResource("/neo_example2.json"), NearEarthObject.class);
    }

    @Test
    @Ignore(value = "Deactivated as the implementation depends on the current date. Mocking will take significant amount of time.")
    public void testOrder() {
        VicinityComparator comparator = new VicinityComparator();

        assertThat(comparator.compare(neo1, neo2), greaterThan(0));
        assertThat(comparator.compare(neo2, neo1), lessThan(0));
        assertEquals(comparator.compare(neo1, neo1), 0);
    }
}
