package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.MusicFestival;
import com.energyaustralia.codingtest.model.RecordLabel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author amo31
 */
@Service
public interface FestivalsService {

    /**
     * Retrieve music festival data from festival management API
     * @return List of MusicFestival
     */
    List<MusicFestival> getFestivalsData();


}
