package io.github.ricky.core.polynomial.infrastructure;

import io.github.ricky.common.repository.mongo.MongoBaseRepository;
import io.github.ricky.core.polynomial.domain.PolynomialExpression;
import io.github.ricky.core.polynomial.domain.PolynomialExpressionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/13
 * @className MongoPolynomialExpressionRepository
 * @desc
 */
@Repository
@RequiredArgsConstructor
public class MongoPolynomialExpressionRepository extends MongoBaseRepository<PolynomialExpression> implements PolynomialExpressionRepository {

    @Override
    public void save(PolynomialExpression polynomialExpression) {
        super.save(polynomialExpression);
    }

    @Override
    public PolynomialExpression byId(String id) {
        return super.byId(id);
    }
}
