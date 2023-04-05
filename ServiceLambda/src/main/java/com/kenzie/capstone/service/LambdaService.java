package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.GuestDao;
import com.kenzie.capstone.service.model.Guest;
import com.kenzie.capstone.service.model.GuestData;
import com.kenzie.capstone.service.model.GuestRecord;
import com.kenzie.capstone.service.model.GuestRequest;

import javax.inject.Inject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LambdaService {

    private GuestDao guestDao;

    @Inject
    public LambdaService(GuestDao guestDao) {
        this.guestDao = guestDao;
    }

    public Guest getGuestData(String id) {
        return recordToGuest(guestDao.getGuestData(id));
    }

    public GuestData setGuestData(GuestRequest request) {
        GuestData data = requestToData(request);
        data.setId(UUID.randomUUID().toString());
        return guestDao.storeGuestData(data);
    }

    public boolean deleteGuestData(String id) {
        GuestRecord record = new GuestRecord();
        record.setId(id);
        return guestDao.deleteGuestData(record);
    }

    public List<Guest> getGuestsByEventId(String id) {
        return guestDao.getGuestsByEventId(id)
                .stream().map(this::recordToGuest)
                .collect(Collectors.toList());
    }

    private GuestData requestToData(GuestRequest request) {
        GuestData data = new GuestData();
        data.setName(request.getName());
        data.setEventId(request.getEventId());
        return data;
    }

    private Guest recordToGuest(GuestRecord record) {
        Guest guest = new Guest();
        guest.setId(record.getId());
        guest.setName(record.getName());
        guest.setEventId(record.getEventId());
        return guest;
    }
}
