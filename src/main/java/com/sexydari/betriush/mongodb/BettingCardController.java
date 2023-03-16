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


    //CRUD-Operations

    //CREATE
    @PostMapping("/betting-cards") //works
    public BettingCard createBettingCard(@RequestBody BettingCard bettingCard){
        bettingCard.setId(new ObjectId());
        return bettingCardRepo.save(bettingCard);
    }




    //READ
    // Just a tester Mapping to see if anything is returned
    @GetMapping("/what")
    public String return_bullshit(){
        return "Bullshit";
    }


    @GetMapping("/betting-cards") //Works :)
    public ResponseEntity <List<BettingCard>> getBettingCards(){

        return ResponseEntity.ok().body(bettingCardRepo.findAll());
    }

    @GetMapping("/betting-cards/{id}") // Works :)
    public ResponseEntity <BettingCard> getBettingCardById(@PathVariable(value="id") ObjectId id)
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
    @PutMapping("/betting-cards/{id}") //works
    public ResponseEntity <BettingCard> updateBettingCard(@PathVariable(value = "id") ObjectId id, @Validated @RequestBody BettingCard bettingCardDetails)
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
        return ResponseEntity.ok(updatedBettingCard);
    }

    //DELETE
    @DeleteMapping("/betting-cards/{id}") //works too
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") ObjectId id)
        throws ResourceNotFoundException{
        BettingCard bettingCard = bettingCardRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No card with this Id::" + id));
        bettingCardRepo.delete(bettingCard);
        Map <String, Boolean> response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
