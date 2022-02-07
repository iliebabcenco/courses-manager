package md.ilie.coursesmanager.educationservice.util.mongo;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class SequenceGeneratorService {

  @Autowired
  private MongoOperations operations;

  public int generateSequence(String seqName) {
    final Query q = new Query(Criteria.where("id").is(seqName));
    final Update u = new Update().inc("sequence", 1);
    final DatabaseSequence counter = operations.findAndModify(q, u,
        FindAndModifyOptions.options().returnNew(true).upsert(true), DatabaseSequence.class);

    return !Objects.isNull(counter) ? counter.getSequence() : 1;
  }

}
