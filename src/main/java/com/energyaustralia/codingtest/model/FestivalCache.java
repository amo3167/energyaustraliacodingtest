package com.energyaustralia.codingtest.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amo31
 */
@Component
public class FestivalCache {

    private List<MusicFestival> festivals = new ArrayList<>();

    public List<MusicFestival> getFestivals() {
        return festivals;
    }

    public void setFestivals(List<MusicFestival> festivals) {
        this.festivals = festivals;
    }
}
