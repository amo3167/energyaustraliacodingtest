package com.energyaustralia.codingtest.model;

import java.util.*;
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

    public TreeSet<BandInFestival> getBands() {
        return bands;
    }

    public void setBands(TreeSet<BandInFestival> bands) {
        this.bands = bands;
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

    @Override
    public String toString() {
        return "RecordLabel{" +
                "recordLabelName='" + recordLabelName + '\'' +
                ", bands=" + bands +
                '}';
    }
}
