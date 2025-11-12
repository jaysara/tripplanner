package com.jay.virtualthread.tripplanner.controllerr;

import com.jay.virtualthread.tripplanner.dto.FlightReservationResponse;
import com.jay.virtualthread.tripplanner.dto.TripPlan;
import com.jay.virtualthread.tripplanner.dto.TripReservationRequest;
import com.jay.virtualthread.tripplanner.service.TripPlanService;
import com.jay.virtualthread.tripplanner.service.TripReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trip")
public class TripController {

    private final TripPlanService planService;
    private final TripReservationService reservationService;

    private static final Logger logger = LoggerFactory.getLogger(TripController.class);
    public TripController(TripPlanService planService, TripReservationService reservationService) {
        this.planService = planService;
        this.reservationService = reservationService;
    }

    @GetMapping("{airportCode}")
    public TripPlan planTrip(@PathVariable String airportCode){
        logger.info("Trip Plann arirport code {}, is virtual {}",airportCode,Thread.currentThread().isVirtual());
        return this.planService.getTripPlan(airportCode);
    }

    @PostMapping("reserve")
    public FlightReservationResponse reserveFlight(@RequestBody TripReservationRequest request){
        return this.reservationService.reserve(request);
    }

}
