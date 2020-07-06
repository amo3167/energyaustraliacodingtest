package com.energyaustralia.codingtest;

import com.energyaustralia.codingtest.model.FestivalCache;
import com.energyaustralia.codingtest.service.FestivalsService;
import com.energyaustralia.codingtest.service.RecordLablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author amo31
 */
public class AppStartupRunner  implements ApplicationRunner {

    @Autowired
    FestivalCache cache;

    @Autowired
    FestivalsService festivalsService;

    @Autowired
    RecordLablesService recordLablesService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cache.setFestivals(festivalsService.getFestivalsData());

        recordLablesService.getRecordLabels();

        System.out.println();

    }
}
