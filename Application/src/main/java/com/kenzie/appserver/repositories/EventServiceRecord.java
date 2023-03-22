package com.kenzie.appserver.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.Objects;

@DynamoDBTable(tableName = "EventServiceRecord")
public class EventServiceRecord {

    private String id;
    private String name;

    public EventServiceRecord() {
        // empty constructor for the DynamoDB
    }

    public EventServiceRecord(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventServiceRecord that = (EventServiceRecord) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "EventServiceRecord{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
