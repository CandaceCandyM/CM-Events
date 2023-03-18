package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.VenueCreateRequest;
import com.kenzie.appserver.controller.model.VenueResponse;
import com.kenzie.appserver.service.VenueService;
import com.kenzie.appserver.service.model.Venue;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/venues")
public class VenueController {

    private final VenueService venueService;

    VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> getVenues(@PathVariable("id") String id) {

        Venue venue = venueService.findById(id);
        if (venue == null) {
            return ResponseEntity.notFound().build();
        }

        VenueResponse venueResponse = convertVenue(venue);

        return ResponseEntity.ok(venueResponse);
    }

    @PostMapping
    public ResponseEntity<VenueResponse> addNewVenue(@RequestBody VenueCreateRequest venueCreateRequest) {
        Venue venue = venueService.addNewVenue(venueCreateRequest.getName(), venueCreateRequest.getEventCapacity());

        VenueResponse venueResponse = convertVenue(venue);

        return ResponseEntity.ok(venueResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VenueResponse> deleteVenue(@PathVariable("id") String venueId){
        venueService.deleteVenueById(venueId);
        return ResponseEntity.noContent().build();
    }

    private VenueResponse convertVenue(Venue venue) {
        VenueResponse venueResponse = new VenueResponse();
        venueResponse.setId(venue.getId());
        venueResponse.setName(venue.getName());
        venueResponse.setEventCapacity(venue.getEventCapacity());
        venueResponse.setStatus(venue.getStatus());
        return venueResponse;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({EmptyResultDataAccessException.class, NoSuchElementException.class})
    public void notFound(){}
}

