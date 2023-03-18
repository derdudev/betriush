package com.sexydari.betriush.mongodb;

import com.sexydari.betriush.exception.ResourceNotFoundException;
import com.sexydari.betriush.mongodb.models.BettingCard;
import com.sexydari.betriush.mongodb.repositories.BettingCardRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

//Annotation

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BettingCardController {
    /*@GetMapping(value="/betting-cards")
    //public String mainRoute(@CookieValue(name = "btusr", required = false) String userCookie){
        return "main";
    }*/
    @Autowired
    BettingCardRepository bettingCardRepo;


    //CRUD-Operations

    //CREATE

    //READ
    // Just a tester Mapping to see if anything is returned
    @GetMapping("/what")
    public String return_bullshit(){
        return "Bullshit";
    }


    @GetMapping("/cards") //Works :)
    public ResponseEntity<List<BettingCard>> getBettingCards(){
        System.out.println("test");
        System.out.println(bettingCardRepo.findAll());
        return ResponseEntity.ok().body(bettingCardRepo.findAll());
    }

    @GetMapping("/cards/{id}") // Works :)
    public ResponseEntity<BettingCard> getBettingCardById(@PathVariable(value="id") ObjectId id)
            throws ResourceNotFoundException {
        BettingCard bettingCard = bettingCardRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No Betting Card found for this id"));
        return ResponseEntity.ok().body(bettingCard);
    }

    @GetMapping("/getcards-t1") //Not tested
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


    //DELETE

}
