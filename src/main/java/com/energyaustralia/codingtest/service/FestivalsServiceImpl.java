package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.MusicFestival;
import com.energyaustralia.codingtest.model.RecordLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author amo31
 */
public class FestivalsServiceImpl implements FestivalsService{

    static final String URL = "http://eacodingtest.digital.energyaustralia.com.au/api/v1/festivals";
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Retrieve music festival data from festival management API
     * @return List of MusicFestival
     */
    @Override
    @Cacheable
    public List<MusicFestival> getFestivalsData() {

        return  Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(URL, MusicFestival[].class)));

    }


}
