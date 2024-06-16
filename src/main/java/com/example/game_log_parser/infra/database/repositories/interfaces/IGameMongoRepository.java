package com.example.game_log_parser.infra.database.repositories.interfaces;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.game_log_parser.infra.database.models.GameModel;

public  interface IGameMongoRepository extends MongoRepository<GameModel, Long>{
    Optional<GameModel> findById(long id);
}
