package com.energyaustralia.codingtest.model;

import java.util.Objects;

/**
 * @author amo31
 */
public class Band implements Comparable<Band>{
    private String name;
    private String recordLabel;

    public Band(String name, String recordLabel) {
        this.name = name;
        this.recordLabel = recordLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Band)) return false;
        Band band = (Band) o;
        return getName().equals(band.getName()) &&
                getRecordLabel().equals(band.getRecordLabel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRecordLabel());
    }

    @Override
    public int compareTo(Band obj) {
        int result = this.name.compareTo(obj.name);

        if (result == 0) {
            return this.recordLabel.compareTo(obj.recordLabel);
        }
        return result;
    }
}
