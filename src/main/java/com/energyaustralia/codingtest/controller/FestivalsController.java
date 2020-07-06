package com.energyaustralia.codingtest.controller;

import com.energyaustralia.codingtest.model.RecordLabel;
import com.energyaustralia.codingtest.service.RecordLablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amo31
 */
@RestController
public class FestivalsController {

    @Autowired
    RecordLablesService service;

    @GetMapping("/recordlabels")
    public Iterable<RecordLabel> read() {
        return service.getRecordLabels();
    }
}
