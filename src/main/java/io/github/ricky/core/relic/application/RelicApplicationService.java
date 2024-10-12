package io.github.ricky.core.relic.application;

import io.github.ricky.core.relic.application.dto.CalculateScoreCommand;
import io.github.ricky.core.relic.application.dto.CalculateScoreResponse;
import io.github.ricky.core.relic.domain.Relic;
import io.github.ricky.core.relic.domain.RelicFactory;
import io.github.ricky.core.relic.domain.RelicRepository;
import io.github.ricky.core.relic.domain.yieldweight.YieldWeight;
import io.github.ricky.core.relic.domain.yieldweight.YieldWeightDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/27
 * @className RelicApplicationService
 * @desc
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RelicApplicationService {

    private final RelicFactory relicFactory;
    private final YieldWeightDao yieldWeightDao;
    private final RelicRepository relicRepository;

    @Transactional
    public CalculateScoreResponse calculateScore(CalculateScoreCommand command) {
        Relic relic = relicFactory.createRelic(command);
        YieldWeight yieldWeight = yieldWeightDao.byBelongs(command.getBelongTo());
        double score = relic.calculateScore(yieldWeight);
        relicRepository.save(relic);
        return CalculateScoreResponse.builder()
                .id(relic.getId())
                .score(score)
                .build();
    }

    public void deleteById(String id) {
        relicRepository.removeById(id);
    }
}
