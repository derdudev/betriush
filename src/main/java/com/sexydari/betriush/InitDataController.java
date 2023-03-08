package com.sexydari.betriush;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InitDataController {
    private final UserRepository userRepository;

    public InitDataController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping(value = "/initDB")
    @ResponseBody
    public InitDataResponse initData(){
        int preLength = userRepository.findAll().size();
        userRepository.save(new User(new ObjectId("6405e1e6816213e3e62e8b51"), "long", "admin"));
        int postLength = userRepository.findAll().size();

        InitDataResponse res = new InitDataResponse(true, preLength != postLength);
        return res;
    }
}
