package com.energyaustralia.codingtest.controller;

import com.energyaustralia.codingtest.model.RecordLabel;
import com.energyaustralia.codingtest.service.RecordLabelsService;
import com.energyaustralia.codingtest.utils.HtmlHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author amo31
 */
@RestController
public class FestivalsController {

    Logger logger = LoggerFactory.getLogger(FestivalsController.class);
    private final RecordLabelsService service;

    @Value("${user.path}")
    private String userPath;

    @Value("${user.filename}")
    private String userFileName;

    @Autowired
    public FestivalsController(RecordLabelsService service) {
        this.service = service;
    }

    @GetMapping("/recordlabels")
    public Iterable<RecordLabel> read() throws ValidationException {

        List<RecordLabel> recordLabels = service.getRecordLabels();

        try {
            HtmlHelper.writeHtmlFile(recordLabels,userPath + File.separator+userFileName);
        } catch (IOException e) {
                logger.error("Fail to write file.",e);
        }

        return recordLabels;
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ValidationException.class)
    String exceptionHandler(ValidationException e) {
        return e.getMessage();
    }
}
