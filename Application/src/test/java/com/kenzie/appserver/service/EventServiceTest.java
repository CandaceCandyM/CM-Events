package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.EventRepository;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventServiceTest {
    private EventService eventService;
    @Mock
    private LambdaServiceClient lambdaServiceClient;
    @Mock
    private EventRepository eventRepository;

    private Optional<Event> Event;
    private Event actualEvent;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        eventService = new EventService(lambdaServiceClient, eventRepository);
    }

    /**
     * Test to verify that the event service is able to get all events by venue
     */
    @Test

    //Test 1
    public void getAllEventsByVenue() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "location", "date",
                "time", "duration", "price", "category", "rating");
        Event event2 = new Event("2", "name", "location", "date",
                "time", "duration", "price", "category", "rating");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByVenueId("venueId")).thenReturn(events);

        // WHEN
        List<Event> result = eventService.getAllEventsByVenue("venueId");

        // THEN
        verify(eventRepository).findByVenueId("venueId");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by date
     */
    @Test

    //Test 2
    public void getAllEventsByDate() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        Event event2 = new Event("2", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByDate("2023-03-20")).thenReturn(events);
        // WHEN
        List<Event> result = eventService.getAllEventsByDate(LocalDate.parse("2023-03-20"));
        // THEN
        verify(eventRepository).findByDate("2023-03-20");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by date range
     */
    @Test

    //Test 3
    public void getAllEventsByDateRange() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        Event event2 = new Event("2", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByDateBetween(LocalDate.parse("2023-03-20"), LocalDate.parse("2023-03-20"))).thenReturn(events);
        // WHEN
        List<Event> result = eventService.getAllEventsByDateRange("2023-03-20", "2023-03-20");
        // THEN
        verify(eventRepository).findByDateBetween(LocalDate.parse("2023-03-20"), LocalDate.parse("2023-03-20"));
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by category
     */
    @Test

    //Test 4
    public void getAllEventsByCategory() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        Event event2 = new Event("2", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByCategory("category")).thenReturn(events);
        // WHEN
        List<Event> result = eventService.getAllEventsByCategory("category");
        // THEN
        verify(eventRepository).findByCategory("category");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by price
     */
    @Test

    //Test 5
    public void getAllEventsByPrice() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        Event event2 = new Event("2", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByPrice("price")).thenReturn(events);
        // WHEN
        List<Event> result = eventService.getAllEventsByPrice("price");
        // THEN
        verify(eventRepository).findByPrice("price");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by rating
     */
    @Test

    //Test 6
    public void getAllEventsByRating() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        Event event2 = new Event("2", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByRating("rating")).thenReturn(events);
        // WHEN
        List<Event> result = eventService.getAllEventsByRating("rating");
        // THEN
        verify(eventRepository).findByRating("rating");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by name
     */
    @Test

    //Test 7
    public void getAllEventsByName() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        Event event2 = new Event("2", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByName("name")).thenReturn(events);
        // WHEN
        List<Event> result = eventService.getAllEventsByName("name");
        // THEN
        verify(eventRepository).findByName("name");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by location
     */
    @Test

    //Test 8
    public void getAllEventsByLocation() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        Event event2 = new Event("2", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByLocation("location")).thenReturn(events);
        // WHEN
        List<Event> result = eventService.getAllEventsByLocation("location");
        // THEN
        verify(eventRepository).findByLocation("location");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by time
     */
    @Test

    //Test 9
    public void getAllEventsByTime() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        Event event2 = new Event("2", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByTime("time")).thenReturn(events);
        // WHEN
        List<Event> result = eventService.getAllEventsByTime("time");
        // THEN
        verify(eventRepository).findByTime("time");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by duration
     */
    @Test

    //Test 10
    public void getAllEventsByDuration() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        Event event2 = new Event("2", "name", "location", "2023-03-20",
                "time", "duration", "price", "category", "rating");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByDuration("duration")).thenReturn(events);
        // WHEN
        List<Event> result = eventService.getAllEventsByDuration("duration");
        // THEN
        verify(eventRepository).findByDuration("duration");
        assertThat(result, containsInAnyOrder(event1, event2));
    }
}







