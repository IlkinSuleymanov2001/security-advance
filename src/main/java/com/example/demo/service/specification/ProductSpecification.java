package com.example.demo.service.specification;

import com.example.demo.dao.entity.ProductEntity;
import com.example.demo.model.criteria.ProductCriteria;
import com.example.demo.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.example.demo.model.constants.ProductCriteriaConstants.*;

@AllArgsConstructor
public class ProductSpecification implements Specification<ProductEntity> {


    private final ProductCriteria pc;

    @Override
    public Predicate toPredicate(Root<ProductEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {


        Predicate[] predicate = PredicateUtil.builder()

                .addNullSafety(pc.getPriceFrom(),
                        priceFrom -> cb.greaterThanOrEqualTo(root.get(PRICE), priceFrom))
                .addNullSafety(pc.getPriceTo(),
                        priceTo -> cb.lessThanOrEqualTo(root.get(PRICE), priceTo))
                .addNullSafety(pc.getPrice(),
                        price -> cb.equal(root.get(PRICE), price))


                .addNullSafety(pc.getCreatedAtTo(),
                        dateAtTo -> cb.lessThan(root.get(CREATED_AT), dateAtTo))
                .addNullSafety(pc.getCreatedAtFrom(),
                        dateAtFrom -> cb.greaterThan(root.get(CREATED_AT), dateAtFrom))
                .addNullSafety(pc.getCreatedAt(),
                        dateAt -> cb.equal(root.get(CREATED_AT), dateAt))

                .addNullSafety(pc.getCategory(),
                        c -> cb.equal(root.get(CATEGORY), c))

                .addNullSafety(pc.getProductCode(),
                        pc -> cb.equal(root.get(PRODUCT_CODE), pc))

                .addNullSafety(pc.getStock(),
                        pc -> cb.equal(root.get(STOCK), pc))
                .addNullSafety(pc.getStockFrom(),
                        pc -> cb.greaterThanOrEqualTo(root.get(STOCK), pc))
                .addNullSafety(pc.getStockTo(),
                        pc -> cb.lessThanOrEqualTo(root.get(STOCK), pc))


                .build();


        return cb.and(predicate);
    }
}
