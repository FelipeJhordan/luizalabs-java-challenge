package com.example.game_log_parser.infra.database.repositories;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.game_log_parser.infra.database.models.DatabaseSequenceModel;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Repository
public class DatabaseSequenceRepository {
  @Autowired
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        DatabaseSequenceModel counter = mongoOperations.findAndModify(new Query().addCriteria(where("_id").is(seqName)),
        new Update().inc("seq",1), options().returnNew(true).upsert(true),
        DatabaseSequenceModel.class);
      return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
