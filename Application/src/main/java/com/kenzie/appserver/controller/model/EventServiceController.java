package com.kenzie.appserver.controller.model;

import com.kenzie.appserver.service.Event;
import com.kenzie.appserver.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/event")
public class EventServiceController {

    private EventService eventService;

    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventServiceResponse> get(@PathVariable("id") String id) {

        Event event = eventService.findById(id);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        EventServiceResponse eventServiceResponse = new EventServiceResponse();
        eventServiceResponse.setId(event.getId());
        eventServiceResponse.setName(event.getName());
        return ResponseEntity.ok(eventServiceResponse);
    }

    @PostMapping
    public ResponseEntity<EventServiceResponse> addNewEvent(@RequestBody EventServiceCreateRequest eventServiceCreateRequest) {
        if (eventService == null) {
            // handle the error
        }
        Event event = eventService.addNewEvent(eventServiceCreateRequest.getName());

        EventServiceResponse eventServiceResponse = new EventServiceResponse();
        eventServiceResponse.setId(event.getId());
        eventServiceResponse.setName(event.getName());

        return ResponseEntity.ok(eventServiceResponse);
    }
}
