package com.stefura.mentorsplatform.repositories;

import com.stefura.mentorsplatform.models.Aspect;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AspectRepository extends CrudRepository<Aspect, Long> {
    List<Aspect> findAll();

    @Query("SELECT a.name from " +
            "Aspect a JOIN a.profilesWithAspect ap " +
            "WHERE ap.id = ?1")
    List<String> findAspectsNamesByProfileId(Long profileId);
}
