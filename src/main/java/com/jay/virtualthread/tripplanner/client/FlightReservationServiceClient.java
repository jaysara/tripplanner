package com.jay.virtualthread.tripplanner.client;

import com.jay.virtualthread.tripplanner.dto.FlightReservationRequest;
import com.jay.virtualthread.tripplanner.dto.FlightReservationResponse;
import org.springframework.web.client.RestClient;

public class FlightReservationServiceClient {

    private final RestClient client;

    public FlightReservationServiceClient(RestClient client) {
        this.client = client;
    }

    public FlightReservationResponse reserve(FlightReservationRequest request) {
        return this.client.post()
                          .body(request)
                          .retrieve()
                          .body(FlightReservationResponse.class);
    }

}
