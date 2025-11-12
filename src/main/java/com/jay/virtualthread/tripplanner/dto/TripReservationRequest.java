package com.jay.virtualthread.tripplanner.dto;

import java.time.LocalDate;

public record TripReservationRequest(String departure,
                                     String arrival,
                                     LocalDate date) {
}
