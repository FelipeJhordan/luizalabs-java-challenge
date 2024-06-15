package com.example.game_log_parser.infra.database.repositories;

import java.util.Objects;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import com.example.game_log_parser.infra.database.models.DatabaseSequence;

public class DatabaseSequenceRepository {
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(new Query().addCriteria(where("_id").is(seqName)),
        new Update().inc("seq",1), options().returnNew(true).upsert(true),
        DatabaseSequence.class);
      return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
