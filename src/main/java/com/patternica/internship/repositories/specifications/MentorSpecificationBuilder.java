package com.patternica.internship.repositories.specifications;

import com.patternica.internship.models.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MentorSpecificationBuilder {
    private List<Specification<Profile>> mentorSpecifications;

    public MentorSpecificationBuilder() {
        mentorSpecifications = new ArrayList<>();
        mentorSpecifications.add(((root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.orderBy(
                    criteriaBuilder.desc(root.get(Profile_.user).get(User_.rating)),
                    criteriaBuilder.desc(root.get(Profile_.viewsCount))
            );
            return criteriaBuilder.equal(root.get(Profile_.user).get(User_.type), UserType.MENTOR);
        }));
    }

    public void addSpecification(Specification<Profile> newSpecification) {
        mentorSpecifications.add(newSpecification);
    }

    public Specification<Profile> build() {
        return mentorSpecifications
                .stream()
                .reduce((spec1, spec2) -> Specification.where(spec1).and(spec2))
                .orElse(mentorSpecifications.iterator().next());
    }
}
