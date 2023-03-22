package com.kenzie.appserver.repositories.model;

import com.kenzie.appserver.service.Event;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for managing Event objects.
 */
@EnableScan
public interface EventRepository extends CrudRepository<Event, String> {

    /**
     * Find events by date.
     * @param date the date to search for
     * @return a list of events matching the given date
     */
    List<Event> findByDate(String date);

    /**
     * Find events by date range.
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a list of events within the given date range
     */
    List<Event> findByDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Find events by venue ID.
     * @param venueId the ID of the venue
     * @return a list of events taking place at the given venue
     */
    List<Event> findByVenueId(String venueId);

    /**
     * Find events by category.
     * @param category the category of the event
     * @return a list of events belonging to the given category
     */
    List<Event> findByCategory(String category);

    /**
     * Find events by duration.
     * @param duration the duration of the event
     * @return a list of events with the given duration
     */
    List<Event> findByDuration(String duration);


/**
 * Find events by time.
 * @param time the time of the event
    * @return a list of events with the given time
    */
    List<Event> findByTime(String time);
    
    /**
     * Find events by name.
     * @param name the name of the event
     * @return a list of events with the given name
     */
    List<Event> findByName(String name);

    List<Event> findByRatingGreaterThanEqual(int minRating);

    List<Event> findByPrice(String valueOf);

    List<Event> findByLocation(String location);

    List<Event> findByRating(String valueOf);

    /**
     * @param i
     * @param i1
     */
    void findByRating(int i, int i1);
}