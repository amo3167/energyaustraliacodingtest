package com.energyaustralia.codingtest.controller;

import com.energyaustralia.codingtest.model.RecordLabel;
import com.energyaustralia.codingtest.service.RecordLabelsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

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
    public Iterable<RecordLabel> read() throws ValidationException {
        return service.getRecordLabels();
    }

    @DeleteMapping("/cache")
    public void clearCache() {
        service.clearCache();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    String exceptionHandler(ValidationException e) {
        return e.getMessage();
    }
}
