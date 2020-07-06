package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.MusicFestival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author amo31
 */
@Service("festivalsService")
public class FestivalsServiceImpl implements FestivalsService{

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
    public List<MusicFestival> getFestivalsData() {
        List<MusicFestival> result = new ArrayList<>();

        //TODO: need to handle exception
        ResponseEntity<MusicFestival[]> response = restTemplate.getForEntity(URL,MusicFestival[].class);
        MusicFestival[] festivals = response.getBody();
        if(festivals != null) {
                result = Arrays.asList(festivals);
        }
        return result;
     }

    /**
     * Clear festival cache
     */
    @Override
    @CacheEvict(value = "festivalDataCache", allEntries = true)
    public void clearCache() {
        //log.info("Cleared task cache");
    }


}
