package com.stefura.mentorsplatform.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MapperConfig {
    @Bean
    public DozerBeanMapper getDozerMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(Arrays.asList("classpath:/dozer-mapping/profileDtoToProfileMap.xml",
                "classpath:/dozer-mapping/profileToMentorDtoMap.xml",
                "classpath:/dozer-mapping/profileToExtendedProfileMap.xml",
                "classpath:/dozer-mapping/reviewToReviewDtoMap.xml"));
        return dozerBeanMapper;
    }
}
