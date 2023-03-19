package com.sexydari.betriush.mongodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sexydari.betriush.exception.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class BettingCardServiceImpl implements BettingCardService{
    @Autowired
    private BettingCardRepository bettingCardRepo;

    //CRUD-OPERATIONS
    //CREATE
    @Override
    public BettingCard createBettingCard(BettingCard bettingCard){
        bettingCard.setId(new ObjectId()); // automatically creates hex-ID
        return bettingCardRepo.save(bettingCard);
    }

    //READ
    @Override
    public  List<BettingCard > getBettingCards(){
        return this.bettingCardRepo.findAll();
    }

    @Override
    public BettingCard getBettingCardById(@PathVariable(value="id") ObjectId id)
            throws ResourceNotFoundException {
        BettingCard bettingCard = bettingCardRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No Betting Card found for this id"));
        return bettingCard;
    }


    //UPDATE
    @Override
    public BettingCard updateBettingCard(ObjectId id,  BettingCard bettingCardDetails)
            throws ResourceNotFoundException{
        BettingCard bettingCard = bettingCardRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No card with this ID:: "+ id));
        //Set new values
        bettingCard.setTitle(bettingCardDetails.getTitle());
        bettingCard.setDescription(bettingCardDetails.getDescription());
        bettingCard.setBettingOptions(bettingCardDetails.getBettingOptions());
        bettingCard.setActive(bettingCardDetails.isActive());
        bettingCard.setDueDate(bettingCardDetails.getDueDate());
        bettingCard.setRepeating(bettingCardDetails.isRepeating());
        bettingCard.setTags(bettingCardDetails.getTags());

        final BettingCard updatedBettingCard = bettingCardRepo.save(bettingCard);
        return updatedBettingCard;
    }

    //DELETE
    public Map<String, Boolean> deleteBettingCard(@PathVariable(value = "id") ObjectId id)
            throws ResourceNotFoundException{

        BettingCard bettingCard = bettingCardRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No card with this Id::" + id));
        bettingCardRepo.delete(bettingCard);
        Map <String, Boolean> response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }






}
