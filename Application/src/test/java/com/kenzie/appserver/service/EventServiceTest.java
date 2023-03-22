package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.EventRepository;
import com.kenzie.appserver.repositories.model.EventRecord;
import com.kenzie.appserver.service.model.Event;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Event event1 = new Event("1", "name", "username", "description",
                "venueId", "startDate", "endDate", "category");
        Event event2 = new Event("2", "name", "username", "description",
                "venueId", "startDate", "endDate", "category");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByVenueId("venueId"))
                .thenReturn(events.stream().map(this::eventToRecord).collect(Collectors.toList()));

        // WHEN
        List<Event> result = eventService.getEventsByVenueId("venueId");

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
        Event event1 = new Event("1", "name", "username", "description",
                "venue", "2023-03-20", "2023-05-20", "category");
        Event event2 = new Event("2", "name", "username", "description",
                "venue", "2023-03-20", "2023-05-20", "category");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByStartDateBeforeAndEndDateAfter("2023-04-20", "2023-04-20"))
                .thenReturn(events.stream().map(this::eventToRecord).collect(Collectors.toList()));
        // WHEN
        List<Event> result = eventService.getEventsByDate("2023-04-20");
        // THEN
        verify(eventRepository).findByStartDateBeforeAndEndDateAfter("2023-04-20", "2023-04-20");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by category
     */
    @Test

    //Test 3
    public void getAllEventsByCategory() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "username", "description",
                "venue", "2023-03-20", "2023-05-20", "category");
        Event event2 = new Event("2", "name", "username", "description",
                "venue", "2023-03-20", "2023-05-20", "category");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByCategory("category"))
                .thenReturn(events.stream().map(this::eventToRecord).collect(Collectors.toList()));
        // WHEN
        List<Event> result = eventService.getEventsByCategory("category");
        // THEN
        verify(eventRepository).findByCategory("category");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by price
     */
    @Test

    //Test 4
    public void getAllEventsByDescription() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "username", "description",
                "venue", "2023-03-20", "2023-05-20", "category");
        Event event2 = new Event("2", "name", "username", "description",
                "venue", "2023-03-20", "2023-05-20", "category");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByDescriptionContaining("ript"))
                .thenReturn(events.stream().map(this::eventToRecord).collect(Collectors.toList()));
        // WHEN
        List<Event> result = eventService.getEventsByDescription("ript");
        // THEN
        verify(eventRepository).findByDescriptionContaining("ript");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by rating
     */
    @Test

    //Test 5
    public void getAllEventsByUsername() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "username", "description",
                "venue", "2023-03-20", "2023-05-20", "category");
        Event event2 = new Event("2", "name", "username", "description",
                "venue", "2023-03-20", "2023-05-20", "category");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByUsername("username"))
                .thenReturn(events.stream().map(this::eventToRecord).collect(Collectors.toList()));
        // WHEN
        List<Event> result = eventService.getEventsByUserame("username");
        // THEN
        verify(eventRepository).findByUsername("username");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    /**
     * Test to verify that the event service is able to get all events by name
     */
    @Test
    //Test 6
    public void getAllEventsByName() {
        // GIVEN
        List<Event> events = new ArrayList<>( );
        Event event1 = new Event("1", "name", "username", "description",
                "venue", "2023-03-20", "2023-05-20", "category");
        Event event2 = new Event("2", "name", "username", "description",
                "venue", "2023-03-20", "2023-05-20", "category");
        events.add(event1);
        events.add(event2);
        when(eventRepository.findByEventNameContaining("name"))
                .thenReturn(events.stream().map(this::eventToRecord).collect(Collectors.toList()));
        // WHEN
        List<Event> result = eventService.getEventsByName("name");
        // THEN
        verify(eventRepository).findByEventNameContaining("name");
        assertThat(result, containsInAnyOrder(event1, event2));
    }

    private EventRecord eventToRecord(Event event){
        EventRecord record = new EventRecord();
        record.setId(event.getId());
        record.setEventName(event.getEventName());
        record.setDescription(event.getDescription());
        record.setVenueId(event.getVenueId());
        record.setUsername(event.getUsername());
        record.setStartDate(event.getStartDate());
        record.setEndDate(event.getEndDate());
        record.setCategory(event.getCategory());
        return record;
    }
}







