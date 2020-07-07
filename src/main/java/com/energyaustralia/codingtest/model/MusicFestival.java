package com.energyaustralia.codingtest.model;

import java.util.List;
import java.util.Objects;

/**
 * @author amo31
 */
public class MusicFestival{

    private String name;

    private List<Band> bands;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }

    public MusicFestival(String name, List<Band> bands) {
        this.name = name;
        this.bands = bands;
    }

    public MusicFestival() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicFestival)) return false;
        MusicFestival that = (MusicFestival) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

}
