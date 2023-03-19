package com.sexydari.betriush.mongodb;

import com.sexydari.betriush.exception.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface BettingCardService {

    //CREATE
    BettingCard createBettingCard(BettingCard bettingCard);

    //READ

    List<BettingCard> getBettingCards();

    BettingCard getBettingCardById(ObjectId id) throws ResourceNotFoundException;



    //UPDATE
    BettingCard updateBettingCard(@PathVariable(value = "id") ObjectId id, @Validated @RequestBody BettingCard bettingCardDetails)
            throws ResourceNotFoundException;


    //DELETE
    Map<String, Boolean> deleteBettingCard(@PathVariable(value = "id") ObjectId id)
        throws ResourceNotFoundException;


}
