package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.RecordLabel;

import java.util.List;

/**
 * @author amo31
 */
public interface RecordLabelsService {

    /**
     * Construct record labels from music festival data
     *
     * @return List of RecordLabel
     */
    List<RecordLabel> getRecordLabels();

    /**
     * Clear cache
     */
    void clearCache();
}
