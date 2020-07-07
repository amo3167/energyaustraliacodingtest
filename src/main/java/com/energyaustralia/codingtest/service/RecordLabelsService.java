package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.RecordLabel;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * @author amo31
 */
public interface RecordLabelsService {

    /**
     * Construct record labels from music festival data
     *
     * @return List of RecordLabel
     * @throws ValidationException
     */
    List<RecordLabel> getRecordLabels() throws ValidationException;

    /**
     * Clear cache
     */
    void clearCache();
}
