package com.sexydari.betriush;

import java.util.List;

import com.sexydari.betriush.BettingCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BettingCardRepository extends MongoRepository<BettingCard, String> {
    @Query("{'id': '?0'}")
    List<BettingCard> findAll(int id);
}
