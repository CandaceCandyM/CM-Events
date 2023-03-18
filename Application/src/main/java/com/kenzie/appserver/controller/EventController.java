package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.EventCreateRequest;
import com.kenzie.appserver.controller.model.EventResponse;
import com.kenzie.appserver.service.EventService;
import com.kenzie.appserver.service.model.Event;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventResponse>> getEvents(){
        List<Event> events = eventService.getAllEvents();

        if(events == null || events.isEmpty()) return ResponseEntity.noContent().build();

        List<EventResponse> responses = events.stream().map(this::convertEvent).collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable("id") String eventId){
        Event event = eventService.getEventById(eventId);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(convertEvent(event));
    }

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventCreateRequest eventCreateRequest){
        Event event = new Event(eventCreateRequest.getEventName(), eventCreateRequest.getDescription(),
                eventCreateRequest.getAddress(), eventCreateRequest.getStartDate(), eventCreateRequest.getEndDate());

        eventService.createNewEvent(event);

        EventResponse eventResponse = convertEvent(event);

        return ResponseEntity.created(URI.create("/events/"+eventResponse.getId())).body(eventResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventResponse> deleteEvent(@PathVariable("id") String eventId){
        eventService.deleteEventById(eventId);
        return ResponseEntity.noContent().build();
    }

    private EventResponse convertEvent(Event event){
        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(event.getId());
        eventResponse.setUsername(event.getUsername());
        eventResponse.setEventName(event.getEventName());
        eventResponse.setDescription(event.getDescription());
        eventResponse.setStartDate(event.getStartDate());
        eventResponse.setEndDate(event.getEndDate());
        eventResponse.setStatus(event.getStatus());
        eventResponse.setAddress(event.getAddress());
        return eventResponse;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({EmptyResultDataAccessException.class, NoSuchElementException.class})
    public void notFound(){}
}