package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.VenueRepository;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueService {

        private final VenueRepository venueRepository;
        private final LambdaServiceClient lambdaServiceClient;

        @Autowired
        public VenueService(VenueRepository venueRepository, LambdaServiceClient lambdaServiceClient) {
            this.venueRepository = venueRepository;
            this.lambdaServiceClient = lambdaServiceClient;
        }

        public Venue createVenue(Venue venue) {
            return venueRepository.save(venue);
        }

        public Venue updateVenue(Venue venue) {
            if (venueRepository.existsById(venue.getId())) {
                return venueRepository.save(venue);
            } else {
                return null;
            }
        }

        public void deleteVenue(String id) {
            venueRepository.deleteById(id);
        }

        public String getVenueEvents(String id) {
            return (String) lambdaServiceClient.getVenueEvents(id);
        }

        public Venue getVenue(String id) {
            // implement the logic to get a venue by id and return it
            return venueRepository.findById(id).orElse(null);
        }

        public Venue findById(String id) {
            return venueRepository.findById(id).orElse(null);
        }

        public String getVenueAvailability(String id) {
            return null;
        }
    }