package com.energyaustralia.codingtest.model;

import java.util.Objects;

/**
 * @author amo31
 */
public class BandInFestival implements Comparable<BandInFestival>{
    private String bandName;
    private String festivalName;

    public BandInFestival() {
    }

    public BandInFestival(String name, String festivalName) {
        this.bandName = name;
        this.festivalName = festivalName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BandInFestival)) return false;
        BandInFestival that = (BandInFestival) o;
        return getBandName().equals(that.getBandName()) &&
                getFestivalName().equals(that.getFestivalName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBandName(), getFestivalName());
    }

    @Override
    public int compareTo(BandInFestival obj) {
        int result = this.bandName.compareTo(obj.bandName);

        if (result == 0) {
            return this.festivalName.compareTo(obj.festivalName);
        }
        return result;
    }

    @Override
    public String toString() {
        return "BandInFestival{" +
                "bandName='" + bandName + '\'' +
                ", festivalName='" + festivalName + '\'' +
                '}';
    }
}
