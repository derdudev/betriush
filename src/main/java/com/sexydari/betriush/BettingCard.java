package com.sexydari.betriush;

import java.util.ArrayList;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("bettingcards")
public class BettingCard {
    @Id
    private int id;

    private int masterId;

    private String title;

    private String description;

    private ArrayList BettingOptions;

    private boolean active;

    private String time;

    private boolean repeating;

    private ArrayList Tags;


    public BettingCard(int id, int masterId, String title, String description, ArrayList BettingOptions, boolean active,
                       String time, boolean repeating, ArrayList Tags){
        this.id= id;
        this.masterId= masterId;
        this.title= title;
        this.description= description;
        this.BettingOptions= BettingOptions;
        this.active = active;
        this.time= time;
        this.repeating = repeating;
        this.Tags= Tags;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
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

    public ArrayList getBettingOptions() {
        return BettingOptions;
    }

    public void setBettingOptions(ArrayList bettingOptions) {
        BettingOptions = bettingOptions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRepeating() {
        return repeating;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }
}

