package com.energyaustralia.codingtest.controller;

import com.energyaustralia.codingtest.model.RecordLabel;
import com.energyaustralia.codingtest.service.RecordLabelsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amo31
 */
@RestController
public class FestivalsController {


    private final RecordLabelsServiceImpl service;

    @Autowired
    public FestivalsController(RecordLabelsServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/recordlabels")
    public Iterable<RecordLabel> read() {
        return service.getRecordLabels();
    }

    @DeleteMapping("/cache")
    public void clearCache() {
        service.clearCache();
    }
}
