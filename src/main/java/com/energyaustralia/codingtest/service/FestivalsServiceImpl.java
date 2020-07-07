package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.MusicFestival;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.ValidationException;
import java.util.*;

/**
 * @author amo31
 */
@Service("festivalsService")
public class FestivalsServiceImpl implements FestivalsService{

    Logger logger = LoggerFactory.getLogger(FestivalsServiceImpl.class);

    static final String URL = "http://eacodingtest.digital.energyaustralia.com.au/api/v1/festivals";

    private final RestTemplate restTemplate;

    @Autowired
    public FestivalsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieve music festival data from festival management API
     * @return List of MusicFestival
     */
    @Override
    @Cacheable("festivalDataCache")
    public List<MusicFestival> getFestivalsData() throws ValidationException {
        List<MusicFestival> result = new ArrayList<>();

        try {
            ResponseEntity<MusicFestival[]> response = restTemplate.getForEntity(URL, MusicFestival[].class);
            MusicFestival[] festivals = response.getBody();
            if (festivals != null) {
                result = Arrays.asList(festivals);
            }
            return result;
        }
        catch (RestClientException e){
            throw new ValidationException("Fail to getFestivalsData.", e);
        }
     }

    /**
     * Clear festival cache
     */
    @Override
    @CacheEvict(value = "festivalDataCache", allEntries = true)
    public void clearCache() {
        logger.info("Cleared festivalDataCache cache");
    }


}
