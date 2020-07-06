package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.MusicFestival;
import com.energyaustralia.codingtest.model.RecordLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author amo31
 */
public class FestivalsServiceImpl implements FestivalsService{

    static final String URL = "http://eacodingtest.digital.energyaustralia.com.au/api/v1/festivals";

    private RestTemplate restTemplate;

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

        return  Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(URL, MusicFestival[].class)));

    }

    @CacheEvict(value = "festivalDataCache", allEntries = true)
    @Override
    public void clearCache() {
        //log.info("Cleared task cache");
    }


}
