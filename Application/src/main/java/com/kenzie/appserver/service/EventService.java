package com.kenzie.appserver.service;
import com.kenzie.appserver.repositories.model.EventRepository;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
 @Autowired
    public EventService(LambdaServiceClient lambdaServiceClient, EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event getEventById(String id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEventById(String id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    public List<Event> getEventsToday() {
        LocalDate today = LocalDate.now();
        return eventRepository.findByDate(String.valueOf(today));
    }

    public List<Event> getEventsNextWeek() {
        LocalDate nextWeek = LocalDate.now().plusWeeks(1);
        return eventRepository.findByDateBetween(LocalDate.now(), nextWeek);
    }

    public List<Event> getEventsByDateRange(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findByDateBetween(startDate, endDate);
    }

    public List<Event> getEventsByVenueId(String venueId) {
        return eventRepository.findByVenueId(venueId);
    }

    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }

    public List<Event> getEventsByMinimumRating(int minRating) {
        return eventRepository.findByRatingGreaterThanEqual(minRating);
    }

    public List<Event> getEventsByPrice(double price) {
        return eventRepository.findByPrice(String.valueOf(price));
    }

    public List<Event> getEventsByLocation(String location) {
        return eventRepository.findByLocation(location);
    }

    public List<Event> getEventsByName(String name) {
        return eventRepository.findByName(name);
    }

    public List<Event> getEventsByDuration(String duration) {
        return eventRepository.findByDuration(duration);
    }

    public List<Event> getEventsByTime(String time) {
        return eventRepository.findByTime(time);

    }
    public List<Event> getAllEventsByLocation(String location) {
        return eventRepository.findByLocation(location);
    }

    public List<Event> getAllEventsByName(String name) {
        return eventRepository.findByName(name);
    }

    public List<Event> getAllEventsByRating(int rating, int i) {
        return eventRepository.findByRating(String.valueOf(rating));
    }

    public List<List<Event>> getAllEventsByPrice(double price) {
        return List.of(eventRepository.findByPrice(String.valueOf(price)));
    }

    public List<Event> getAllEventsByDate(LocalDate date) {
        return eventRepository.findByDate(String.valueOf(date));
    }

    public List<Event> getAllEventsByVenue(String venueId) {
        return eventRepository.findByVenueId(venueId);
    }

    public Event findById(String id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event addNewEvent(String name) {
        return addEvent(new Event(name));
    }

    public List<Event> getAllEventsByTime(String time) {
        return eventRepository.findByTime(time);
    }

    public List<Event> getAllEventsByDateRange(String s, String s1) {
        return eventRepository.findByDateBetween(LocalDate.parse(s), LocalDate.parse(s1));
    }

    public List<Event> getAllEventsByCategory(String category) {
        return eventRepository.findByCategory(category);
    }

    public List<Event> getAllEventsByPrice(String price) {
        return eventRepository.findByPrice(price);
    }

    public List<Event> getAllEventsByDuration(String duration) {
        return eventRepository.findByDuration(duration);
    }

    public List<Event> getAllEventsByRating(String rating) {
        return eventRepository.findByRating(rating);
    }
}