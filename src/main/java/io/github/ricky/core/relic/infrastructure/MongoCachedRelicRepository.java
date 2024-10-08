package io.github.ricky.core.relic.infrastructure;

import io.github.ricky.common.repository.mongo.MongoBaseRepository;
import io.github.ricky.core.relic.domain.Relic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/8
 * @className MongoCachedRelicRepository
 * @desc
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class MongoCachedRelicRepository extends MongoBaseRepository<Relic> {



}
