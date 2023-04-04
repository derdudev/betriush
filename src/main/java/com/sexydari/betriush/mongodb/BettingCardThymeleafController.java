package com.sexydari.betriush.mongodb;

import com.sexydari.betriush.exception.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BettingCardThymeleafController {

    private BettingCardService bettingService;
    private BettingCardRepository bettingCardRepo;
    @Autowired
    public BettingCardThymeleafController(BettingCardService bettingService) {
        this.bettingCardRepo = bettingCardRepo;
        this.bettingService = bettingService;
    }
    //Following Tut GFG
    @GetMapping("/cards-ui")
    public String cards(Model model) {
        model.addAttribute("cards", bettingService.getBettingCards());
        model.addAttribute("serverTime", 15);

        return "cards";
    }

    @GetMapping("/new-card")
    public String addCard(Model model) throws ResourceNotFoundException {
        ObjectId objId = new ObjectId("6405e1e6816213e3e62e8c50");
        model.addAttribute("card", bettingService.getBettingCardById(objId));
        return "new-card";

    }

    @PostMapping("/save")
    public String saveCard(@ModelAttribute("card") BettingCard bettingCard){
        try{
        bettingService.createBettingCard(bettingCard);
        } catch(Exception e){

        }
        return "redirect:/cards-ui";
    }


    //OLD
    @GetMapping(value={"/edit-card/{id}"})
    public String editCard(@PathVariable(value="id") ObjectId id, Model model)
        throws ResourceNotFoundException{
        BettingCard bettingCard = bettingCardRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No Betting Card found for this id"));
        BettingCard bettingCard1 = bettingService.getBettingCardById(id);
        model.addAttribute("card",bettingCard1);
        return "edit-card";
    }

    @PostMapping("/save-card")
    public String editCard(@ModelAttribute("card") @Validated BettingCard card, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "edit-card";
        }
        bettingService.createBettingCard(card);
        return "redirect:cards-ui";


    }

}





