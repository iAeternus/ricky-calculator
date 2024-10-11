package io.github.ricky.core.relic.infrastructure;

import io.github.ricky.common.repository.mongo.MongoBaseRepository;
import io.github.ricky.core.relic.domain.Relic;
import io.github.ricky.core.relic.domain.RelicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className MongoRelicRepository
 * @desc
 */
@Repository
@RequiredArgsConstructor
public class MongoRelicRepository extends MongoBaseRepository<Relic> implements RelicRepository {

    @Override
    public void save(Relic relic) {
        super.save(relic);
    }

    @Override
    public Relic byId(String id) {
        return super.byId(id);
    }
}
