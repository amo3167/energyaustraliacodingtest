package com.energyaustralia.codingtest;

import com.energyaustralia.codingtest.model.RecordLabel;
import com.energyaustralia.codingtest.service.RecordLabelsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author amo31
 */
@Component
public class AppStartupRunner  implements ApplicationRunner {

    @Autowired
    RecordLabelsServiceImpl recordLablesService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<RecordLabel> recordLabels =  recordLablesService.getRecordLabels();

        System.out.println(recordLabels.toString());

    }
}
