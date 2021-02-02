package com.patternica.internship.repositories;

import com.patternica.internship.models.Aspect;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AspectRepository extends CrudRepository<Aspect, Long> {
    Aspect findByName(String name);
    List<Aspect> findAll();

    @Query("SELECT a.name from " +
            "Aspect a JOIN a.profilesWithAspect ap " +
            "WHERE ap.id = ?1")
    List<String> findAspectsNamesByProfileId(Long profileId);

    void deleteByName(String name);
}
