package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.Venue;
import com.kenzie.appserver.service.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venue")
public class VenueServiceController {
    private VenueService venueService;
    VenueServiceController(VenueService venueService) {
        this.venueService = venueService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> getId(@PathVariable("id") String id) {
        Venue venue = venueService.findById(id);
        if (venue == null) {
            return ResponseEntity.notFound().build();
        }
        VenueResponse venueResponse = new VenueResponse();
        venueResponse.setId(venue.getId());
        venueResponse.setName(venue.getName());
        return ResponseEntity.ok(venueResponse);
    }
    @PostMapping
    public ResponseEntity<VenueResponse> addNewVenue(@RequestBody VenueCreateRequest venueCreateRequest) {
        Venue venue = venueService.createVenue(venueCreateRequest.findVenue());
        VenueResponse venueResponse = new VenueResponse();
        venueResponse.setId(venue.getId());
        venueResponse.setName(venue.getName());
        return ResponseEntity.ok(venueResponse);
    }

    private class VenueResponse {
        public void setId(String id) {
        }

        public void setName(String name) {
        }
    }

    private class VenueCreateRequest {
        public Venue findVenue() {
            return null;
        }
    }
}
