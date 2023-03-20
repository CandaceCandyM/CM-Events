package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.VenueRepository;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class VenueServiceTest {
    private VenueService venueService;

    @Mock
    private VenueRepository venueRepository;

    @Mock
    private LambdaServiceClient lambdaServiceClient;

    private Venue venue;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        venueRepository = mock(VenueRepository.class);
        lambdaServiceClient = mock(LambdaServiceClient.class); // this adds the mock of the lambda service client
        venueService = new VenueService(venueRepository, lambdaServiceClient);
        venue = new Venue("1", "Test Venue");
    }

    // Test 1
    @Test
    public void getVenueAvailability() {
        // GIVEN
        when(venueRepository.findById(venue.getId())).thenReturn(Optional.of(venue));

        // WHEN
        String result = venueService.getVenueAvailability(venue.getId());

        // THEN
        assertEquals(venue.getId(), result);
    }

    // Test 2
    @Test
    public void CreateVenueBooking() {
        // GIVEN
        when(venueRepository.save(venue)).thenReturn(venue);

        // WHEN
        Venue result = venueService.createVenue(venue);

        // THEN
        assertEquals(venue, result);
    }

    // Test 3
    @Test
    public void UpdateVenueStatus() {
        // GIVEN
        when(venueRepository.existsById(venue.getId())).thenReturn(true);
        when(venueRepository.save(venue)).thenReturn(venue);

        // WHEN
        Venue result = venueService.updateVenue(venue);

        // THEN
        assertEquals(venue, result);
    }

    // Test 4
    @Test
    public void ReturnNullWhenUpdatingNonexistentVenue() {
        // GIVEN
        when(venueRepository.existsById(venue.getId())).thenReturn(false);

        // WHEN
        Venue result = venueService.updateVenue(venue);

        // THEN
        assertNull(result);
    }

    // Test 5
    @Test
    public void DeleteVenueBooking() {
        // GIVEN
        when(venueRepository.existsById(venue.getId())).thenReturn(true);

        // WHEN
        venueService.deleteVenue(venue.getId());

        // THEN
        verify(venueRepository, times(1)).deleteById(venue.getId());
    }

    // Test 6
    @Test
    public void ReturnVenueWhenGettingVenueById() {
        // GIVEN
        when(venueRepository.findById(venue.getId())).thenReturn(Optional.of(venue));

        // WHEN
        Venue result = venueService.getVenue(venue.getId());

        // THEN
        assertEquals(venue, result);
    }

    // Test 7
    @Test
    public void getVenueNotFound() {
        // GIVEN
        when(venueRepository.findById(anyString())).thenReturn(Optional.empty());

        // WHEN
        Venue result = venueService.getVenue("1");

        // THEN
        assertNull(result);
    }

    // Test 8
    @Test
    public void GetVenue() {
        // GIVEN
        when(venueRepository.findById(venue.getId())).thenReturn(Optional.of(venue));

        // WHEN
        Venue result = venueService.getVenue(venue.getId());

        // THEN
        assertEquals(venue, result);
    }
}