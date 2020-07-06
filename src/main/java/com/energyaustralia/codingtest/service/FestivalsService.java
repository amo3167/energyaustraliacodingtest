package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.MusicFestival;

import java.util.List;

/**
 * @author amo31
 */
public interface FestivalsService {

    /**
     * Retrieve music festival data from festival management API
     * @return List of MusicFestival
     */
    List<MusicFestival> getFestivalsData();

    /**
     * Clear festival cache
     */
    void clearCache();
}
