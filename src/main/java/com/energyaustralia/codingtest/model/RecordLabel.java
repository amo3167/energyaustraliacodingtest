package com.energyaustralia.codingtest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author amo31
 */
public class RecordLabel implements Comparable<RecordLabel> {

    private String recordLabelName;
    private TreeSet<BandInFestival> bands;

    public RecordLabel(String recordLabel){
        this.recordLabelName = recordLabel;
    }

    public RecordLabel(String recordLabel, List<BandInFestival> bands) {
        this.recordLabelName = recordLabel;
        this.bands = bands.stream().collect(Collectors.toCollection(
                ()-> new TreeSet<>(BandInFestival::compareTo)
        ));
    }

    public String getRecordLabel() {
        return recordLabelName;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabelName = recordLabel;
    }

    public List<BandInFestival> getBands() {
        return new ArrayList<>(bands);
    }

    public void setBands(List<BandInFestival> bands) {
        this.bands = bands.stream().collect(Collectors.toCollection(
                ()-> new TreeSet<>(BandInFestival::compareTo)
        ));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordLabel)) return false;
        RecordLabel that = (RecordLabel) o;
        return recordLabelName.equals(that.recordLabelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordLabelName);
    }

    @Override
    public int compareTo(RecordLabel obj) {
        return this.recordLabelName.compareTo(obj.recordLabelName);
    }
}
