package com.energyaustralia.codingtest.service;

import com.energyaustralia.codingtest.model.RecordLabel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author amo31
 */
public interface RecordLablesService {

    /**
     * Construct record labels from music festival data
     * @return List of RecordLabel
     */
    List<RecordLabel> getRecordLabels();
}
