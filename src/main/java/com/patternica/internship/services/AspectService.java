package com.patternica.internship.services;

import com.patternica.internship.models.Aspect;
import com.patternica.internship.repositories.AspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AspectService {
    private AspectRepository aspectRepository;

    @Autowired
    public AspectService(AspectRepository aspectRepository) {
        this.aspectRepository = aspectRepository;
    }

    public List<Aspect> getAllLifeAspects() {
        return aspectRepository.findAll();
    }

    public List<String> getAspectsNamesByProfileId(Long profileId) {
        return aspectRepository.findAspectsNamesByProfileId(profileId);
    }
}
