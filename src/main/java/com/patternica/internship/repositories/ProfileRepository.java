package com.patternica.internship.repositories;

import com.patternica.internship.models.Profile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long>, JpaSpecificationExecutor<Profile> {
    List<Profile> findAll();
    List<Profile> findByRate(Double rate);
    List<Profile> findByRateLessThan(Double rate);
    List<Profile> findByRateGreaterThan(Double rate);
    Profile findByUserId(Long userId);

    @Query("SELECT DISTINCT p.profession FROM Profile p")
    List<String> findAllProfessions();
}
