package com.stefura.mentorsplatform.repositories.specifications;

import com.stefura.mentorsplatform.models.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Root;

public class ProfileSpecifications {
    public static Specification<Profile> getByUserFullNameSpec(String userFullName) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(Profile_.user).get(User_.fullName), "%" + userFullName + "%");

    }

    public static Specification<Profile> getByDescriptionSpec(String description) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.distinct(true);

            return criteriaBuilder.like(root.get(Profile_.description),
                    "%" + description + "%");
        };
    }

    public static Specification<Profile> getByProfessionSpec(String profession) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(Profile_.profession),
                "%" + profession + "%");
    }

    public static Specification<Profile> getByLifeAspectSpec(String lifeAspect) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Root<Aspect> aspect = criteriaQuery.from(Aspect.class);

            return criteriaBuilder.and(criteriaBuilder.like(aspect.get(Aspect_.name), "%" + lifeAspect + "%"),
                    criteriaBuilder.isMember(aspect, root.get(Profile_.profileAspects)));
        };
    }

    public static Specification<Profile> getByRateLessThanOrEqualsToSpec(Double maxRate) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
                root.get(Profile_.rate), maxRate);
    }

    public static Specification<Profile> getByRatingHigherThanOrEqualsToSpec(Double minRating) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
                root.get(Profile_.user).get(User_.rating), minRating);
    }
}
