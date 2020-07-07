package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.MusicFestival;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * @author amo31
 */
public interface FestivalsService {

    /**
     * Retrieve music festival data from festival management API
     * @return List of MusicFestival
     * ValidationException
     */
    List<MusicFestival> getFestivalsData() throws ValidationException;

}
