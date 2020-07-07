package com.energyaustralia.codingtest;

import com.energyaustralia.codingtest.model.RecordLabel;
import com.energyaustralia.codingtest.service.RecordLabelsServiceImpl;
import com.energyaustralia.codingtest.utils.HtmlHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.List;


/**
 * @author amo31
 */
@Component
public class AppStartupRunner  implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);


    private final RecordLabelsServiceImpl recordLablesService;

    @Autowired
    public AppStartupRunner(RecordLabelsServiceImpl recordLablesService) {
        this.recordLablesService = recordLablesService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try {
            List<RecordLabel> recordLabels = recordLablesService.getRecordLabels();

            HtmlHelper.writeHtmlFile(recordLabels,"test.html");
        }
        catch (ValidationException e) {
            logger.error("Something wrong.",e);
        }
        catch (IOException ie) {
            logger.error("Fail to write file.",ie);
        }
    }
}
