package com.energyaustralia.codingtest;

import com.energyaustralia.codingtest.model.RecordLabel;
import com.energyaustralia.codingtest.service.FestivalsService;
import com.energyaustralia.codingtest.service.RecordLablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.List;

/**
 * @author amo31
 */
public class AppStartupRunner  implements ApplicationRunner {

    @Autowired
    RecordLablesService recordLablesService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        List<RecordLabel> recordLabels =  recordLablesService.getRecordLabels();
//
//        System.out.println(recordLabels.toString());

    }
}
