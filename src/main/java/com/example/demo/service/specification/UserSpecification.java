package com.example.demo.service.specification;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.model.criteria.UserCriteria;
import com.example.demo.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.example.demo.model.constants.UserCriteriaConstants.*;
import static com.example.demo.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class UserSpecification implements Specification<UserEntity> {


    private final UserCriteria userCriteria;

    @Override
    public Predicate toPredicate(Root<UserEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {


        Predicate[] predicate = PredicateUtil.builder()
                .addNullSafety(userCriteria.getBirthPlace(),
                        bp -> cb.like(root.get(BIRTHPLACE), applyLikePattern(bp)))

                .addNullSafety(userCriteria.getAgeFrom(),
                        ageFrom -> cb.greaterThanOrEqualTo(root.get(AGE), ageFrom))
                .addNullSafety(userCriteria.getAgeTo(),
                        ageTo -> cb.lessThanOrEqualTo(root.get(AGE), ageTo))
                .addNullSafety(userCriteria.getCreatedAtFrom(),
                        dateAtFrom -> cb.greaterThan(root.get(CREATED_AT), dateAtFrom))
                .addNullSafety(userCriteria.getCreatedAtTo(),
                        dateAtTo -> cb.lessThan(root.get(CREATED_AT), dateAtTo))
                .build();


        return cb.and(predicate);
    }
}
