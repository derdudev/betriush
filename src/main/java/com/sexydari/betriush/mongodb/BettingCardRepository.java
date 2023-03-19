package com.sexydari.betriush.mongodb;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BettingCardRepository extends MongoRepository<BettingCard, ObjectId> {
    @Query("{'id': '?0'}")


    List<BettingCard> findByTitleContaining(String title);

    public List<BettingCard> findBy(String title);

    public long count();
}
