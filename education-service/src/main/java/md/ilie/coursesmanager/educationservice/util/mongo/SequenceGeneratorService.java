package md.ilie.coursesmanager.educationservice.util.mongo;


import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SequenceGeneratorService {

  @Autowired
  private MongoOperations operations;

  public int generateSequence(String seqName) {
    final Query q = new Query(Criteria.where("id").is(seqName));
    // increment the sequence number by 1
    // "sequence" should match the attribute value specified in DbSequence.java class.
    final Update u = new Update().inc("sequence", 1);
    // modify in document
    final DatabaseSequence counter = operations.findAndModify(q, u,
        FindAndModifyOptions.options().returnNew(true).upsert(true), DatabaseSequence.class);

    return !Objects.isNull(counter) ? counter.getSequence() : 1;
  }

}
