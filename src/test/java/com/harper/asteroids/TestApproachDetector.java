package com.harper.asteroids;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harper.asteroids.model.CloseApproachData;
import com.harper.asteroids.model.NearEarthObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestApproachDetector {

    private final ObjectMapper mapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    private NearEarthObject neo1, neo2;
    private NeoRestApiClient neoRestApiClient;

    @Before
    public void setUp() throws IOException {
        neo1 = mapper.readValue(getClass().getResource("/neo_example.json"), NearEarthObject.class);
        neo2 = mapper.readValue(getClass().getResource("/neo_example2.json"), NearEarthObject.class);

        neoRestApiClient = mock(NeoRestApiClient.class);
    }

    @Test
    public void testFiltering() {
        List<NearEarthObject> neos = List.of(neo1, neo2);
        List<NearEarthObject> filtered = ApproachDetector.getClosest(neos, 1);
        //Neo2 has the closest passing at 5261628 kms away.
        // TODO: Neo2's closest passing is in 2028.
        // In Jan 202, neo1 is closer (5390966 km, vs neo2's at 7644137 km)
        assertEquals(1, filtered.size());
        assertEquals(neo2, filtered.get(0));

    }

    @Test
    public void givenOnlyOneNeo__whenGettingTheClosestNeo__thenThisNeoIsReturned() throws IOException {
        // given
        var neoId = "1";
        var approachDetector = new ApproachDetector(List.of(neoId), neoRestApiClient);

        var nearEarthObject = new NearEarthObject();
        nearEarthObject.setCloseApproachData(List.of(new CloseApproachData()));
        when(neoRestApiClient.getNeoWithId(neoId)).thenReturn(nearEarthObject);

        // when
        var closestApproaches = approachDetector.getClosestApproaches(1);

        // then
        assertThat(closestApproaches.size(), is(1));
        assertThat(closestApproaches, contains(nearEarthObject));
    }

    @Test
    public void givenOnlyOneNeoWithoutCloseApproachData__whenGettingTheClosestNeo__thenEmptyListIsReturned() throws IOException {
        // given
        var neoId = "1";
        var approachDetector = new ApproachDetector(List.of(neoId), neoRestApiClient);

        var nearEarthObjectWithoutCloseApproachData = new NearEarthObject();
        when(neoRestApiClient.getNeoWithId(neoId)).thenReturn(nearEarthObjectWithoutCloseApproachData);

        // when
        var closestApproaches = approachDetector.getClosestApproaches(1);

        // then
        assertThat(closestApproaches.isEmpty(), is(true));
    }
}
