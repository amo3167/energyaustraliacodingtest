package com.energyaustralia.codingtest.model;

import java.util.Objects;

/**
 * @author amo31
 */
public class Band{
    private String name;
    private String recordLabel;

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
    public String toString() {
        return "Band{" +
                "name='" + name + '\'' +
                ", recordLabel='" + recordLabel + '\'' +
                '}';
    }
}
