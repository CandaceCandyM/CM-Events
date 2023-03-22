package com.kenzie.appserver.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "VenueService")
public class VenueRecord {

    private String id;
    private String name;
    private String venue;

    // Make an empty constructor
    public VenueRecord() {}

    // Have the constructor show all fields except the 'id' parameter
    public VenueRecord(String name, String venue) {
        this.name = name;
        this.venue = venue;
    }

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "venue")
    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VenueRecord that = (VenueRecord) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(venue, that.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, venue);
    }
}