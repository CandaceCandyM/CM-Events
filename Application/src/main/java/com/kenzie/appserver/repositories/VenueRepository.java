package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.Venue;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface VenueRepository extends CrudRepository<Venue, String> {
    Venue findByLocation(String venueLocation);
    Venue findByPhone(String venuePhone);
    Venue findByWebsite(String venueWebsite);
    Venue findByEmail(String venueEmail);
    Venue findByDescription(String venueDescription);
}
