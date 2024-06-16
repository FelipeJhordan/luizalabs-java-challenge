package com.example.game_log_parser.infra.database.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.game_log_parser.core.domain.entities.Game;
import com.example.game_log_parser.core.domain.interfaces.IGameRepository;
import com.example.game_log_parser.infra.database.mappers.GameMapper;
import com.example.game_log_parser.infra.database.models.GameModel;
import com.example.game_log_parser.infra.database.repositories.interfaces.IGameMongoRepository;



@Repository
public class GameRepositoryImp implements IGameRepository {
    private final IGameMongoRepository mongoRepository;
    
    @Autowired
    public GameRepositoryImp(IGameMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Game findById(long id) {
        Optional<GameModel> gameModel =  mongoRepository.findById(id);
        if(gameModel.isEmpty()) {
            return null;
        }

        return GameMapper.toDomain(gameModel.get());
    }

    @Override
    public List<Game> findAll() {
        List<GameModel> gameModels =  mongoRepository.findAll();
        return gameModels.stream().map(GameMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public int saveBulk(List<Game> games) {
        List<GameModel> gameModels = games.stream().map(GameMapper::ofDomain).collect(Collectors.toList());
        return mongoRepository.saveAll(gameModels).size();
    }
        
}
