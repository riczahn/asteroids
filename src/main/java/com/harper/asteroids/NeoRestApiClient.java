package com.harper.asteroids;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harper.asteroids.model.NearEarthObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class NeoRestApiClient {

    private static final String NEO_URL = "https://api.nasa.gov/neo/rest/v1/neo/";

    private final Client client;

    public NeoRestApiClient() {
        this(ClientBuilder.newClient());
    }

    public NeoRestApiClient(Client client) {
        this.client = client;
    }

    public NearEarthObject getNeoWithId(String asteroidId) throws IOException {
        Response response = client
                .target(NEO_URL + asteroidId)
                .queryParam("api_key", App.API_KEY)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 429) {
            System.out.println("Got 429 for Neo with ID " + asteroidId);
        }

        var mapper = getObjectMapper();
        return mapper.readValue(response.readEntity(String.class), NearEarthObject.class);
    }

    private static ObjectMapper getObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
}
