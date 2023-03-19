package com.sexydari.betriush.mongodb;



import com.sexydari.betriush.exception.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sexydari.betriush.mongodb.BettingCardService;
import com.sexydari.betriush.mongodb.BettingCard;

//Annotation

//@CrossOrigin(origins= "http://localhost:8081"

@RestController
public class BettingCardController {
    /*@GetMapping(value="/betting-cards")
    //public String mainRoute(@CookieValue(name = "btusr", required = false) String userCookie){
        return "main";
    }*/
    @Autowired
    BettingCardRepository bettingCardRepo;

    @Autowired
    private BettingCardService bettingCardService;


    //CRUD-Operations

    //CREATE
    @PostMapping("/cards") //works
    public ResponseEntity<BettingCard>createBettingCard(@RequestBody BettingCard bettingCard){
        return ResponseEntity.ok().body(bettingCardService.createBettingCard(bettingCard));
    }


    //READ
    @GetMapping("/cards") //Works :)
    public ResponseEntity <List<BettingCard>> getBettingCards(){
        return ResponseEntity.ok().body(bettingCardService.getBettingCards());
    }

    @GetMapping("/cards/{id}") // Works :)
    public ResponseEntity<BettingCard> getBettingCardById(@PathVariable(value="id") ObjectId id)
            throws ResourceNotFoundException {
        BettingCard bettingCard = bettingCardRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No Betting Card found for this id"));
        return ResponseEntity.ok().body(bettingCardService.getBettingCardById(id));
    }

    @GetMapping("/cards-t1") //Not tested
    public ResponseEntity<List<BettingCard>> getAllBettingCards(@RequestParam(required = false) String title) {
        try {
            List<BettingCard> cards = new ArrayList<BettingCard>();

            if (title == null)
                bettingCardRepo.findAll().forEach(cards::add);
            else
                bettingCardRepo.findByTitleContaining(title).forEach(cards::add);

            if (cards.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    void getAllBettingCards(){
        System.out.println("Get all bettings cards");
        bettingCardRepo.findAll().forEach(card-> System.out.println(getCardDetails(card)));

    }
    public String getCardDetails(BettingCard card) {
        System.out.println(
                "Card-Id: " + card.getId() +
                        ", \nCard Title: " + card.getTitle()
        );

        return "";
    }



    //UPDATE
    @PutMapping("/cards/{id}") //works
    public ResponseEntity <BettingCard> updateBettingCard(@PathVariable(value = "id") ObjectId id, @Validated @RequestBody BettingCard bettingCardDetails)
        throws ResourceNotFoundException{
        return ResponseEntity.ok(bettingCardService.updateBettingCard(id, bettingCardDetails));
    }


    //DELETE
    @DeleteMapping("/cards/{id}") //works too
    public Map<String, Boolean> deleteBettingCard(@PathVariable(value = "id") ObjectId id)
        throws ResourceNotFoundException{
        return (bettingCardService.deleteBettingCard(id));

    }



}
