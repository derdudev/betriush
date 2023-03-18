package com.sexydari.betriush.mongodb;

import com.sexydari.betriush.mongodb.models.BettingCard;
import com.sexydari.betriush.mongodb.models.BettingOption;
import com.sexydari.betriush.mongodb.models.User;
import com.sexydari.betriush.mongodb.models.UserRole;
import com.sexydari.betriush.mongodb.repositories.BettingCardRepository;
import com.sexydari.betriush.mongodb.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class InitDataController {
    private final UserRepository userRepository;
    private final BettingCardRepository bettingCardRepository;

    public InitDataController(UserRepository userRepository, BettingCardRepository bettingCardRepository) {
        this.userRepository = userRepository;
        this.bettingCardRepository = bettingCardRepository;
    }
    /*
    @PostMapping(value = "/initDB")
    public InitDataResponse initDB(){
        long preLengthUser = userRepository.count();
        userRepository.save(new User(new ObjectId("6405e1e6816213e3e62e8b51"), "long", List.of(new UserRole[]{UserRole.ROLE_ADMIN})));
        long postLengthUser = userRepository.count();
        long preLengthBettingCards = bettingCardRepository.count();
        bettingCardRepository.save(new BettingCard(
                new ObjectId("64089d41b1e095f1c47740a8"),
                new ObjectId("6405e1e6816213e3e62e8b51"),
                "Die Mittwochs Ana-TÜ wird ein Vollbruch",
                "",
                Arrays.stream(new BettingOption[]{new BettingOption("Ja"), new BettingOption("Nein")}).toList(),
                true,
                "",
                true,
                Arrays.stream(new String[]{}).toList()
        ));
        long postLengthBettingCards = bettingCardRepository.count();

        InitDataResponse res = new InitDataResponse(true, (preLengthUser != postLengthUser) || (preLengthBettingCards != postLengthBettingCards));
        return res;
    }
     */
}
