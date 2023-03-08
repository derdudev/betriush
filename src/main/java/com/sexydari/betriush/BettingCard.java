package com.sexydari.betriush;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("betting-cards")
public class BettingCard {
    @Id
    @JsonSerialize(using = ToStringSerializer.class) // see: https://github.com/MaBeuLux88/java-spring-boot-mongodb-starter/blob/master/src/main/java/com/mongodb/starter/models/Person.java#L16-L17
    private ObjectId id;

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId masterId;

    private String title;

    private String description;

    private List<BettingOption> bettingOptions;

    private boolean active;

    private String dueDate;

    private boolean repeating;

    private List<String> tags;


    public BettingCard(ObjectId id, ObjectId masterId, String title, String description, List<BettingOption> bettingOptions, boolean active,
                       String dueDate, boolean repeating, List<String> tags){
        this.id= id;
        this.masterId= masterId;
        this.title= title;
        this.description= description;
        this.bettingOptions = bettingOptions;
        this.active = active;
        this.dueDate = dueDate;
        this.repeating = repeating;
        this.tags = tags;

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getMasterId() {
        return masterId;
    }

    public void setMasterId(ObjectId masterId) {
        this.masterId = masterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BettingOption> getBettingOptions() {
        return bettingOptions;
    }

    public void setBettingOptions(List<BettingOption> bettingOptions) {
        this.bettingOptions = bettingOptions;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isRepeating() {
        return repeating;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}

