package com.sexydari.betriush.mongodb.repositories;

import com.sexydari.betriush.mongodb.models.UserRole;
import com.sexydari.betriush.mongodb.models.UserRoleModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRoleRepository extends MongoRepository<UserRoleModel, String> {
    public UserRoleModel findByName(UserRole role);
}
