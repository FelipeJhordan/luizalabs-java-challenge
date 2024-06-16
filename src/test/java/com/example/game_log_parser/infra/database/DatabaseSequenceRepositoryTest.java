package com.example.game_log_parser.infra.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.example.game_log_parser.infra.database.models.DatabaseSequenceModel;
import com.example.game_log_parser.infra.database.repositories.DatabaseSequenceRepository;

public class DatabaseSequenceRepositoryTest {

    private DatabaseSequenceRepository databaseSequenceRepository;
    private MongoOperations mongoOperations;

    @BeforeEach
    public void setup() {
        mongoOperations = mock(MongoOperations.class);
        databaseSequenceRepository = new DatabaseSequenceRepository(mongoOperations);
    }

    @Test
    public void shouldReturnTheDefaultSequenceAfterCallGenerateSequence() {
        String seqName = "testSeq";
        DatabaseSequenceModel counter = new DatabaseSequenceModel();

        when(mongoOperations.findAndModify(any(Query.class), any(Update.class), any())).thenReturn(counter);

        long result = databaseSequenceRepository.generateSequence(seqName);

        assertEquals(1, result);
    }
    @Test
    public void shouldReturnTheSequenceAfterCallGenerateSequence() {
        String seqName = "testSeq";
        DatabaseSequenceModel counter = new DatabaseSequenceModel();

        counter.setSeq(3);

        when(mongoOperations.findAndModify(any(Query.class), any(Update.class),any(FindAndModifyOptions.class),any() )).thenReturn(counter);

        long result = databaseSequenceRepository.generateSequence(seqName);

        assertEquals(3, result);
    }
}