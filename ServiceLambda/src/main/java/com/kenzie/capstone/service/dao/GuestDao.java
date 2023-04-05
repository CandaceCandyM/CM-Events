package com.kenzie.capstone.service.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kenzie.capstone.service.model.GuestData;
import com.kenzie.capstone.service.model.Guest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.google.common.collect.ImmutableMap;
import com.kenzie.capstone.service.model.GuestRecord;

import java.util.List;

public class GuestDao {
    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Match objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public GuestDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public GuestData storeGuestData(GuestData guestData) {
        try {
            mapper.save(guestData, new DynamoDBSaveExpression()
                    .withExpected(ImmutableMap.of(
                            "id",
                            new ExpectedAttributeValue().withExists(false)
                    )));
        } catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("id has already been used");
        }

        return guestData;
    }

    public GuestRecord getGuestData(String id) {
        GuestRecord record = new GuestRecord();
        record.setId(id);

        DynamoDBQueryExpression<GuestRecord> queryExpression = new DynamoDBQueryExpression<GuestRecord>()
                .withHashKeyValues(record)
                .withConsistentRead(false);

        return mapper.load(GuestRecord.class, queryExpression);
    }

    public List<GuestRecord> getGuestsByEventId(String id) {
        DynamoDBQueryExpression<GuestRecord> queryExpression = new DynamoDBQueryExpression<GuestRecord>()
                .withExpressionAttributeValues(ImmutableMap.of("eventId", new AttributeValue(id)))
                .withConsistentRead(false);

        return mapper.query(GuestRecord.class, queryExpression);
    }

    public boolean deleteGuestData(GuestRecord guestRecord) {
        try {
            mapper.delete(guestRecord, new DynamoDBDeleteExpression()
                    .withExpected(ImmutableMap.of("id",
                            new ExpectedAttributeValue().withValue(new AttributeValue(guestRecord.getId()))
                                    .withExists(true))));
        } catch (AmazonDynamoDBException e) {
            return false;
        }
        return true;
    }
}
