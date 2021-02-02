package com.stefura.mentorsplatform.services;

import com.stefura.mentorsplatform.models.Aspect;
import com.stefura.mentorsplatform.repositories.AspectRepository;
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
